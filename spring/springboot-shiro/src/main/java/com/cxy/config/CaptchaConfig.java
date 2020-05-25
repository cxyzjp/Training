package com.cxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.base.utils.captcha.CaptchaCacheService;

/**
 * 验证码
 */
@Configuration
public class CaptchaConfig {

	@Bean
	public CaptchaCacheService captchaCacheService() {
		return new CaptchaCacheService("captcha-admin-");
	}

}
