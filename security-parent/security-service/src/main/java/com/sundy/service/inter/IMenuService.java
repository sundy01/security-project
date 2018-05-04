package com.sundy.service.inter;

import java.util.List;

import com.sundy.core.Menu;

public interface IMenuService {
	
	public List<Menu> loadMenuByParentId(Integer parentId);
	
	public List<Menu> findAllMenu();
	
	public List<Menu> findMenuByUserName(String username);

}
