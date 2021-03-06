package com.sundy.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sundy.core.Menu;
import com.sundy.dao.core.MenuMapper;
import com.sundy.service.inter.IMenuService;

@Component
public class MenuServiceImpl implements IMenuService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
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

	public List<Menu> findMenuByUserName(String username) {
		return this.menuMapper.findMenuByUserName(username);
	}

	public List<Menu> findAllMenu() {
		return this.menuMapper.findAllMenu();
	}

	@Override
	public Menu getMenuById(Integer menuId) {
		return this.menuMapper.selectByPrimaryKey(menuId);
	}

}
