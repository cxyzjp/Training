package com.cxy.aliyun.mts;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.mts.model.v20140618.SubmitJobsRequest;
import com.aliyuncs.mts.model.v20140618.SubmitJobsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.cxy.aliyun.config.AliConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleTranscode {

    private static String templateId_mp4 = "S00000001-200030";
    private static String templateId_hls = "S00000001-100030";

    public static void main(String[] args) {

    }

    public String transcode(String userSn, String ossInputObject) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(new Date());
        String ossFileName = ossInputObject.substring(ossInputObject.lastIndexOf("/") + 1, ossInputObject.lastIndexOf("."));

        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(AliConfig.region, AliConfig.accessKeyId, AliConfig.accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        SubmitJobsRequest request = new SubmitJobsRequest();
        request.setOutputLocation(AliConfig.ossLocation);
        request.setOutputBucket(AliConfig.bucketName);
        request.setPipelineId(AliConfig.pipelineId);
        try {
            // Input
            JSONObject input = new JSONObject();
            input.put("Location", AliConfig.ossLocation);
            input.put("Bucket", AliConfig.bucketName);
            input.put("Object", URLEncoder.encode(ossInputObject, "utf-8"));
            request.setInput(input.toJSONString());

            // Output
            String mp4Path = userSn + "/v/t/mp4/" + date + "/" + ossFileName;
            JSONObject mp4 = new JSONObject();
            mp4.put("OutputObject", URLEncoder.encode(mp4Path, "utf-8"));
            mp4.put("TemplateId", templateId_mp4);
            String hlsPath = userSn + "/v/t/hls/" + date + "/" + ossFileName;
            JSONObject hls = new JSONObject();
            hls.put("OutputObject", URLEncoder.encode(hlsPath, "utf-8"));
            hls.put("TemplateId", templateId_hls);

            JSONArray outputs = new JSONArray();
            outputs.add(mp4);
            outputs.add(hls);
            request.setOutputs(outputs.toJSONString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("input URL encode failed");
        }

        try {
            // 发起请求并处理应答或异常
            SubmitJobsResponse response = client.getAcsResponse(request);
            if (response.getJobResultList().get(0).getSuccess()) {
                return response.getJobResultList().get(0).getJob().getJobId() + "," +
                        response.getJobResultList().get(1).getJob().getJobId();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "";
    }
}