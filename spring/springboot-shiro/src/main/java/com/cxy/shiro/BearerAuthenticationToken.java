package com.cxy.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;

public class BearerAuthenticationToken implements AuthenticationToken,
		HostAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private String bearer;

	private String host;

	public BearerAuthenticationToken(String bearer, String host) {
		super();
		this.bearer = bearer;
		this.host = host;
	}

	@Override
	public Object getPrincipal() {
		return bearer;
	}

	@Override
	public Object getCredentials() {
		return bearer;
	}

	@Override
	public String getHost() {
		return host;
	}

}
