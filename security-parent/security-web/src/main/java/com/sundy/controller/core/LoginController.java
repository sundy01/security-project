package com.sundy.controller.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping("/")
	public String login(Model model) {
		return "login";
	}
	
	
	
}
