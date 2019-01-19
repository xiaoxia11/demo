package com.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.bean.UserDomain;
import com.demo.service.UserService;
import com.demo.util.Export;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/info")
	public String userInfo(){
		return "subject";
	}
	
	@RequestMapping(value="/selectAll",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<UserDomain> selectAll(){
		PageHelper.startPage(1, 5);
		List<UserDomain> list = userService.selectAll();
		PageInfo<UserDomain> info = new PageInfo<UserDomain>(list);
		return info;
	}
	
	@RequestMapping(value="/selectUser",method=RequestMethod.GET)
	@ResponseBody
	public UserDomain selectUser(String id) {
		return userService.selectUser(id);
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public int addUser(@RequestBody UserDomain user){
		return userService.addUser(user);
	}
	
	@RequestMapping(value="/upUser",method=RequestMethod.POST)
	@ResponseBody
	public int upUser(@RequestBody UserDomain user){
		return userService.upUser(user);
	}
	
	@RequestMapping(value="/deUser",method=RequestMethod.GET)
	@ResponseBody
	public int deUser(String id){
		return userService.deUser(id);
	}
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "exportExcel",method = RequestMethod.GET)
	@ResponseBody
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<UserDomain> list = userService.selectAll();
		String fileName = "人员清单";
		String[] key = Export.keys;
		String[] name = Export.value;
		List<Map<String, Object>> listMap = Export.creatExcel(list);
		Export.creatExcelRecord(request, response, name, key, listMap, fileName);
	}

}
