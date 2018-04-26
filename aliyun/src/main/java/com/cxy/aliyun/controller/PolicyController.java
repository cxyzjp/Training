package com.cxy.aliyun.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.cxy.aliyun.config.AliConfig;
import com.cxy.aliyun.pojo.PolicyRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/oss/policy")
public class PolicyController {

    @PostMapping
    public Map<String, String> doGet(@RequestBody PolicyRequest policyRequest)
            throws ServletException, IOException {
        String endpoint = AliConfig.endpoint;
        String accessId = AliConfig.accessKeyId;
        String accessKey = AliConfig.accessKeySecret;
        String bucket = AliConfig.bucketName;
        String dir = policyRequest.getDir();
//        String accessId = AliConfig.accessKeyId;
//        String accessKey = AliConfig.accessKeySecret;
//        String bucket = AliConfig.bucketName;
//        String dir = policyRequest.getDir();
        String host = "http://" + bucket + "." + endpoint;
//        String host = "http://chuandao01.oss-cn-hangzhou.aliyuncs.com";
//        String host = "http://rad.oss-cn-hangzhou.aliyuncs.com";
        OSS client = new OSSClientBuilder().build(endpoint,accessId,accessKey);
//        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try {
            long expireTime = 300;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            JSONObject callback = new JSONObject();
            callback.put("callbackUrl", "http://dev.love-kb.com/aliyun/oss/callback");
//            callback.put("callbackBody","{\"bucket\":${bucket},\"object\":${object},\"uid\":\"2333333\"}");
            callback.put("callbackBody", "{\"bucket\":${bucket},\"object\":${object}}");
            callback.put("callbackBodyType", "application/json");

            BASE64Encoder encoder = new BASE64Encoder();
            respMap.put("callback", encoder.encode(callback.toString().getBytes()));
            return respMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/api")
    public Map<String, String> getOSSPolicy(@RequestBody PolicyRequest policyRequest) {
        String endpoint = AliConfig.endpoint;
        String accessId = AliConfig.accessKeyId;
        String accessKey = AliConfig.accessKeySecret;
        String bucket = AliConfig.bucketName;
        String dir = policyRequest.getDir();

        String host = "http://" + bucket + "." + endpoint;
        long expireEndTime = System.currentTimeMillis() + 300 * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

//        OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = null;
        try {
            binaryData = postPolicy.getBytes("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<>();
        respMap.put("accessid", accessId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        return respMap;
    }
}
