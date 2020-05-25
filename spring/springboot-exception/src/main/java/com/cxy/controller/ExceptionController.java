package com.cxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxy.base.BaseResponse;
import com.cxy.exception.ApiException;
import com.cxy.exception.CustomException;
import com.cxy.service.ExceptionService;

@RestController
@RequestMapping("/ex")
public class ExceptionController {
	
	@Autowired
	ExceptionService exceptionService;
	
	@GetMapping("/hello")
	public BaseResponse<Object> hello() throws CustomException {
		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setData(exceptionService.hello());
		return baseResponse;
	}
	
	@GetMapping("/ex1")
	public BaseResponse<Object> ex1() throws CustomException {
		throw new ApiException("100", "exceptions");
	}
	
	@GetMapping("/ex2")
	public BaseResponse<Object> ex2() throws CustomException {
		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		
		System.out.println(1/0);
		return baseResponse;
	}
	
}
