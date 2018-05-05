package com.sundy.service.inter;

import java.util.List;

import com.sundy.core.Role;

public interface IRoleService {
	
	public Role getRoleById(Integer roleId);

	public List<Role> findRoleByUserName(String username);
	
	public List<Role> findRoleByMenuId(Integer menuId);
}
