package com.sundy.core.security;

import org.springframework.security.access.ConfigAttribute;

public class CustomerConfigAttribute implements ConfigAttribute {
	private String attribute="IS_AUTHENTICATED_FULLY"; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getAttribute() {
		return null;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	

}
