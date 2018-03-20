package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.entity.User;
import com.cxy.shardingjdbc.mapper.a.UserAMapper;
import com.cxy.shardingjdbc.mapper.b.UserBMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MuliTest {

    @Autowired
    private UserAMapper userAMapper;
    @Autowired
    private UserBMapper userBMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setCreateTime(1);
        userAMapper.insert(user);

        user = new User();
        user.setCreateTime(2);
        userBMapper.insert(user);
        System.out.println(userAMapper.selectAll());
        System.out.println(userBMapper.selectAll());
    }

}
