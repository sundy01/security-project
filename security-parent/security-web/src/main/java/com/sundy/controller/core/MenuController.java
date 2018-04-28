package com.sundy.controller.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 菜单页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/menuManager")
	public String resourceInfoView(Model model) {
		logger.info("加载资源页面");
		return "core/menuManager";
	}
	
	/**
	 *增加资源页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addResourceInfo")
	public String addResourceInfoView(Model model) {
		logger.info("增加载资源页面");
		return "core/addResourceInfo";
	}
	
	@RequestMapping("/editorResourceInfo")
	public String editorResourceInfoView(Model model) {
		logger.info("编辑载资源页面");
		return "core/editorResourceInfo";
	}
	
	
}
