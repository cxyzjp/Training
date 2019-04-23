package com.study.spring.aop.auth;

import java.util.Set;

/**
 * description: 权限
 * author: bowen
 */
public interface AuthRealm {
    /**
     * 是否已经认证
     */
    boolean isAuth();

    /**
     * 获取用户角色字符串
     */
    Set<String> getUserRoles();

    /**
     * 获取用户权限字符串
     */
    Set<String> getUserPermissions();
}
