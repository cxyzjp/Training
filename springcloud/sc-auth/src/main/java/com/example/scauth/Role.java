package com.example.scauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * description:
 * author: bowen
 * date: 2019/7/19
 */
@Getter
@Setter
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;

    public Role(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
