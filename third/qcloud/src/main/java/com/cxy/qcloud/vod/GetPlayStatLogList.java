package com.cxy.qcloud.vod;

import com.cxy.qcloud.config.QcloudConfig;
import com.qcloud.Module.Vod;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Utilities.Json.JSONObject;

import java.util.TreeMap;

public class GetPlayStatLogList {

    public static void main(String[] args) {

        /* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
        TreeMap<String, Object> config = new TreeMap<>();
        config.put("SecretId", QcloudConfig.SecretId);
        config.put("SecretKey", QcloudConfig.SecretKey);
        /* 请求方法类型 POST、GET */
        config.put("RequestMethod", "GET");
        /* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
//        config.put("DefaultRegion", "gz");

        // 你将要使用接口所在的模块，可以从 官网->云 API 文档->XXXX 接口->接口描述->域名中获取，比如域名：cvm.api.qcloud.com，module 就是 new Cvm()。

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> params = new TreeMap<>();
        params.put("from", "2018-03-10");
        params.put("to", "2018-03-11");
        // 在这里指定所要用的签名算法，不指定默认为 HmacSHA1*/
//        params.put("SignatureMethod", "HmacSHA256");

        // generateUrl 方法生成请求串,可用于调试使用
        System.out.println("===url: " + module.generateUrl("GetPlayStatLogList", params));
        String result;
        try {
            // call 方法正式向指定的接口名发送请求，并把请求参数 params 传入，返回即是接口的请求结果。
            result = module.call("GetPlayStatLogList", params);
            JSONObject json_result = new JSONObject(result);
            System.out.println("===json_result: " + json_result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }

    }
}
