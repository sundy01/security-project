package com.sundy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sundy.core.Menu;
import com.sundy.dao.core.MenuMapper;
import com.sundy.service.inter.IMenuService;

@Component
public class MenuServiceImpl implements IMenuService {
	
	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> loadMenuByParentId(Integer parentId) {
		List<Menu> menuList=this.menuMapper.loadMenuByParentId(parentId);
		
		if(menuList!=null && menuList.size()>0) {
			for(Menu currentMenu : menuList) {
		         Integer id=currentMenu.getId();
		         if(!currentMenu.getLeaf()) {
		        	 currentMenu.setState("closed");
		         }
		         List<Menu> childrenMenu=this.loadMenuByParentId(id);
		          
		           if(childrenMenu!=null && childrenMenu.size()>0) {
		        	   currentMenu.setChildren(childrenMenu);
		           }
			}
		}
		
		return menuList;
	}

}
