package com.cxy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxy.bean.SysUserAddReq;
import com.cxy.entity.SysRole;
import com.cxy.entity.SysRolePermission;
import com.cxy.entity.SysUser;
import com.cxy.entity.SysUserRole;
import com.cxy.mapper.SysRoleMapper;
import com.cxy.mapper.SysRolePermissionMapper;
import com.cxy.mapper.SysUserMapper;
import com.cxy.mapper.SysUserRoleMapper;
import com.ms.base.bean.login.LoginErrorCode;
import com.ms.base.bean.login.LoginUser;
import com.ms.base.utils.serialnumbergenerator.UUIDUtil;
import com.ms.exception.base.CustomException;

@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	
	@Override
	@Transactional
	public LoginUser doLogin(SysUser sysUser) throws CustomException{
		LoginUser loginUser = null;
		SysUser su = sysUserMapper.selectByUsername(sysUser.getUsername());
		
		// 登录成功
		if(su.getPassword().equals(sysUser.getPassword())){
			loginUser = new LoginUser();
			
			loginUser.setName(su.getUsername());
			loginUser.setBearer(UUIDUtil.generatorToken());
			loginUser.setId(su.getId());
		}else{
			throw new CustomException(LoginErrorCode.USER_IS_NOT_EXIST_OR_PASSWORD_IS_ERROR.getCode(), 
					LoginErrorCode.USER_IS_NOT_EXIST_OR_PASSWORD_IS_ERROR.getDescription());
		}
		return loginUser;
	}
	
	@Override
	public List<String> selectAllPerms(Long userId) throws CustomException {
		return sysUserMapper.selectAllPerms(userId);
	}
	
	@Override
	public void add(SysUserAddReq sysUserAddReq) throws CustomException{
		// 用户
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserAddReq, sysUser);
		sysUserMapper.insertSelective(sysUser);
		
		// 角色
		SysRole role = new SysRole();
		role.setRole(sysUser.getUsername());
		sysRoleMapper.insertSelective(role);
		
		// 用户-角色
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setRoleId(role.getId());
		sysUserRole.setUserId(sysUser.getId());
		sysUserRoleMapper.insertSelective(sysUserRole);
		
		// 角色-资源
		List<SysRolePermission> list = new ArrayList<SysRolePermission>();
		String[] permIds = sysUserAddReq.getPermIds().split(",");
		for (String string : permIds) {
			SysRolePermission p = new SysRolePermission();
			p.setRoleId(role.getId());
			p.setPermId(Long.valueOf(string));
			list.add(p);
		}
		sysRolePermissionMapper.insertBatch(list);
	}

}
