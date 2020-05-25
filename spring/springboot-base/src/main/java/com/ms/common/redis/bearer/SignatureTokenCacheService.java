package com.ms.common.redis.bearer;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ms.base.bean.login.LoginErrorCode;
import com.ms.common.redis.RedisCacheUtil;
import com.ms.exception.base.CustomException;

public class SignatureTokenCacheService {
	private Logger logger = LoggerFactory.getLogger(SignatureTokenCacheService.class);

	@Value("${redis.signature-token.timeout}")
	private long tokenTimeout;
	
	public static final String SignatureToken = "SignatureToken";
	public static final String ResponseToken = "signaturetoken";
	public static final String TokenScheme = "Token";
	
	@Autowired
	private RedisCacheUtil<?> redisCacheUtil;

	private static final String bearerKeyPrefix = "signature-token-";

	public void saveToken(String token) throws CustomException {
		try {
			redisCacheUtil.setCacheObject(bearerKeyPrefix + token, token, tokenTimeout, TimeUnit.MINUTES);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(LoginErrorCode.LOGIN_FAILED.getCode(),
					LoginErrorCode.LOGIN_FAILED.getDescription());
		}
	}

	public void deleteToken(String token) throws CustomException {
		try {
			redisCacheUtil.delete(bearerKeyPrefix + token);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(LoginErrorCode.LOGIN_FAILED.getCode(),
					LoginErrorCode.LOGIN_FAILED.getDescription());
		}
	}

	public String getToken(HttpServletRequest request) {
		String token = request.getParameter(SignatureToken);
		if (StringUtils.isEmpty(token)) {
			String authzHeader = request.getHeader(SignatureToken);
			if (authzHeader == null || !authzHeader.startsWith(TokenScheme)) {
				return null;
			}
			String[] authTokens = authzHeader.split(" ");
			if (authTokens == null || authTokens.length < 2) {
				return null;
			}
			token = authTokens[1];
		}
		return redisCacheUtil.getCacheObject(bearerKeyPrefix + token);
	}

}
