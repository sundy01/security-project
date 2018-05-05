package com.sundy.service.security.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;


public class RememberMeCookieAuthenticationFilter extends RememberMeAuthenticationFilter{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public RememberMeCookieAuthenticationFilter(
			AuthenticationManager authenticationManager,
			RememberMeServices rememberMeServices) {
		super(authenticationManager, rememberMeServices);
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) {
		super.onSuccessfulAuthentication(request, response, authResult);
	}	

}
