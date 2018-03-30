package com.cxy.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.cxy.aliyun.config.AliConfig;

public class ObjectsSample {

    public static void main(String[] args) {
        OSS client = AliConfig.ossClient();
        String bucketName = AliConfig.bucketName;

        ObjectListing objectListing = client.listObjects(bucketName, "campaign/");
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey()+ " = " +objectSummary.getSize());
        }
    }

}
