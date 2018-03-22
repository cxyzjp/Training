package com.cxy.aliyun.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class OSSConfig {

    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "LTAIPm88zC2syDI0";
    private static final String accessKeySecret = "qAIva0tBU8Zwf8VKlxpi7zHwrCqwyW";
    public static final String bucketName = "rad";

    public static OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
