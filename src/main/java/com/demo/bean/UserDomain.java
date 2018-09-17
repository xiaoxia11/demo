package com.demo.bean;

import java.io.Serializable;

//声明表名
//@Table(name="person")
public class UserDomain implements Serializable{
	
	private static final long serialVersionUID = -4577255781088498763L;
	
	private String id;
	private String name;
	private String age;
	private String sex;
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
