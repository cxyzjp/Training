package com.cxy.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import com.ms.common.constant.AuthenticationConstants;
import com.ms.exception.base.ExceptionType;
import com.ms.exception.base.ResponseFormat;

public class ShiroFilter extends AuthenticatingFilter {

	protected String getBearer(ServletRequest request) {
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

	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		String bearer = getBearer(request);
		return StringUtils.isNotEmpty(bearer);
	}

	private void recordErrorLog(String code, String message) {
//		 logger.error("----------------------------");
//		 logger.error(code);
//		 logger.error(message);
//		 logger.error("----------------------------");
	}

	protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
		String code = String.valueOf(HttpServletResponse.SC_UNAUTHORIZED);
		String msg = "Authentication required: sending 401 Authentication challenge response.";
		recordErrorLog(code, msg); 
		ResponseFormat.sendSystemExceptionResponseInfo((HttpServletResponse) response, code, msg,
				ExceptionType.SYSTEM_EXCEPTION.code());
		return false;
	}
	
	@Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		boolean b = super.isAccessAllowed(request, response, mappedValue);
		System.out.println(b);
        return false;
    }

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		String bearer = getBearer(request);
		String host = getHost(request);
		return new BearerAuthenticationToken(bearer, host);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		boolean loggedIn = false;
		if (isLoginAttempt(request, response)) {
			loggedIn = executeLogin(request, response);
		} else {
			sendChallenge(request, response);
		}
		return loggedIn;
	}

	/**
	 * 认证失败的返回值如下： 
	 * { 
	 * 	"code": "401", 
	 * 	"message": "NotAuthorization",
	 * 	"description": "AUTH TOKEN INVALID", 
	 * 	"data": null 
	 * }
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {

		String code = String.valueOf(HttpServletResponse.SC_UNAUTHORIZED);
		String msg = e.getMessage();
		recordErrorLog(code, msg);
		ResponseFormat.sendSystemExceptionResponseInfo((HttpServletResponse) response, code, msg,
				ExceptionType.SYSTEM_EXCEPTION.code());
		return super.onLoginFailure(token, e, request, response);
	}

}
