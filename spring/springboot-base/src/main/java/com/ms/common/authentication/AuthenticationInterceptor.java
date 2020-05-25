package com.ms.common.authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ms.base.utils.serialnumbergenerator.UUIDUtil;
import com.ms.common.constant.AuthenticationConstants;
import com.ms.common.redis.bearer.SignatureTokenCacheService;
import com.ms.exception.base.CustomException;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Autowired
	private SignatureTokenCacheService tokenCacheService;
	@Autowired
	private ClientAuthenticationService clientAuthenticationService;
	
	private static final String VERSION_TAG = "Version";
	private static final String ACCESS_KEY_ID_TAG = "AccessKeyId";
	private static final String SIGNATURE_TAG = "Signature";
	private static final String SIGNATURE_METHOD_TAG = "SignatureMethod";
	private static final String TIMESTAMP_TAG = "Timestamp";
	private static final String SIGNATURE_VERSION_TAG = "SignatureVersion";
	private static final String SIGNATURE_NONCE_TAG = "SignatureNonce";
	private static final String CONTENT_MD5_TAG = "Content-MD5";

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String cacheToken = tokenCacheService.getToken(request);
		// signature token没有失效
		if(StringUtils.isNotEmpty(cacheToken)){
			response.setHeader(SignatureTokenCacheService.ResponseToken, cacheToken);
			return true;
		}
		
		Map<String, String> clientAuthenticationHeader = getClientAuthenticationHeader(
				request, response);
		if (clientAuthenticationHeader != null && !clientAuthenticationHeader.isEmpty()) {
			ClientAuthenticationResp clientAuthenticationResp = doClientAuthentc(clientAuthenticationHeader);

			if (clientAuthenticationResp.getStatus().equalsIgnoreCase("true")) {
				cacheToken = UUIDUtil.generatorToken();
				tokenCacheService.saveToken(cacheToken);
				response.setHeader(SignatureTokenCacheService.ResponseToken, cacheToken);
				return true;
			} else {
				throw new CustomException(
						clientAuthenticationResp.getCode(),
						clientAuthenticationResp.getMessage());
			}
		} else {
			return false;
		}
	}
	
	protected String getBearer(HttpServletRequest httpRequest) {
		String bearer = httpRequest.getParameter(AuthenticationConstants.authcScheme);
		if (StringUtils.isNotEmpty(bearer)) {
			return bearer;
		}
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

	private Map<String, String> getClientAuthenticationHeader(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> header = new HashMap<String, String>();
		CustomException customException = new CustomException();
		if (request.getHeader(VERSION_TAG) != null) {
			header.put(VERSION_TAG, request.getHeader(VERSION_TAG));
		} else {
			customException
					.setCode(AuthenticationErrorCode.VERSION_IS_EMPTY_ERROR
							.code());
			customException
					.setCustomMessage(AuthenticationErrorCode.VERSION_IS_EMPTY_ERROR
							.getDescription());
		}
		if (request.getHeader(ACCESS_KEY_ID_TAG) != null) {
			header.put(ACCESS_KEY_ID_TAG, request.getHeader(ACCESS_KEY_ID_TAG));
		} else {
			customException
					.setCode(AuthenticationErrorCode.ACCESS_KEY_IS_EMPTY_ERROR
							.code());
			customException
					.setCustomMessage(AuthenticationErrorCode.ACCESS_KEY_IS_EMPTY_ERROR
							.getDescription());
		}
		if (request.getHeader(SIGNATURE_TAG) != null) {
			header.put(SIGNATURE_TAG, request.getHeader(SIGNATURE_TAG));
		} else {
			customException
					.setCode(AuthenticationErrorCode.SIGNATURE_IS_EMPTY_ERROR
							.code());
			customException
					.setCustomMessage(AuthenticationErrorCode.SIGNATURE_IS_EMPTY_ERROR
							.getDescription());
		}
		if (request.getHeader(SIGNATURE_METHOD_TAG) != null) {
			header.put(SIGNATURE_METHOD_TAG,
					request.getHeader(SIGNATURE_METHOD_TAG));
		} else {
			customException
					.setCode(AuthenticationErrorCode.SIGNATURE_METHOD_IS_EMPTY_ERROR
							.code());
			customException
					.setCustomMessage(AuthenticationErrorCode.SIGNATURE_METHOD_IS_EMPTY_ERROR
							.getDescription());
		}
		if (request.getHeader(TIMESTAMP_TAG) != null) {
			header.put(TIMESTAMP_TAG, request.getHeader(TIMESTAMP_TAG));
		} else {
			customException
					.setCode(AuthenticationErrorCode.TIMESTAMP_IS_EMPTY_ERROR
							.code());
			customException
					.setCustomMessage(AuthenticationErrorCode.TIMESTAMP_IS_EMPTY_ERROR
							.getDescription());
		}
		if (request.getHeader(SIGNATURE_VERSION_TAG) != null) {
			header.put(SIGNATURE_VERSION_TAG,
					request.getHeader(SIGNATURE_VERSION_TAG));
		} else {
			customException
					.setCode(AuthenticationErrorCode.SIGNATURE_VERSION_IS_EMPTY_ERROR
							.code());
			customException
					.setCustomMessage(AuthenticationErrorCode.SIGNATURE_VERSION_IS_EMPTY_ERROR
							.getDescription());
		}
		if (request.getHeader(SIGNATURE_NONCE_TAG) != null) {
			header.put(SIGNATURE_NONCE_TAG,
					request.getHeader(SIGNATURE_NONCE_TAG));
		} else {
			customException
					.setCode(AuthenticationErrorCode.SIGNATURE_NONCE_IS_EMPTY_ERROR
							.code());
			customException
					.setCustomMessage(AuthenticationErrorCode.SIGNATURE_NONCE_IS_EMPTY_ERROR
							.getDescription());
		}
		if (request.getHeader(CONTENT_MD5_TAG) != null) {
			header.put(CONTENT_MD5_TAG, request.getHeader(CONTENT_MD5_TAG));
		}
		if (customException.getCode() != null) {
			throw customException;
		} else {
			return header;
		}
	}

	private ClientAuthenticationResp doClientAuthentc(
			Map<String, String> clientAuthenticationHeader) throws IOException {
		ClientAuthenticationResp clientAuthenticationResp = clientAuthenticationService
				.doAuthentication(clientAuthenticationHeader);
		return clientAuthenticationResp;
	}

}
