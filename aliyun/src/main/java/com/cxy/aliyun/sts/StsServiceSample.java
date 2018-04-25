package com.cxy.aliyun.sts;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.cxy.aliyun.config.AliConfig;

public class StsServiceSample {
    public static void main(String[] args) {
        String policy = "{\"Version\":\"1\",\"Statement\":[{\"Effect\":\"Allow\",\"Action\":[\"oss:*\"],\"Resource\":[\"acs:oss:*:*:chuandao-in1\",\"acs:oss:*:*:chuandao-out1\"],\"Condition\":{}}]}";
        try {
            // Init ACS Client
            DefaultProfile.addEndpoint("", "", "Sts", "sts.aliyuncs.com");
//            DefaultProfile.addEndpoint(AliConfig.endpoint, AliConfig.region, "Sts", "sts.aliyuncs.com");
            IClientProfile profile = DefaultProfile.getProfile("", AliConfig.m_accessKeyID, AliConfig.m_accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(AliConfig.m_roleArn);
            request.setRoleSessionName(AliConfig.m_roleSessionName);
            request.setPolicy(policy);
            final AssumeRoleResponse response = client.getAcsResponse(request);

            System.out.println("Expiration: " + response.getCredentials().getExpiration());
            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
            System.out.println("RequestId: " + response.getRequestId());
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
    }
}