package com.sundy.service.security.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class CustomerLoginUrlAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CustomerLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

}
