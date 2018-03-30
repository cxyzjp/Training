package com.cxy.aliyun.mts;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.mts.model.v20140618.QueryMediaListByURLRequest;
import com.aliyuncs.mts.model.v20140618.QueryMediaListByURLResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.cxy.aliyun.config.AliConfig;

import java.util.List;

public class MediaListByURL {

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile(AliConfig.region, AliConfig.accessKeyId, AliConfig.accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        QueryMediaListByURLRequest request = new QueryMediaListByURLRequest();
        request.setFileURLs("http://chuandao-in1.oss-cn-hangzhou.aliyuncs.com/1001/201803/b.mp4");
        request.setIncludeMediaInfo(true);
        request.setIncludePlayList(true);
        try {
            QueryMediaListByURLResponse acsResponse;
            int i = 1;
            while (true) {
                acsResponse = client.getAcsResponse(request);
                List<QueryMediaListByURLResponse.Media> mediaList = acsResponse.getMediaList();
                if (mediaList != null && mediaList.size() > 0) {
                    QueryMediaListByURLResponse.Media media = mediaList.get(0);
                    String state = media.getFile().getState();
                    String publishState = media.getPublishState();
                    System.out.println(state + " " + publishState);

                    // 已经发布
                    if ("Published".equalsIgnoreCase(publishState)) {
                        break;
                    }
                }
                System.out.println(i++);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
