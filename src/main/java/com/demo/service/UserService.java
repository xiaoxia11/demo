package com.demo.service;

import java.util.List;

import com.demo.bean.UserDomain;

public interface UserService {
	
	List<UserDomain> selectAll();
	
	UserDomain selectUser(String id);
	
	int addUser(UserDomain user);
	
	int upUser(UserDomain user);
	
	int deUser(String id);

}
