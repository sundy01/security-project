package com.sundy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sundy.core.User;
import com.sundy.dao.core.UserMapper;
import com.sundy.service.inter.IUserService;

@Component
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	public User findUserByUserName(String userName) {
		return this.userMapper.findUserByUserName(userName);
	}

}
