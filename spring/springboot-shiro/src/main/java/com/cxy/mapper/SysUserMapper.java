package com.cxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxy.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    SysUser selectByUsername(@Param("username") String username);
    
    List<String> selectAllPerms(@Param("userId") Long userId);
    
}