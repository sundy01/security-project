package com.sundy.controller.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sundy.core.Menu;
import com.sundy.service.inter.IMenuService;

@Controller
@RequestMapping("/main")
public class MainController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("/mainPage")
	public String login(Model model) {
		return "core/main";
	}
	
	@RequestMapping("/loadMenuInfo")
	@ResponseBody
	public List<Menu> loadMenuInfo(Integer parentId){
		 List<Menu> list=this.menuService.loadMenuByParentId(parentId);
		 return list;
	}
	
	
	
}
