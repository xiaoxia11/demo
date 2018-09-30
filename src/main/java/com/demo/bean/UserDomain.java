package com.demo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//声明表名
//@Table(name="person")
public class UserDomain implements Serializable{
	
	private static final long serialVersionUID = -4577255781088498763L;
	
	//@JsonIgnore    //不显示
	private String id;
	private String name;
	private String age;
	private String sex;
	//@JsonInclude(Include.NON_NULL)   //为空时不显示
	//@JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")   //Date装换字符串
	private String txt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}

}
