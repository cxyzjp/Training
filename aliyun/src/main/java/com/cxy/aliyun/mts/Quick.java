package com.cxy.aliyun.mts;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.mts.model.v20140618.SearchPipelineRequest;
import com.aliyuncs.mts.model.v20140618.SearchPipelineResponse;
import com.aliyuncs.profile.DefaultProfile;

public class Quick {
    private static String accessKeyId = "LTAID4ynYiSomloM";
    private static String accessKeySecret = "6hx7qnM9D5NW1RukewK2raeoapuJIk";
    private static String[] mpsRegionIds = new String[]{
            "cn-hangzhou"
//            , "cn-beijing", "cn-shenzhen", "cn-shanghai",
//            "cn-hongkong", "us-west-1", "ap-southeast-1", "ap-northeast-1",
//            "eu-central-1", "ap-south-1"
    };

    public static void main(String[] args) {
        for (String mpsRegionId : mpsRegionIds) {
            System.out.println("region id is:" + mpsRegionId);
            // 创建DefaultAcsClient实例并初始化
            DefaultProfile profile = DefaultProfile.getProfile(
                    mpsRegionId,      // 地域ID
                    accessKeyId,      // RAM账号的AccessKey ID
                    accessKeySecret); // RAM账号Access Key Secret
            IAcsClient client = new DefaultAcsClient(profile);
            // 创建API请求并设置参数
            SearchPipelineRequest request = new SearchPipelineRequest();
            // 发起请求并处理应答或异常
            SearchPipelineResponse response;
            try {
                response = client.getAcsResponse(request);
                System.out.println("PipelineName is:" + response.getPipelineList().get(0).getName());
                System.out.println("PipelineId is:" + response.getPipelineList().get(0).getId());
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
    }
}