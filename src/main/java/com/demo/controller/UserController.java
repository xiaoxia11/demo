package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.bean.UserDomain;
import com.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/info")
	public String userInfo(){
		return "index";
	}
	
	@RequestMapping(value="/selectAll",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> selectAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserDomain> list = userService.selectAll();
		map.put("list", list);
		return map;
	}
	
	@RequestMapping(value="/selectUser",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> selectUser(String id) {
		//int i=1/0;   测试ClobalException.java这个异常处理类
		Map<String, Object> map = new HashMap<String, Object>();
		UserDomain user = userService.selectUser(id);
		map.put("user", user);
		return map;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addUser(@RequestBody UserDomain user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", userService.addUser(user));
		return map;
	}
	
	@RequestMapping(value="/upUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upUser(@RequestBody UserDomain user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", userService.upUser(user));
		return map;
	}
	
	@RequestMapping(value="/deUser",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> deUser(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", userService.deUser(id));
		return map;
	}

}
