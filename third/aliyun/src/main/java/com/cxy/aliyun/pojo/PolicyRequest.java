package com.cxy.aliyun.pojo;

import java.io.Serializable;

public class PolicyRequest implements Serializable {
    private static final long serialVersionUID = 7106996467392858727L;

    /** 1：视频，2：音频，3：图片 */
    private String materialType;

    /** 原始文件名 */
    private String originFileName;

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }
}
