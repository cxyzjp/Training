package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: bowen
 * date: 2019/3/12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/page")
    public Page<User> pageUser() {
        List<Sort.Order> list = new ArrayList<>();
        list.add(Sort.Order.asc("name"));
        list.add(Sort.Order.desc("age"));
        Sort sort = Sort.by(list);
        PageRequest pageRequest = PageRequest.of(0, 3, sort);
        return userRepository.findByNameLike("zhang%", pageRequest);
    }

}
