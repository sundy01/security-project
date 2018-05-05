package com.sundy.service.security.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;


public class UrlLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		  return super.determineTargetUrl(request, response);
	}
}
