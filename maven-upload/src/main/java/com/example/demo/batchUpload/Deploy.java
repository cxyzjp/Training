package com.example.demo.batchUpload;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * 上传依赖到 Maven 私服
 * 注意快照版只能传快照版，正式版只能传正式版
 * <p>
 * mvn -s F:\.m2\settings.xml
 * org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy-file
 * -Durl=http://IP:PORT/nexus/content/repositories/thirdpart
 * -DrepositoryId=thirdpart
 * -Dfile=antlr-2.7.2.jar
 * -DpomFile=antlr-2.7.2.pom
 * -Dpackaging=jar
 * -DgeneratePom=false
 * -Dsources=./path/to/artifact-name-1.0-sources.jar
 * -Djavadoc=./path/to/artifact-name-1.0-javadoc.jar
 */
public class Deploy {

    // 自定义配置start
    private static final String jarBaseDir = "C:\\Users\\Bowen\\Desktop";
    private static final String jarPath = "C:\\Users\\Bowen\\Desktop\\com\\baozun\\casaba-plus";

    private static final String mavenSettingPath = "D:\\software\\apache\\apache-maven-3.5.3\\conf\\settings1.xml";
    // maven-releases maven-snapshots
    private static final String nexusUrl = "http://192.168.4.108:8081/repository/maven-snapshots/";
    // mavenSettingPath中配置的repositoryId， nexus-releases nexus-snapshots
    private static final String repositoryId = "nexus-snapshots";
    // 自定义配置end

    private static final int jarBaseDirLength = jarBaseDir.length();
    private static final Pattern DATE_PATTERN = Pattern.compile("-[\\d]{8}\\.[\\d]{6}-");
    private static final Runtime CMD = Runtime.getRuntime();
    private static final Writer ERROR;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    private static final String BASE_CMD = "cmd /c mvn -s " + mavenSettingPath +
            " deploy:deploy-file " +
            " -Durl=" + nexusUrl +
            " -DrepositoryId=" + repositoryId +
            " -DgeneratePom=false";

    static {
        Writer err = null;
        try {
            err = new OutputStreamWriter(new FileOutputStream("deploy-error.log"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        ERROR = err;
    }

    public static void main(String[] args) {
        //上传某个目录下的包
        deploy(Objects.requireNonNull(new File(jarPath).listFiles()));
//        if(checkArgs(args)){
//            File file = new File(args[0]);
//            deploy(file.listFiles());
//        }
        EXECUTOR_SERVICE.shutdown();
        try {
            ERROR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void error(String error) {
        try {
            System.err.println(error);
            ERROR.write(error + "\n");
            ERROR.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deploy(File[] files) {
        if (files.length > 0) {
            if (files[0].isDirectory()) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deploy(Objects.requireNonNull(file.listFiles()));
                    }
                }
            } else if (files[0].isFile()) {
                File pom = null;
                File jar = null;
                File source = null;
                File javadoc = null;
                //忽略日期快照版本，如 xxx-mySql-2.2.6-20170714.095105-1.jar
                for (File file : files) {
                    String name = file.getName();
                    if (!DATE_PATTERN.matcher(name).find()) {
                        if (name.endsWith(".pom")) {
                            pom = file;
                        } else if (name.endsWith("-javadoc.jar")) {
                            javadoc = file;
                        } else if (name.endsWith("-sources.jar")) {
                            source = file;
                        } else if (name.endsWith(".jar")) {
                            jar = file;
                        }
                    }
                }
                if (pom != null) {
                    if (jar != null) {
                        deploy(pom, jar, source, javadoc);
                    } else if (packingIsPom(pom)) {
                        deployPom(pom);
                    }
                }
            }
        }
    }

    private static boolean packingIsPom(File pom) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(pom)));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().contains("<packaging>pom</packaging>")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static boolean checkArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("用法如： java -jar Deploy D:\\some\\path\\");
            return false;
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println(args[0] + " 目录不存在!");
            return false;
        }
        if (!file.isDirectory()) {
            System.out.println("必须指定为目录!");
            return false;
        }
        return true;
    }

    private static void deployPom(final File pom) {
        EXECUTOR_SERVICE.execute(() -> {
            StringBuilder cmd = new StringBuilder(BASE_CMD);
            cmd.append(" -DpomFile=").append(pom.getPath());

            //以下步骤为获得根路径后减掉包名以前的路径，去除文件名，然后再得出groupId、artifactId、version
            //获得根路径后减掉包名以前的路径
            String str = pom.getPath().substring(jarBaseDirLength + 1);
            String str1 = str.substring(0, str.lastIndexOf("\\"));
            String version = str1.substring(str1.lastIndexOf("\\") + 1, str1.length());
            cmd.append(" -Dversion=").append(version);
            System.err.println(version);

            String str2 = str1.substring(0, str1.lastIndexOf("\\"));
            str2 = str2.replace("\\", ".");
            String groupId = str2.substring(0, str2.lastIndexOf("."));
            cmd.append(" -DgroupId=").append(groupId);

            String artifactId = str2.substring(str2.lastIndexOf(".") + 1, str2.length());
            cmd.append(" -DartifactId=").append(artifactId);

            cmd.append(" -Dfile=").append(pom.getPath());
            System.out.println(cmd.toString());
            try {
                final Process proc = CMD.exec(cmd.toString(), null, pom.getParentFile());
                InputStream inputStream = proc.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line;
                StringBuffer logBuffer = new StringBuffer();
                logBuffer.append("\n\n\n==================================\n");
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("[INFO]") || line.startsWith("Upload")) {
                        logBuffer.append(Thread.currentThread().getName()).append(" : ").append(line).append("\n");
                    }
                }
                System.out.println(logBuffer);
                int result = proc.waitFor();
                if (result != 0) {
                    error("上传失败：" + pom.getAbsolutePath());
                }
            } catch (IOException | InterruptedException e) {
                error("上传失败：" + pom.getAbsolutePath());
                e.printStackTrace();
            }
        });
    }

    private static void deploy(final File pom, final File jar, final File source, final File javadoc) {
        EXECUTOR_SERVICE.execute(() -> {
            StringBuilder cmd = new StringBuilder(BASE_CMD);
            cmd.append(" -DpomFile=").append(pom.getPath());

            //以下步骤为获得根路径后减掉包名以前的路径，去除文件名，然后再得出groupId、artifactId、version
            //获得根路径后减掉包名以前的路径
            String str = pom.getPath().substring(jarBaseDirLength + 1);
            String str1 = str.substring(0, str.lastIndexOf("\\"));
            String version = str1.substring(str1.lastIndexOf("\\") + 1, str1.length());
            cmd.append(" -Dversion=").append(version);
            System.err.println(version);

            String str2 = str1.substring(0, str1.lastIndexOf("\\"));
            str2 = str2.replace("\\", ".");
            String groupId = str2.substring(0, str2.lastIndexOf("."));
            cmd.append(" -DgroupId=").append(groupId);

            String artifactId = str2.substring(str2.lastIndexOf(".") + 1, str2.length());
            cmd.append(" -DartifactId=").append(artifactId);
            if (jar != null) {
                //当有bundle类型时，下面的配置可以保证上传的jar包后缀为.jar
                cmd.append(" -Dpackaging=jar -Dfile=").append(jar.getPath());
            } else {
                cmd.append(" -Dfile=").append(pom.getName());
            }
            if (source != null) {
                cmd.append(" -Dsources=").append(source.getName());
            }
            if (javadoc != null) {
                cmd.append(" -Djavadoc=").append(javadoc.getName());
            }
            System.out.println(cmd.toString());
            try {
                final Process proc = CMD.exec(cmd.toString(), null, pom.getParentFile());
                InputStream inputStream = proc.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line;
                StringBuffer logBuffer = new StringBuffer();
                logBuffer.append("\n\n\n=======================================\n");
                while ((line = reader.readLine()) != null) {
                    //if (line.startsWith("[INFO]") || line.startsWith("Upload")) {
                    logBuffer.append(Thread.currentThread().getName()).append(" : ").append(line).append("\n");
                    //}
                }
                System.out.println(logBuffer);
                int result = proc.waitFor();
                if (result != 0) {
                    error("上传失败：==========" + pom.getAbsolutePath());
                }
            } catch (IOException | InterruptedException e) {
                error("上传失败：" + pom.getAbsolutePath());
                e.printStackTrace();
            }
        });
    }
}
