package com.example.demo.jpa;

import com.example.demo.BaseTest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * description:
 * author: bowen
 * date: 2019/3/12
 */
public class UserAddTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void test01() {
        int i = ThreadLocalRandom.current().nextInt(1, 10);
        User user = User.builder().name("zhang" + i).age(1).createTime(new Date()).build();
        user.setCreateTime(new Date());
        userService.addUser(user);
    }

}
