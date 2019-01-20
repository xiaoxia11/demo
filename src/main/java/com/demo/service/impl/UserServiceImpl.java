package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bean.UserDomain;
import com.demo.dao.UserDao;
import com.demo.service.UserService;

@Service
@Transactional  //事务处理注解
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<UserDomain> selectAll(){
		return userDao.selectAll();
	}
	
	@Override
	public List<UserDomain> selectUser(String sex) {
		return userDao.selectUser(sex);
	}

	@Override
	public int addUser(UserDomain user) {
		return userDao.addUser(user);
	}

	@Override
	public int upUser(UserDomain user) {
		return userDao.upUser(user);
	}

	@Override
	public int deUser(String id) {
		return userDao.deUser(id);
	}
	

}
