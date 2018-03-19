package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.User;
import com.cxy.shardingjdbc.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void create() {
        userRepository.createIfNotExistsTable();
        System.out.println("create success");
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUserId(1);
        user.setCreateTime(1521428895);
        userRepository.insert(user);

        user = new User();
        user.setUserId(1);
        user.setCreateTime(1521428895);
        userRepository.insert(user);
        System.out.println(userRepository.selectAll());
    }

}
