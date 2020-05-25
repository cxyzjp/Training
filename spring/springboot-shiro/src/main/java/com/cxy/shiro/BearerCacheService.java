package com.cxy.shiro;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cxy.exception.CustomException;
import com.ms.base.bean.login.LoginErrorCode;
import com.ms.base.bean.login.LoginUser;
import com.ms.common.constant.AuthenticationConstants;
import com.ms.common.redis.RedisCacheUtil;

@Service
public class BearerCacheService {
	private Logger logger = LoggerFactory.getLogger(BearerCacheService.class);

	@Value("${redis.bearer.timeout}")
	private long bearerTimeout;

	@Autowired
	private RedisCacheUtil<?> redisCacheUtil;

	private static final String bearerKeyPrefix = "bearer-admin-";

	public void saveAccount(LoginUser loginUser) throws CustomException {
		String bearer = loginUser.getBearer();
		SimpleAccount account = new SimpleAccount(loginUser, bearer, "bearer-realm-admin");
		saveAccount(bearer, account);
	}

	public void saveAccount(String bearer, SimpleAccount account) throws CustomException {
		try {
			redisCacheUtil.setCacheObject(bearerKeyPrefix + bearer, account, bearerTimeout, TimeUnit.MINUTES);
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
		LoginUser loginUser = null;
		String bearer = getBearer(request);
		loginUser = getLoginUser(bearer);
		return loginUser;
	}

	private LoginUser getLoginUser(String bearer) {
		LoginUser loginUser = null;

		SimpleAccount simpleAccount = getAccount(bearer);
		loginUser = (LoginUser) simpleAccount.getPrincipals().getPrimaryPrincipal();

		return loginUser;
	}

	public String getBearer(HttpServletRequest request) {
		String bearer = request.getParameter(AuthenticationConstants.authcScheme);
		if (StringUtils.isNotEmpty(bearer)) {
			return bearer;
		}
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		String authzHeader = httpRequest.getHeader(AuthenticationConstants.AUTHORIZATION_HEADER);
		if (authzHeader == null || !authzHeader.startsWith(AuthenticationConstants.authzScheme)) {
			return null;
		}
		String[] authTokens = authzHeader.split(" ");
		if (authTokens == null || authTokens.length < 2) {
			return null;
		}
		return authTokens[1];
	}

	public SimpleAccount getAccount(String bearer) {

		SimpleAccount account = redisCacheUtil.getCacheObject(bearerKeyPrefix + bearer);
		return account;
	}

}
