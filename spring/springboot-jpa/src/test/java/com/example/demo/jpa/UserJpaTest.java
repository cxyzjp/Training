package com.example.demo.jpa;

import com.example.demo.BaseTest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * description:
 * author: bowen
 * date: 2019/3/12
 */
public class UserJpaTest extends BaseTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test01() {
        int i = ThreadLocalRandom.current().nextInt(1, 10);
        User user = User.builder().name("zhang" + i).age(1).createTime(new Date()).build();
        user.setCreateTime(new Date());
        userRepository.save(user);
    }

    @Test
    public void test02() {
        // 分页、排序、模糊查询
        List<Sort.Order> list = new ArrayList<>();
        list.add(Sort.Order.asc("name"));
        list.add(Sort.Order.desc("age"));
        Sort sort = Sort.by(list);
        PageRequest pageRequest = PageRequest.of(0, 3, sort);
        Page<User> byNameLike = userRepository.findByNameLike("zhang%", pageRequest);
        byNameLike.forEach(u -> System.out.println(u.getName() + " : " + u.getAge()));

        Slice<User> users = userRepository.findByAge(1, pageRequest);
        users.forEach(u -> System.out.println(u.getName() + " : " + u.getAge()));
    }

    @Test
    public void test03() {
        // 分页、排序、模糊查询
        List<Sort.Order> list = new ArrayList<>();
        list.add(Sort.Order.asc("name"));
        list.add(Sort.Order.desc("age"));
        Sort sort = Sort.by(list);
        PageRequest pageRequest = PageRequest.of(0, 3, sort);
        Page<User> byNameLike = userRepository.findByNameLike("zhang%", pageRequest);
        byNameLike.forEach(u -> System.out.println(u.getName() + " : " + u.getAge()));

        Slice<User> users = userRepository.findByAge(1, pageRequest);
        users.forEach(u -> System.out.println(u.getName() + " : " + u.getAge()));
    }
}
