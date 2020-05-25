package com.ms.common.redis.bearer;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ms.base.bean.login.LoginErrorCode;
import com.ms.base.bean.login.LoginUser;
import com.ms.common.constant.AuthenticationConstants;
import com.ms.common.redis.RedisCacheUtil;
import com.ms.exception.base.CustomException;

public class UserBearerCacheService {
	private Logger logger = LoggerFactory.getLogger(UserBearerCacheService.class);

	@Value("${redis.bearer.timeout}")
	private long bearerTimeout;

	@Autowired
	private RedisCacheUtil<?> redisCacheUtil;

	private static final String bearerKeyPrefix = "bearer-user-";

	public void saveAccount(LoginUser loginUser) throws CustomException {
		String bearer = loginUser.getBearer();
		try {
			redisCacheUtil.setCacheObject(bearerKeyPrefix + bearer, loginUser, bearerTimeout, TimeUnit.MINUTES);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(LoginErrorCode.LOGIN_FAILED.getCode(),
					LoginErrorCode.LOGIN_FAILED.getDescription());
		}
	}

	public void deleteAccount(String bearer) throws CustomException {
		try {
			redisCacheUtil.delete(bearerKeyPrefix + bearer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException(LoginErrorCode.LOGIN_FAILED.getCode(),
					LoginErrorCode.LOGIN_FAILED.getDescription());
		}
	}

	public LoginUser getLoginUser(HttpServletRequest request) {
		String bearer = getBearer(request);
		LoginUser loginUser = redisCacheUtil.getCacheObject(bearerKeyPrefix + bearer);
		return loginUser;
	}

	public String getBearer(HttpServletRequest request) {
		String bearer = request.getParameter(AuthenticationConstants.authcScheme);
		if (StringUtils.isNotEmpty(bearer)) {
			return bearer;
		}
		String authzHeader = request.getHeader(AuthenticationConstants.AUTHORIZATION_HEADER);
		if (authzHeader == null || !authzHeader.startsWith(AuthenticationConstants.authzScheme)) {
			return null;
		}
		String[] authTokens = authzHeader.split(" ");
		if (authTokens == null || authTokens.length < 2) {
			return null;
		}
		return authTokens[1];
	}

}
