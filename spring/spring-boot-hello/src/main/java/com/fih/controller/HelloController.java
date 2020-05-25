package com.fih.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fih.pojo.User;
import com.fih.service.UserService;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author Bowen
 *
 */
@RestController
public class HelloController {

	@Autowired
	private UserService demoService;

	@RequestMapping("/hello")
	public String hello(){
		return "hello world 哈哈哈";
	}
	
	@RequestMapping("/getUser")
	public User getUser(){
		User user = new User();
		user.setId(1);
		user.setName("张");
		user.setCreateTime(new Date());
		return user;
	}
	
	@RequestMapping("/likeName")
	public List<User> likeName(String name){
		PageHelper.startPage(1, 2);
		return demoService.likeName(name);
	}
	
	@RequestMapping("/save")
	public User save(){
		User user = new User();
		user.setName("张三");
		demoService.save(user);
		return user;
	}
}
