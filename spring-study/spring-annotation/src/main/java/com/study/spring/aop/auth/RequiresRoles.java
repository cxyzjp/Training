package com.study.spring.aop.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: 角色注解
 * author: bowen
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRoles {

    /**
     * 角色字符串，匹配用户是否拥有该角色。
     */
    String[] value();

    /**
     * 多个权限字符串存在时，是要满足全部条件（AND），还是只需要满足至少一个（OR）。
     */
    Logical logical() default Logical.AND;
}
