package com.cxy.aliyun.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class AliConfig {

    public static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    public static final String region = "cn-hangzhou";
    public static final String ossLocation = "oss-cn-hangzhou";
    public static final String accessKeyId = "LTAID4ynYiSomloM";
    public static final String accessKeySecret = "accessKeySecret";
    public static final String bucketName = "chuandao01";
    public static final String inputBucketName = "chuandao-in1";
    public static final String outputBucketName = "chuandao-out1";

    public static final String pipelineId = "b51564714aa34c428cbcd0dfcfa3150c";

    // 媒体上传用户账号信息
    public static final String m_accessKeyID = "LTAIlvL4JjetBzFR";
    public static final String m_accessKeySecret = "m_accessKeySecret";
    public static final String m_roleArn = "acs:ram::1317421807545751:role/media";
    public static final String m_roleSessionName = "media_user";

    public static OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
