package com.cxy.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.cxy.aliyun.config.AliConfig;

public class ObjectsSTS {

    public static void main(String[] args) {
        String accessKeyId = "STS.DjuMGDP6LKtk3ZVReW9aQe2aG";
        String accessKeySecret = "3NsF8if3GrSzRKpNhexXxQi4U6KVrnH7bVK3HNMXZGC1";
        String securityToken = "CAISrwJ1q6Ft5B2yfSjIpanABv3wvelt/LaAMXzntmUCNe59ivfKpTz2IHlNeHRvBesdsf82mG1U7PgdlqN2RpNETHvZdsZqth3VTYZmJ9ivgde8yJBZon/MewHKefKSvqL7Z+H+U6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj+wIDLkQRRLqL0B0ZrFsKxBltdUROFbIKP+pKWSKuGfLC1dysQcO3wEL4K+kkMqH8Uic3h+owe8IroL9K5/8No5tOo9iT7XD1edtJK3ay3wSuVoY/6drhapI8DCf55acBUFM/1LEEZKNqYQwcFUgNvBlRvIZ86Wlr5Ai5LyPzbaQ4g1WIORYXx7YQI2d28beEIurTI1gLOqmZCiWiY/Ub8Wo71l6Pmh2MQ5GetMx1cz2t/aCiVsagAE7axSPDPXTMBdAXZqN/JQ06QX8HjLj+jdAfQK59vrwOR8dRQ/h3MvWlZTXgXz4fd1haTjk1iV3essJd+1NM+AZMyAK8hL2/gkNNEhJbDbZXoYfiQL8q+LZ7H/Y5naNb86CSrN/zYtb9nkBJI17t9rzv008kDShWRzsFSjkS515aw==";
        OSS client = new OSSClientBuilder().build(AliConfig.endpoint, accessKeyId, accessKeySecret, securityToken);
        String bucketName = AliConfig.bucketName;

        ObjectListing objectListing = client.listObjects(bucketName, "input/");
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey()+ " = " +objectSummary.getSize());
        }
    }

}
