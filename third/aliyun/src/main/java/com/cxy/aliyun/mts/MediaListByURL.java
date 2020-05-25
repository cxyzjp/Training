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
        String accessKeyId = "STS.GobpatpV82mJn7xrj71wg7VYA";
        String accessKeySecret = "E5NAqWbHEwdZhqYmgxmjaU6sFT3fk8ccjSP1ixeZDCfg";
        String securityToken = "CAISpQJ1q6Ft5B2yfSjIpqzXO9vAnYkZha+hbBHJlmpiPfhL2JPyozz2IHlNeHRvBesdsf82mG1U7PgdlqN2RpNETHvZdsZqti/hE4VmJ9ivgde8yJBZonXMewHKeQ2ZsebWZ+LmNqS/Ht6md1HDkAJq3LL+bk/Mdle5MJqP+/EFA9MMRVv6FwMkYu1bPQx/ssQXGGLMPPK2SH7Qj3HXEVBjt3gh6xN24r/txdaHuFiMzg/xyvIcoYj4Jd2laZAOTZ50SIWyx/ckNPiDgiVQ9hFM+K5xyatZ5W7lxojGWgYNuUzWa7WJroAwcVZDC/JkS/Ienp/VjuZlv+HfrYPzxitWMPtdOyalH9n5nJabRLjyaY1gKeunYSqSyL2KO5X0uhPb0vSl3q2JERqAAZzJA9csmdfagOYIDDFHc62shNIGZh70XIj0tjLH0N1udiX9374lbV41mDAICGoZTFxrfYS7viQj/ZF178IX0Jj8HA9X3GFvCF9vEKtVUYNAMH9iW7RzE9qjnG01ooMK5G/jEOK8iBJeNSIMZyHrgQzpjdOmuxMT5rDaiRdS59cz";
        DefaultProfile profile = DefaultProfile.getProfile(AliConfig.region, accessKeyId, accessKeySecret, securityToken);

//        DefaultProfile profile = DefaultProfile.getProfile(AliConfig.region, AliConfig.accessKeyId, AliConfig.accessKeySecret);
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
