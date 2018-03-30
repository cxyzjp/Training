package com.cxy.aliyun.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.cxy.aliyun.config.AliConfig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListObjectsSample {

    public static void main(String[] args) throws IOException {

//        OSS client = AliConfig.ossClient();
        OSS client = null;
        String bucketName = AliConfig.bucketName;

        try {
            final String content = "Hello OSS";
            final String keyPrefix = "MyObjectKey";

            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
            }

            // Prepare the environment---inserting 100 test files.
            List<String> keys = new ArrayList<String>();
            for (int i = 0; i < 100; i++) {
                String key = keyPrefix + i;
                InputStream instream = new ByteArrayInputStream(content.getBytes());
                client.putObject(bucketName, key, instream);
                keys.add(key);
            }
            System.out.println("Put " + keys.size() + " objects completed." );

            ObjectListing objectListing = null;
            String nextMarker = null;
            final int maxKeys = 30;
            List<OSSObjectSummary> sums = null;

            // use default parameter to list the files. By default up to 100 entries could be listed.
            System.out.println("Default paramters:");
            objectListing = client.listObjects(bucketName);
            sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }

            // Sets the max keys with 200 (the max value could be 1000).
            System.out.println("With max keys:");
            objectListing = client.listObjects(new ListObjectsRequest(bucketName).
                    withMaxKeys(200));

            sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }

            // Gets the object with specified prefix. By default it returns up to 100 entries.
            System.out.println("With prefix:");
            objectListing = client.listObjects(new ListObjectsRequest(bucketName).withPrefix(keyPrefix));

            sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }

            // Gets the object with specified marker. By default it returns up to 100 entries.
            System.out.println("With marker: ");
            objectListing = client.listObjects(new ListObjectsRequest(bucketName).withMarker(keyPrefix + "11"));

            sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }

            // Gets all object by paging. Each page will have up to 100 entries.
            System.out.println("List all objects:");
            nextMarker = null;
            do {
                objectListing = client.listObjects(new ListObjectsRequest(bucketName).
                        withMarker(nextMarker).withMaxKeys(maxKeys));

                sums = objectListing.getObjectSummaries();
                for (OSSObjectSummary s : sums) {
                    System.out.println("\t" + s.getKey());
                }

                nextMarker = objectListing.getNextMarker();

            } while (objectListing.isTruncated());


            // Gets all object with specified prefix by paging. Each page will have up to 100 entries.
            System.out.println("List all objects after marker:");
            nextMarker = keyPrefix + "11";
            do {
                objectListing = client.listObjects(new ListObjectsRequest(bucketName).
                        withMarker(nextMarker).withMaxKeys(maxKeys));

                sums = objectListing.getObjectSummaries();
                for (OSSObjectSummary s : sums) {
                    System.out.println("\t" + s.getKey());
                }

                nextMarker = objectListing.getNextMarker();

            } while (objectListing.isTruncated());

            // Gets all object with specified marker by paging. Each page will have up to 100 entries.
            System.out.println("List all objects with prefix:");
            nextMarker = null;
            do {
                objectListing = client.listObjects(new ListObjectsRequest(bucketName).
                        withPrefix(keyPrefix + "1").withMarker(nextMarker).withMaxKeys(maxKeys));

                sums = objectListing.getObjectSummaries();
                for (OSSObjectSummary s : sums) {
                    System.out.println("\t" + s.getKey());
                }

                nextMarker = objectListing.getNextMarker();

            } while (objectListing.isTruncated());

            // Clean up the environment----deleting the test files.
            System.out.println("Deleting all objects:");
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(
                    new DeleteObjectsRequest(bucketName).withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String object : deletedObjects) {
                System.out.println("\t" + object);
            }

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }
}