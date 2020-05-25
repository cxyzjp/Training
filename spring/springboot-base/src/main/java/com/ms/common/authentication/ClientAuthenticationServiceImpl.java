package com.ms.common.authentication;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ms.common.httpclient.ApiService;
import com.ms.common.httpclient.HttpResult;

public class ClientAuthenticationServiceImpl implements
		ClientAuthenticationService {
	@Autowired
	private ApiService apiService;

	@Value("${custom.console.authentication.url}")
	private String consoleAuthenticationUrl;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public ClientAuthenticationResp doAuthentication(
			Map<String, String> clientAuthenticationHeader) {
		HttpResult httpResult = null;
		JsonNode jsonNode = null;
		mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.UpperCamelCaseStrategy());

		ClientAuthenticationResp clientAuthenticationResp = new ClientAuthenticationResp();
		try {
			httpResult = apiService.doPostJson(consoleAuthenticationUrl, null,
					clientAuthenticationHeader);
			jsonNode = mapper.readTree(httpResult.getData());
		} catch (IOException e) {
			clientAuthenticationResp.setStatus("false");
			clientAuthenticationResp
					.setCode(AuthenticationErrorCode.AUTHENTICATION_CONSOLE_RESULT_FORMAT_ERROR
							.code());
			clientAuthenticationResp.setMessage(e.getMessage());
			return clientAuthenticationResp;
		} catch (Exception e) {
			clientAuthenticationResp.setStatus("false");
			clientAuthenticationResp
					.setCode(AuthenticationErrorCode.AUTHENTICATION_CONSOLE_NETWORK_ERROR
							.code());
			clientAuthenticationResp.setMessage(e.getMessage());
			return clientAuthenticationResp;
		}

		if (httpResult.getCode() == 200) {
			clientAuthenticationResp.setStatus("true");
			String message = jsonNode.findValue("message").asText();
			clientAuthenticationResp.setMessage(message);
		} else {
			clientAuthenticationResp.setStatus("false");
			String errorCode = jsonNode.findValue("errorCode").asText();
			String errorMessage = jsonNode.findValue("errorMessage").asText();
			clientAuthenticationResp.setCode(errorCode);
			clientAuthenticationResp.setMessage(errorMessage);
		}
		return clientAuthenticationResp;
	}

}
