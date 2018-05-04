package com.sundy.controller.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/login")
	public String login(Model model) {
	/*	logger.debug(" HomeController => /login");
		String exception = request.getParameter("shiroLoginFailure");
		logger.debug("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
            	logger.error("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
            	logger.error("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
            	logger.error("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                logger.error("else -- >" + exception);
            }
        }
        
        map.put("msg", msg);*/
        
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping("/unauth")
	public String unauth(Model model) {
		return "unauth";
	}
	
}
