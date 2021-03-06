package com.cxy.aliyun.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
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
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/oss/policy")
public class PolicyController {

    @PostMapping
    public Map<String, String> doGet(@RequestBody PolicyRequest policyRequest)
            throws ServletException, IOException {
        Long userSn = 23333L;
        String originFileName = policyRequest.getOriginFileName();
        String materialType = policyRequest.getMaterialType();
        String dir;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(new java.util.Date());
        // 1：视频，2：音频，3：图片
        if ("1".equals(materialType)) {
            dir = userSn + "/v/o/" + date + "/";
        } else if ("2".equals(materialType)) {
            dir = userSn + "/a/" + date + "/";
        } else if ("3".equals(materialType)) {
            dir = userSn + "/p/" + date + "/";
        } else {
            return null;
        }

        OSS client = new OSSClientBuilder().build(AliConfig.endpoint, AliConfig.accessKeyId, AliConfig.accessKeySecret);
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
            respMap.put("accessid", AliConfig.accessKeyId);
            respMap.put("host", AliConfig.host);
            respMap.put("dir", dir);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            String oFile = URLEncoder.encode(originFileName, "utf-8");
            JSONObject callback = new JSONObject();
            callback.put("callbackUrl", "http://dev.love-kb.com/aliyun/oss/callback");
            callback.put("callbackBody", "{\"object\":${object},\"size\":${size},\"userSn\":\"" + userSn +
                    "\",\"materialType\":\"" + materialType + "\",\"originFileName\":\"" + oFile + "\"}");
            callback.put("callbackBodyType", "application/json");
            BASE64Encoder encoder = new BASE64Encoder();
            respMap.put("callback", encoder.encode(callback.toString().getBytes()));
            return respMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
