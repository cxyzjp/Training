package com.cxy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cxy.entity.SysUser;
import com.cxy.service.SysUserService;
import com.cxy.shiro.BearerCacheService;
import com.ms.base.bean.BaseResponse;
import com.ms.base.bean.login.LoginUser;
import com.ms.exception.base.CustomException;

@RestController
public class SysUserController {
	
	@Autowired
	private BearerCacheService bearerCacheService;
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 管理员登录
	 */
	@PostMapping("/login")
	public BaseResponse<Object> dologin(HttpServletRequest request, 
			@RequestBody @Valid SysUser sysUser) throws CustomException {
		
		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		
		LoginUser loginUser = sysUserService.doLogin(sysUser);
		String host = request.getRemoteHost();
		loginUser.setHost(host);
		bearerCacheService.saveAccount(loginUser);

		baseResponse.setData(loginUser);
		return baseResponse;
	}

}
