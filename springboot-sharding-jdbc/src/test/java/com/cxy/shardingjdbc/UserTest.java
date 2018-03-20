package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.User;
import com.cxy.shardingjdbc.mapper.sharding.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void create() {
        userMapper.createIfNotExistsTable();
        System.out.println("create success");
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUserId(1);
        user.setCreateTime(1521428895);
        userMapper.insert(user);

        user = new User();
        user.setUserId(1);
        user.setCreateTime(1521428895);
        userMapper.insert(user);
        System.out.println(userMapper.selectAll());
    }

}
