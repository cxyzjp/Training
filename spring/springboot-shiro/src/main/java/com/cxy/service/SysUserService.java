package com.cxy.service;

import java.util.List;

import com.cxy.bean.SysUserAddReq;
import com.cxy.entity.SysUser;
import com.ms.base.bean.login.LoginUser;
import com.ms.exception.base.CustomException;

public interface SysUserService {
	
	public LoginUser doLogin(SysUser sysUser) throws CustomException;
	
	public List<String> selectAllPerms(Long userId) throws CustomException;
	
	public void add(SysUserAddReq sysUserAddReq) throws CustomException;
	
}
