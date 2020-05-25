package com.ms.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.ms.base.bean.login.LoginUser;
import com.ms.common.redis.bearer.UserBearerCacheService;
import com.ms.exception.base.BaseErrorCode;
import com.ms.exception.base.CustomException;

public class BaseController {
	
	@Autowired
	private UserBearerCacheService bearerCacheService;

	protected Long getUserSn(HttpServletRequest request) throws CustomException {
		LoginUser loginUser = getLoginUser(request);
		return loginUser.getUserSn();
	}

	protected String getAccessKeyId(HttpServletRequest request) throws CustomException {
		LoginUser loginUser = getLoginUser(request);
		return loginUser.getAccessKeyId();
	}

	protected LoginUser getLoginUser(HttpServletRequest request) throws CustomException {
		LoginUser loginUser = bearerCacheService.getLoginUser(request);
		if (loginUser == null) {
			throw new CustomException(BaseErrorCode.NOT_AUTHORIZATION.code(), 
					BaseErrorCode.NOT_AUTHORIZATION.getDescription());
		}
		return loginUser;
	}
	
	protected String getBearer(HttpServletRequest request) {
		return bearerCacheService.getBearer(request);
	}
}
