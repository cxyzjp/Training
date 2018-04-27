package com.cxy.aliyun.controller;

import com.cxy.aliyun.config.AliConfig;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/sign")
public class SignController {
    private static final String ENCODE_TYPE = "UTF-8";
    private static final String ALGORITHM = "HmacSHA1";
    private static final String HTTP_METHOD = "GET";
    private static final String SEPARATOR = "&";
    private static final String EQUAL = "=";
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @PostMapping
    public String sign(){

        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {

        String keyId = AliConfig.accessKeyId;
        String keySecret = AliConfig.accessKeySecret;
        String action = "PutObject";

        Map<String, String> parameterMap = new HashMap<>();
        // 加入请求公共参数
        parameterMap.put("Action", action);
        parameterMap.put("Version", "2014-06-18");
        parameterMap.put("AccessKeyId", keyId);
        parameterMap.put("Timestamp", formatIso8601Date(new Date()));
        parameterMap.put("SignatureMethod", "HMAC-SHA1");
        parameterMap.put("SignatureVersion", "1.0");
        parameterMap.put("SignatureNonce", UUID.randomUUID().toString());
        parameterMap.put("Format", "JSON");

        // 加入方法特有参数
//        parameterMap.put("JobIds", "e7c4e89b5140486ebe0f52080a721f34,ec5df8d40a214b9b931240a9b38d5007");

        sign(keySecret, parameterMap);
    }

    private static void sign(String keySecret, Map<String, String> parameterMap) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException  {
        String canonicalizedQueryString = buildCanonicalizedQueryString(parameterMap);
        System.out.println("CanonicalizedQueryString:" + canonicalizedQueryString);

        String stringToSign = buildStringToSign(canonicalizedQueryString);
        System.out.println("StringToSign:" + stringToSign);

        String signature = buildSignature(keySecret, stringToSign);
        System.out.println("Signature:" + signature);

        String requestURL = buildRequestURL(signature, parameterMap);
        System.out.println("Request URL:" + requestURL);
    }

    private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    private static String percentEncode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, ENCODE_TYPE).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    private static String buildCanonicalizedQueryString(Map<String, String> parameterMap) throws UnsupportedEncodingException {
        // 对参数进行排序
        List<String> sortedKeys = new ArrayList<>(parameterMap.keySet());
        Collections.sort(sortedKeys);

        StringBuilder temp = new StringBuilder();
        for (String key : sortedKeys) {
            // 此处需要对key和value进行编码
            String value = parameterMap.get(key);
            temp.append(SEPARATOR).append(percentEncode(key)).append(EQUAL).append(percentEncode(value));
        }
        return temp.toString().substring(1);
    }

    private static String buildStringToSign(String canonicalizedQueryString) throws UnsupportedEncodingException {
        // 生成stringToSign字符
        // 此处需要对canonicalizedQueryString进行编码
        return HTTP_METHOD + SEPARATOR +
                percentEncode("/") + SEPARATOR +
                percentEncode(canonicalizedQueryString);
    }

    private static String buildSignature(String keySecret, String stringToSign) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        SecretKey key = new SecretKeySpec((keySecret + SEPARATOR).getBytes(ENCODE_TYPE), SignatureMethod.HMAC_SHA1);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(key);
        byte[] hashBytes = mac.doFinal(stringToSign.getBytes(ENCODE_TYPE));
        byte[] base64Bytes = new Base64().encode(hashBytes);
        String base64UTF8String = new String(base64Bytes, "utf-8");

        return URLEncoder.encode(base64UTF8String, ENCODE_TYPE);
    }

    private static String buildRequestURL(String signature, Map<String, String> parameterMap) throws UnsupportedEncodingException {
        // 生成请求URL
        StringBuilder temp = new StringBuilder("http://mts.cn-hangzhou.aliyuncs.com/?");
        temp.append(URLEncoder.encode("Signature", ENCODE_TYPE)).append("=").append(signature);
        for (Map.Entry<String, String> e : parameterMap.entrySet()) {
            temp.append("&").append(percentEncode(e.getKey())).append("=").append(percentEncode(e.getValue()));
        }
        return temp.toString();
    }
}
