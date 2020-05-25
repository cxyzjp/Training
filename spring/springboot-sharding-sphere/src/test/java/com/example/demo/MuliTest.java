package com.example.demo;

import com.cxy.sphere.DemoApplication;
import com.cxy.sphere.mapper.a.UserAMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MuliTest {

    @Autowired
    private UserAMapper userAMapper;

    @Test
    public void list() {
        System.out.println(userAMapper.selectAll());
    }

    @Test
    public void aTest() {
//        System.out.println(usersMapper.selectByPrimaryKey(1L));
    }
}
