package com.cxy.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxy.bean.SysUserAddReq;
import com.cxy.service.SysUserService;
import com.ms.exception.base.CustomException;

@RestController
@RequestMapping("/shiro")
public class ShiroController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@GetMapping("/hi")
	public String hi() throws Exception {
		return "hi shiro";
	}
	
	@GetMapping("/list")
	@RequiresPermissions("user:query")
	public String list() throws Exception {
		return "hi list";
	}
	
	@GetMapping("/delete")
	@RequiresPermissions("user:delete")
	public String delete() throws Exception {
		return "hi delete";
	}
	
	@PostMapping("/add")
	@RequiresPermissions("user:create")
	@Transactional
	public String add(@RequestBody SysUserAddReq sysUserAddReq) throws CustomException {
		sysUserService.add(sysUserAddReq);
		return "hi add";
	}

}
