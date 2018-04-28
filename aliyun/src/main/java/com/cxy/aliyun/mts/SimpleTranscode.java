package com.cxy.aliyun.mts;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.mts.model.v20140618.SubmitJobsRequest;
import com.aliyuncs.mts.model.v20140618.SubmitJobsResponse;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.cxy.aliyun.config.AliConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SimpleTranscode {

    private static String templateId_mp4 = "S00000001-200030";
    private static String templateId_hls = "S00000001-100030";

    public static void main(String[] args) {

    }

    public String transcodeJob(String userSn, String ossInputObject) {
        String dateAndFileName = ossInputObject.substring(ossInputObject.indexOf("/v/o/") + 5, ossInputObject.lastIndexOf("."));
        String fileName = ossInputObject.substring(ossInputObject.lastIndexOf("/") + 1, ossInputObject.lastIndexOf("."));

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
            String mp4Path = userSn + "/v/t/mp4/" + dateAndFileName + ".mp4";
            JSONObject mp4 = new JSONObject();
            mp4.put("OutputObject", URLEncoder.encode(mp4Path, "utf-8"));
            mp4.put("TemplateId", templateId_mp4);
            String hlsPath = userSn + "/v/t/hls/" + dateAndFileName + "/" + fileName;
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
            SubmitJobsResponse response = getClient().getAcsResponse(request);
            if (response.getJobResultList().get(0).getSuccess()) {
                return response.getJobResultList().get(0).getJob().getJobId() + "," +
                        response.getJobResultList().get(1).getJob().getJobId();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void snapshotJob(String userSn, String ossInputObject) {
        String dateAndFileName = ossInputObject.substring(ossInputObject.indexOf("/v/o/") + 5, ossInputObject.lastIndexOf("."));
        String ossOutObject = userSn + "/v/t/mp4/" + dateAndFileName + ".jpg";

        try {
            SubmitSnapshotJobRequest jobRequest = new SubmitSnapshotJobRequest();
            JSONObject input = new JSONObject();
            input.put("Location", AliConfig.ossLocation);
            input.put("Bucket", AliConfig.bucketName);
            input.put("Object", URLEncoder.encode(ossInputObject, "utf-8"));
            jobRequest.setInput(input.toString());

            JSONObject snapshotConfig = new JSONObject();
            JSONObject output = new JSONObject();
            output.put("Location", AliConfig.ossLocation);
            output.put("Bucket", AliConfig.bucketName);
            output.put("Object", URLEncoder.encode(ossOutObject, "utf-8"));
            snapshotConfig.put("OutputFile", output);
            snapshotConfig.put("Time", "1");
            snapshotConfig.put("Num", "1");
            jobRequest.setSnapshotConfig(snapshotConfig.toString());

            jobRequest.setPipelineId(AliConfig.pipelineId);
            getClient().getAcsResponse(jobRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private IAcsClient getClient() {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(AliConfig.region, AliConfig.accessKeyId, AliConfig.accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}