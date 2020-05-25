package com.cxy.aliyun.mts;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.mts.model.v20140618.ListMediaWorkflowExecutionsRequest;
import com.aliyuncs.mts.model.v20140618.ListMediaWorkflowExecutionsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.cxy.aliyun.config.AliConfig;

import java.util.List;

/**
 * 媒体工作流
 */
public class WorkflowId {

    public static void main(String[] args) {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(AliConfig.region, AliConfig.accessKeyId, AliConfig.accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        ListMediaWorkflowExecutionsRequest request = new ListMediaWorkflowExecutionsRequest();

        try {
            request.setInputFileURL("http://chuandao-in1.oss-cn-hangzhou.aliyuncs.com/1001/201803/h.mp4");
            while (true) {
                ListMediaWorkflowExecutionsResponse response = client.getAcsResponse(request);

                List<ListMediaWorkflowExecutionsResponse.MediaWorkflowExecution> mediaWorkflowExecutionList = response.getMediaWorkflowExecutionList();
                if (mediaWorkflowExecutionList != null && mediaWorkflowExecutionList.size() > 0) {
                    ListMediaWorkflowExecutionsResponse.MediaWorkflowExecution mediaWorkflowExecution = mediaWorkflowExecutionList.get(0);
                    String state = mediaWorkflowExecution.getState();
                    System.out.println(mediaWorkflowExecution.getMediaId() + " " + state);
                    if ("Completed".equalsIgnoreCase(state)) {
                        break;
                    }
                }
                Thread.sleep(1000);
            }
            System.out.println("stop");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
