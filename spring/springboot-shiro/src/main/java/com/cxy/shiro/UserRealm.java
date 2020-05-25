package com.cxy.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxy.service.SysUserService;
import com.ms.base.bean.login.LoginUser;
import com.ms.exception.base.BaseErrorCode;

/**
 * 身份校验核心类;
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private BearerCacheService bearerCacheService;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public boolean supports(AuthenticationToken token) {
		if (token == null) {
			return false;
		}
		if (token instanceof BearerAuthenticationToken) {
			return true;
		} else {
			return false;
		}
	}

	private SimpleAccount getSimpleAccount(BearerAuthenticationToken token) {

		String bearer = (String) token.getPrincipal();

		SimpleAccount account = bearerCacheService.getAccount(bearer);

		if (account == null) {
			throw new AuthenticationException(BaseErrorCode.AUTH_TOKEN_INVALID.getDescription());
		}

		return account;
	}

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAccount account = getSimpleAccount((BearerAuthenticationToken) token);

		String bearer = (String) token.getPrincipal();

		// 刷新redis存储时间
		try {
			bearerCacheService.saveAccount(bearer, account);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LoginUser user = (LoginUser) principals.getPrimaryPrincipal();
		Long userId = user.getId();

		// 用户权限列表
		List<String> permsList = sysUserService.selectAllPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

}
