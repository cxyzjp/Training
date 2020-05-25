package com.fih.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {
	
	@GetMapping("/doGet/{urlParam}")
	public String doGet(HttpServletRequest request, 
			@PathVariable String urlParam, @RequestParam String param){
		return "doGet success : " + urlParam + param;
	}
	
	@PostMapping("/doPost/{urlParam}")
	public String doPost(HttpServletRequest request, 
			@PathVariable String urlParam, @RequestParam String param){
		return "doPost success : " + urlParam + param;
	}
	
	@PutMapping("/doPut/{urlParam}")
	public String doPut(HttpServletRequest request, 
			@PathVariable String urlParam, @RequestParam String param){
		return "doPut success : " + urlParam + param;
	}
	
	/**
	 * 不能接收body里面的参数
	 */
	@DeleteMapping("/doDelete/{urlParam}")
	public String doDelete(HttpServletRequest request, 
			@PathVariable String urlParam){
		return "doDelete success : " + urlParam;
	}
	
	@PatchMapping("/doPatch/{urlParam}")
	public String doPatch(HttpServletRequest request, 
			@PathVariable String urlParam, @RequestParam String param){
		return "doPatch success : " + urlParam + param;
	}

}
