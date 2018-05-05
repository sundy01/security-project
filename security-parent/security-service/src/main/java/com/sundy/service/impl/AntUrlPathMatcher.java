package com.sundy.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.sundy.service.inter.UrlMatcher;

public class AntUrlPathMatcher implements UrlMatcher {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	  private boolean requiresLowerCaseUrl = true;
	    private PathMatcher pathMatcher = new AntPathMatcher();

	    public AntUrlPathMatcher() {
	        this(true);
	    }

	    public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
	        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	    }

	    public Object compile(String path) {
	        if (requiresLowerCaseUrl) {
	            return path.toLowerCase();
	        }

	        return path;
	    }

	    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
	        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	    }

	    public boolean pathMatchesUrl(Object path, String url) {
	        if ("/**".equals(path) || "**".equals(path)) {
	            return true;
	        }
	        return pathMatcher.match((String)path, url);
	    }

	    public String getUniversalMatchPattern() {
	        return "/**";
	    }

	    public boolean requiresLowerCaseUrl() {
	        return requiresLowerCaseUrl;
	    }

	    public String toString() {
	        return getClass().getName() + "[requiresLowerCase='" + requiresLowerCaseUrl + "']";
	    }

}
