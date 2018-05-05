package com.sundy.controller.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/login.page")
	public String login(HttpServletRequest request,Map<String,String> map) {
        return "/login";
	}
	
	@RequestMapping("/expired.page")
	public String expired(Model model) {
		return "/expired";
	}
	
	@RequestMapping("/accessDenied.page")
	public String accessDenied(Model model) {
		return "/accessDenied";
	}
	
	@RequestMapping("/loginLimit.page")
	public String loginLimit(Model model) {
		return "/loginLimit";
	}
	@RequestMapping("/failure.page")
	public String failure(Model model) {
		return "/failure";
	}
	
	@RequestMapping("/manager.page")
	public String manager(Model model) {
		return "/manager";
	}
	
}
