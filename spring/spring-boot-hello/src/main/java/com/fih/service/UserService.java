package com.fih.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fih.mapper.UserMapper;
import com.fih.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> likeName(String name){
		return userMapper.likeName(name);
	}
	
	@Transactional//添加事务.
	public void save(User user){
		userMapper.save(user);
	}
}
