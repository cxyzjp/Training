package com.example.scauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: bowen
 * date: 2019/7/19
 */
@Service
public class UserService implements UserDetailsService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final String pwd = encoder.encode("123456");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Role a = new Role(1, "A");
        Role b = new Role(2, "B");
        List<Role> roles = new ArrayList<>();
        roles.add(a);
        roles.add(b);

        return new User(1, "zhangsan", pwd, roles);
    }

}
