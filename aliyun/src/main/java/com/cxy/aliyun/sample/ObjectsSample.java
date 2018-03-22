package com.cxy.aliyun.sample;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.cxy.aliyun.config.OSSConfig;

import java.util.List;

public class ObjectsSample {

    public static void main(String[] args) {
        OSS client = OSSConfig.ossClient();
        String bucketName = OSSConfig.bucketName;

        List<Bucket> buckets = client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

        BucketInfo info = client.getBucketInfo(bucketName);
        System.out.println(info);
    }

}
