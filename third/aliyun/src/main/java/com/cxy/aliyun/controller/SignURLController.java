package com.cxy.aliyun.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.cxy.aliyun.config.AliConfig;
import com.cxy.aliyun.pojo.PolicyRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/sign/url")
public class SignURLController {
    private static final String ENCODE_TYPE = "UTF-8";
    private static final String ALGORITHM = "HmacSHA1";
    private static final String HTTP_METHOD = "GET";
    private static final String SEPARATOR = "&";
    private static final String EQUAL = "=";
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @GetMapping("/sign")
    public PolicyRequest sign(){
        PolicyRequest request = new PolicyRequest();
        request.setMaterialType("888888");
        return request;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        OSS client = AliConfig.ossClient();

        Date expires = new Date (new Date().getTime() + 1000 * 600);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest("chuandao-out1", "1001/201803/hls/a/");
        generatePresignedUrlRequest.setExpiration(expires);
        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);

        System.out.println(url.toString());
    }

}
