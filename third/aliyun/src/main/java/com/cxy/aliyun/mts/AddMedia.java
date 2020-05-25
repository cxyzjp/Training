package com.cxy.aliyun.mts;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.AddMediaRequest;
import com.aliyuncs.mts.model.v20140618.AddMediaResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.cxy.aliyun.config.AliConfig;

public class AddMedia {
    //Step 1 .set region：cn-hangzhou、cn-shenzhen、cn-shanghai、cn-beijing
    private String OSS_REGION = "oss-cn-shenzhen";
    //Step 2.set accesskey & keySecret
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    private static DefaultAcsClient aliyunClient;

//    static {
//        try {
//            DefaultProfile.addEndpoint(REGION, REGION, "Mts", mtsEndpoint);
//        } catch (ClientException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        aliyunClient = new DefaultAcsClient(DefaultProfile.getProfile(REGION, accessKeyId, accessKeySecret));
//    }

    public static void main(String[] args) throws ClientException {
        String region = AliConfig.region;
        String mtsEndpoint = "mts." + region + ".aliyuncs.com";
        try {
            DefaultProfile.addEndpoint(region, region, "Mts", mtsEndpoint);
        } catch (ClientException e) {
            e.printStackTrace();
            System.exit(1);
        }
        aliyunClient = new DefaultAcsClient(DefaultProfile.getProfile(region, accessKeyId, accessKeySecret));


        AddMediaRequest request = new AddMediaRequest();
        request.setFileURL("http://mtb-sz-in.oss-cn-shenzhen.aliyuncs.com/media/r180-ABC.mp4");
        request.setMediaWorkflowId("829bed0300994057a49e4f16de957e34");
        try {
            AddMediaResponse response = aliyunClient.getAcsResponse(request);
            System.out.println(JSONObject.toJSONString(response));
        } catch (ServerException e) {
            System.out.println("Code:" + e.getErrCode() + " Msg:" + e.getMessage());
        } catch (ClientException e) {
            System.out.println("Code:" + e.getErrCode() + " Msg:" + e.getMessage());
        }
    }
}
