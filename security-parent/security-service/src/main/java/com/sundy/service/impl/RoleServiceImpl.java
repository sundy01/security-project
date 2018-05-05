package com.sundy.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sundy.core.Role;
import com.sundy.dao.core.RoleMapper;
import com.sundy.service.inter.IRoleService;

@Component
public class RoleServiceImpl implements IRoleService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleMapper roleMapper;

	public List<Role> findRoleByUserName(String username) {
		return this.roleMapper.findRoleByUserName(username);
	}

	public List<Role> findRoleByMenuId(Integer menuId) {
		return this.roleMapper.findRoleByMenuId(menuId);
	}

	@Override
	public Role getRoleById(Integer roleId) {
		return this.roleMapper.selectByPrimaryKey(roleId);
	}

}
