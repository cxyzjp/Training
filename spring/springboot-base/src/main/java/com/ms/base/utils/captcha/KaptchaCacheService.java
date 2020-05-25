package com.ms.base.utils.captcha;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ms.common.redis.RedisCacheUtil;
import com.ms.exception.base.BaseErrorCode;
import com.ms.exception.base.CustomException;

/**
 *  需要添加pom依赖
	<dependency>  
	    <groupId>com.github.penggle</groupId>  
	    <artifactId>kaptcha</artifactId>  
	    <version>2.3.2</version>  
	    <exclusions>  
	        <exclusion>  
	            <artifactId>javax.servlet-api</artifactId>  
	            <groupId>javax.servlet</groupId>  
	        </exclusion>  
	    </exclusions>  
	</dependency>
 * 
 * 验证码 缓存、验证
 */
public class KaptchaCacheService {
	private static final Logger logger = LoggerFactory.getLogger(KaptchaCacheService.class);
	
	private String kaptchaPrefix = "kaptcha-";
	/** 验证码过期时间 分钟 */
	private long kaptchaTimeout = 3;
	
	@Autowired
	private RedisCacheUtil<?> redisCacheUtil;
	
	
	public KaptchaCacheService(){
		
	}
	public KaptchaCacheService(String kaptchaPrefix){
		this.kaptchaPrefix = kaptchaPrefix;
	}
	public KaptchaCacheService(String kaptchaPrefix, long kaptchaTimeout){
		this.kaptchaPrefix = kaptchaPrefix;
		this.kaptchaTimeout = kaptchaTimeout;
	}
	
	public void saveCode(String sn, String code) throws CustomException {
		try {
			redisCacheUtil.setCacheObject(kaptchaPrefix + sn, code, kaptchaTimeout, TimeUnit.MINUTES);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(BaseErrorCode.SERVER_EXCEPTION.code(),
					BaseErrorCode.SERVER_EXCEPTION.getDescription());
		}
	}
	
	public String getCode(String sn) throws CustomException {
		try {
			String code = redisCacheUtil.getCacheObject(kaptchaPrefix + sn);
			redisCacheUtil.delete(kaptchaPrefix + sn);
			return code;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(BaseErrorCode.SERVER_EXCEPTION.code(),
					BaseErrorCode.SERVER_EXCEPTION.getDescription());
		}
	}
	
}
