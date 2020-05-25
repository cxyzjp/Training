package com.cxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxy.exception.WebException;

@Controller
@RequestMapping("/web")
public class WebExceptionController {

	@GetMapping("/ex1")
	public String ex1() throws Exception {
		throw new WebException("100", "exception msg", "/hello.html");
	}
	@GetMapping("/hello")
	public ModelAndView hello() throws Exception {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("/hello.html");
		return mView;
	}
	
	@GetMapping("/hi")
	public String hi() throws Exception {
		return "redirect:/hello.html";
	}

}
