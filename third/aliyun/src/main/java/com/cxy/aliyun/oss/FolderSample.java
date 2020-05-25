package com.cxy.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.cxy.aliyun.config.AliConfig;

public class FolderSample {

    public static void main(String[] args) {
        OSS client = AliConfig.ossClient();
        String bucketName = AliConfig.bucketName;

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        listObjectsRequest.setPrefix("input/");
        listObjectsRequest.setDelimiter("/");
        ObjectListing objectListing = client.listObjects(listObjectsRequest);

        System.out.println(objectListing);

//        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
//        listBucketsRequest.setBid(bucketName);
//        listBucketsRequest.setPrefix("input");
//        BucketList bucketList = client.listBuckets(listBucketsRequest);
//        System.out.println(bucketList);

//        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//            System.out.println(objectSummary.getKey()+ " = " +objectSummary.getSize());
//        }
    }

}
