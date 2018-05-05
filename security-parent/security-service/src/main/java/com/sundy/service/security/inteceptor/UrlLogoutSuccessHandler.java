package com.sundy.service.security.inteceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class UrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements InitializingBean{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String logoutSuccessUrl;

	public String getLogoutSuccessUrl() {
		return logoutSuccessUrl;
	}

	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.onLogoutSuccess(request, response, authentication);
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
	
	

}
