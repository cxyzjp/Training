package com.study.spring.aop.auth;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * description: 注解切面
 * author: bowen
 */
@RequiredArgsConstructor
@Aspect
@Component
@ConditionalOnBean(AuthRealm.class)
public class AuthAspect {
    private final AuthRealm authRealm;

    @Pointcut("@annotation(com.cd.common.core.aspect.auth.RequiresAuth)")
    public void authPointCut() {
    }

    @Pointcut("@annotation(com.cd.common.core.aspect.auth.RequiresRoles)")
    public void rolePointCut() {
    }

    @Pointcut("@annotation(com.cd.common.core.aspect.auth.RequiresPermissions)")
    public void permissionPointCut() {
    }

    @Before("authPointCut()")
    public void processAuth() {
        if (!authRealm.isAuth()) {
//            throw new CommonException(BaseErrorCode.NOT_AUTHORIZATION);
        }
    }

    @Before("rolePointCut()")
    public void processRole(JoinPoint joinPoint) {
        // 1. 获取注解内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);

        String[] roles = requiresRoles.value();
        Logical logical = requiresRoles.logical();

        // 2. 获取用户所有权限字符串
        Set<String> userRoles = authRealm.getUserRoles();
        if (userRoles == null || userRoles.isEmpty()) {
//            throw new CommonException(BaseErrorCode.NOT_ROLES, "User is not roles " + Arrays.toString(roles));
        }

        // 3. 判断用户是否拥有从注解中获取的字符串权限
        boolean hasRole = false;
        if (roles.length == 1) {
            hasRole = hasRole(roles[0], userRoles);
        } else {
            for (String role : roles) {
                hasRole = hasPermission(role, userRoles);
                if (Logical.AND.equals(logical) && !hasRole) {
                    break;
                }
                if (Logical.OR.equals(logical) && hasRole) {
                    break;
                }
            }
        }
        if (!hasRole) {
//            throw new CommonException(BaseErrorCode.NOT_ROLES, "User is not roles " + Arrays.toString(roles));
        }
    }

    @Before("permissionPointCut()")
    public void processPermission(JoinPoint joinPoint) {
        // 1. 获取注解内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequiresPermissions permissions = method.getAnnotation(RequiresPermissions.class);

        String[] perms = permissions.value();
        Logical logical = permissions.logical();

        // 2. 获取用户所有权限字符串
        Set<String> userPermissions = authRealm.getUserPermissions();
        if (userPermissions == null || userPermissions.isEmpty()) {
//            throw new CommonException(BaseErrorCode.NOT_PERMISSIONS, "User is not permitted " + Arrays.toString(perms));
        }

        // 3. 判断用户是否拥有从注解中获取的字符串权限
        boolean hasPerm = false;
        if (perms.length == 1) {
            hasPerm = hasPermission(perms[0], userPermissions);
        } else {
            for (String perm : perms) {
                hasPerm = hasPermission(perm, userPermissions);
                if (Logical.AND.equals(logical) && !hasPerm) {
                    break;
                }
                if (Logical.OR.equals(logical) && hasPerm) {
                    break;
                }
            }
        }
        if (!hasPerm) {
//            throw new CommonException(BaseErrorCode.NOT_PERMISSIONS, "User is not permitted " + Arrays.toString(perms));
        }
    }

    private boolean hasRole(String role, Set<String> userRoles) {
        for (String userRole : userRoles) {
            if (userRole.equals(role)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPermission(String perm, Set<String> userPermissions) {
        for (String permission : userPermissions) {
            if (permission.equals(perm)) {
                return true;
            }
        }
        return false;
    }
}
