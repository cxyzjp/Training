package com.cxy.aliyun.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.cxy.aliyun.config.AliConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aliyun/sts")
public class STSController {

    @GetMapping
    public AssumeRoleResponse.Credentials getSTS() throws Exception{
        IClientProfile profile = DefaultProfile.getProfile(AliConfig.region, AliConfig.m_accessKeyID, AliConfig.m_accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        AssumeRoleResponse response = assumeRole(client, AliConfig.m_roleArn);
        AssumeRoleResponse.Credentials credentials = response.getCredentials();
        System.out.println(credentials.getAccessKeyId() + "\n" +
                credentials.getAccessKeySecret() + "\n" +
                credentials.getSecurityToken() + "\n" +
                credentials.getExpiration());
        return credentials;
    }

    private static AssumeRoleResponse assumeRole(DefaultAcsClient client, String roleArn)
            throws ClientException {
        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion("2015-04-01");
        request.setMethod(MethodType.POST);
        request.setProtocol(ProtocolType.HTTPS);
        request.setDurationSeconds(3600L);
        request.setRoleArn(roleArn);
        request.setRoleSessionName(AliConfig.m_roleSessionName);
        return client.getAcsResponse(request);
    }

    public static void main(String[] args) {
        STSController s = new STSController();
        try {
            s.getSTS();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
