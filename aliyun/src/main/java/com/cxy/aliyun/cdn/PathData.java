package com.cxy.aliyun.cdn;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cdn.model.v20141111.DescribeDomainPathDataRequest;
import com.aliyuncs.cdn.model.v20141111.DescribeDomainPathDataResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.cxy.aliyun.config.AliConfig;

public class PathData {

    public static void main(String[] args) {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(AliConfig.region, AliConfig.accessKeyId, AliConfig.accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        DescribeDomainPathDataRequest request = new DescribeDomainPathDataRequest();
        request.setDomainName("1001.love-kb.com");
        request.setStartTime("2018-04-03T00:00:00Z");
        request.setEndTime("2018-04-03T01:20:00Z");

        try {
            DescribeDomainPathDataResponse acsResponse = client.getAcsResponse(request);

            System.out.println(acsResponse.getTotalCount());
            System.out.println(acsResponse.getPathDataPerInterval());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
