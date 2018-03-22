package com.cxy.aliyun.sample;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.cxy.aliyun.config.OSSConfig;

import java.util.List;

public class ObjectsSample {

    public static void main(String[] args) {
        OSS client = OSSConfig.ossClient();
        String bucketName = OSSConfig.bucketName;

        ObjectListing objectListing = client.listObjects(bucketName, "campaign/1803/");
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey()+ " = " +objectSummary.getSize());
        }
    }

}
