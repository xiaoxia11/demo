package com.demo.dao;

import java.util.List;

import com.demo.bean.UserDomain;

//@Mapper
public interface UserDao {

	//@Select("select * from person")
	List<UserDomain> selectAll();
	
	UserDomain selectUser(String id);
	
	int addUser(UserDomain user);
	
	int upUser(UserDomain user);
	
	int deUser(String id);
	
}
