package com.ms.common.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ms.base.bean.login.LoginUser;
import com.ms.common.redis.bearer.UserBearerCacheService;
import com.ms.exception.base.BaseErrorCode;
import com.ms.exception.base.CustomException;

public class UserBearerAuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserBearerCacheService bearerCacheService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		LoginUser loginUser = bearerCacheService.getLoginUser(request);
		if(loginUser != null){
			return true;
		}else{
			throw new CustomException(BaseErrorCode.NOT_AUTHORIZATION.code(),
					BaseErrorCode.NOT_AUTHORIZATION.getDescription());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
}
