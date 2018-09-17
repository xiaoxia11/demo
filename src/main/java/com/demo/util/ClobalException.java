package com.demo.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类-简易型
 * @author Administrator
 *
 */
@ControllerAdvice
public class ClobalException {
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Map<String, Object> exceptionHandler(HttpServletRequest request,Exception e){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", false);
		map.put("errMsg", e.getMessage());
		return map;
	}
}
