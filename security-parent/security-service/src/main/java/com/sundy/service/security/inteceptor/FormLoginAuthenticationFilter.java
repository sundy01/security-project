package com.sundy.service.security.inteceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class FormLoginAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean postOnly = true;
	private String usernameParameter = "j_username";
	private String passwordParameter = "j_password";
	protected AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private SessionRegistry sessionRegistry;
	private String loginUsersLimitUrl;
	private int maximumUsers = -1;
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";


	protected FormLoginAuthenticationFilter() {
		 super("/j_spring_security_check");
		
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
	
		/*if (maximumUsers != -1 && sessionRegistry.getAllPrincipals().size() >= maximumUsers) {
			redirectStrategy.sendRedirect(request, response, loginUsersLimitUrl);
			return null;
		}*/
		
		String username = request.getParameter(usernameParameter);
		String password = request.getParameter(passwordParameter);
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		username = username.trim();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		HttpSession session = request.getSession(false);
		if (session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, TextEscapeUtils.escapeEntities(username));
		}
		setDetails(request, authRequest);
		Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
		logger.info("========="+authentication);
		return authentication;
	}
	
	
	
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public String getLoginUsersLimitUrl() {
		return loginUsersLimitUrl;
	}

	public void setLoginUsersLimitUrl(String loginUsersLimitUrl) {
		this.loginUsersLimitUrl = loginUsersLimitUrl;
	}

	public int getMaximumUsers() {
		return maximumUsers;
	}

	public void setMaximumUsers(int maximumUsers) {
		this.maximumUsers = maximumUsers;
	}

}
