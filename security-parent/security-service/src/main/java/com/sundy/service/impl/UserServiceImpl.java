package com.sundy.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sundy.core.User;
import com.sundy.dao.core.UserMapper;
import com.sundy.service.inter.IUserService;

@Component
public class UserServiceImpl implements IUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserMapper userMapper;
	public User findUserByUserName(String userName) {
		return this.userMapper.findUserByUserName(userName);
	}

}
