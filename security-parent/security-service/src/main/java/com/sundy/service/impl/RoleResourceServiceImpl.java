package com.sundy.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sundy.core.Menu;
import com.sundy.core.Role;
import com.sundy.core.RoleResource;
import com.sundy.dao.core.MenuMapper;
import com.sundy.dao.core.RoleMapper;
import com.sundy.dao.core.RoleResourceMapper;
import com.sundy.service.inter.IRoleResourceService;

@Component
public class RoleResourceServiceImpl implements IRoleResourceService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
    private RoleResourceMapper roleResourceMapper;
	
	@Override
	public List<RoleResource> findAllRoleResouce() {
		
		List<RoleResource> list=this.roleResourceMapper.findAllRoleResource();
		if(list!=null && list.size()>0){
			for(RoleResource roleRes : list){
				Integer roleId=roleRes.getRoleid();
				Integer menuId=roleRes.getMenuid();
				
				Role roleBean=this.roleMapper.selectByPrimaryKey(roleId);
				Menu menuBean=this.menuMapper.selectByPrimaryKey(menuId);
				roleRes.setRoleBean(roleBean);
				roleRes.setMenuBean(menuBean);
			}
		}
		return list;
	}

}
