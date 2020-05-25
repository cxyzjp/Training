package com.ms.base.utils.captcha;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ms.common.redis.RedisCacheUtil;
import com.ms.exception.base.BaseErrorCode;
import com.ms.exception.base.CustomException;

/**
 * 验证码 缓存、验证
 */
public class CaptchaCacheService {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaCacheService.class);
	
	/** redis key前缀 */
	private String captchaPrefix = "captcha-";
	/** 验证码过期时间 分钟 */
	private long captchaTimeout = 3;
	
	@Autowired
	private RedisCacheUtil<?> redisCacheUtil;
	
	public CaptchaCacheService(){
	}
	public CaptchaCacheService(String captchaPrefix){
		this.captchaPrefix = captchaPrefix;
	}
	public CaptchaCacheService(String captchaPrefix, long captchaTimeout){
		this.captchaPrefix = captchaPrefix;
		this.captchaTimeout = captchaTimeout;
	}
	
	public void saveCode(String sn, String code) throws CustomException {
		try {
			redisCacheUtil.setCacheObject(captchaPrefix + sn, code, captchaTimeout, TimeUnit.MINUTES);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(BaseErrorCode.SERVER_EXCEPTION.code(),
					BaseErrorCode.SERVER_EXCEPTION.getDescription());
		}
	}
	
	public String getCode(String sn) throws CustomException {
		try {
			String code = redisCacheUtil.getCacheObject(captchaPrefix + sn);
			redisCacheUtil.delete(captchaPrefix + sn);
			return code;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(BaseErrorCode.SERVER_EXCEPTION.code(),
					BaseErrorCode.SERVER_EXCEPTION.getDescription());
		}
	}
	
}
