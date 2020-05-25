package com.ms.common.authentication;

import java.util.Map;

public interface ClientAuthenticationService {
	public ClientAuthenticationResp doAuthentication(
			Map<String, String> clientAuthenticationHeader);
}
