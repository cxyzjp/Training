package com.cxy.mapper;

import java.util.List;

import com.cxy.entity.SysRolePermission;

public interface SysRolePermissionMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SysRolePermission record);

	int insertSelective(SysRolePermission record);

	SysRolePermission selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SysRolePermission record);

	int updateByPrimaryKey(SysRolePermission record);

	int insertBatch(List<SysRolePermission> list);

}