package com.cxy.aliyun.mts;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.SubmitJobsRequest;
import com.aliyuncs.mts.model.v20140618.SubmitJobsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.cxy.aliyun.config.AliConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SimpleTranscode {

    private static String templateId = "S00000001-100030";
    private static String ossInputObject = "record/a.mp4";
    private static String ossOutputObject = "1/a";

    public static void main(String[] args) {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                AliConfig.region,      // 地域ID
                AliConfig.accessKeyId,      // RAM账号的AccessKey ID
                AliConfig.accessKeySecret); // RAM账号Access Key Secret
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建API请求并设置参数
        SubmitJobsRequest request = new SubmitJobsRequest();
        // Input
        JSONObject input = new JSONObject();
        input.put("Location", AliConfig.ossLocation);
        input.put("Bucket", "chuandao01");
        try {
            input.put("Object", URLEncoder.encode("1/in/c.avi", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("input URL encode failed");
        }
        request.setInput(input.toJSONString());

        // Output
        String outputOSSObject;
        try {
            outputOSSObject = URLEncoder.encode("1/out/c", "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("output URL encode failed");
        }
        JSONObject output = new JSONObject();
        output.put("OutputObject", outputOSSObject);

        // Ouput->Container
//        JSONObject container = new JSONObject();
//        container.put("Format", "mp4");
//        output.put("Container", container.toJSONString());
//        // Ouput->Video
//        JSONObject video = new JSONObject();
//        video.put("Codec", "H.264");
//        video.put("Bitrate", "1500");
//        video.put("Width", "1280");
//        video.put("Fps", "25");
//        output.put("Video", video.toJSONString());
//        // Ouput->Audio
//        JSONObject audio = new JSONObject();
//        audio.put("Codec", "AAC");
//        audio.put("Bitrate", "128");
//        audio.put("Channels", "2");
//        audio.put("Samplerate", "44100");
//        output.put("Audio", audio.toJSONString());
        // Ouput->TemplateId
        output.put("TemplateId", templateId);
        JSONArray outputs = new JSONArray();
        outputs.add(output);

        request.setOutputs(outputs.toJSONString());
        request.setOutputLocation(AliConfig.ossLocation);
        request.setOutputBucket("chuandao01");

        // PipelineId
        request.setPipelineId(AliConfig.pipelineId);
        // 发起请求并处理应答或异常
        SubmitJobsResponse response;
        try {
            response = client.getAcsResponse(request);
            System.out.println("RequestId is:" + response.getRequestId());
            if (response.getJobResultList().get(0).getSuccess()) {
                System.out.println("JobId is:" + response.getJobResultList().get(0).getJob().getJobId());
            } else {
                System.out.println("SubmitJobs Failed code:" + response.getJobResultList().get(0).getCode() +
                        " message:" + response.getJobResultList().get(0).getMessage());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}