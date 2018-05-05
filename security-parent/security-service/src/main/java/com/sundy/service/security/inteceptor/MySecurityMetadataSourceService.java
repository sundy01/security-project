package com.sundy.service.security.inteceptor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.StringUtils;

import com.sundy.core.Menu;
import com.sundy.core.Role;
import com.sundy.core.RoleResource;
import com.sundy.core.security.CustomerConfigAttribute;
import com.sundy.service.impl.AntUrlPathMatcher;
import com.sundy.service.impl.RoleResourceServiceImpl;
import com.sundy.service.inter.IRoleResourceService;
import com.sundy.service.inter.UrlMatcher;


public class MySecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource,InitializingBean,ApplicationContextAware{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private  Map<String,Collection<ConfigAttribute>> resourceMap; //key=url,value=list<role>
    private IRoleResourceService roleResourceService;
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    
    private static ApplicationContext applicationContext;
    
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		logger.info("MySecurityMetadataSourceService getAttributes object"+object);
		String url=null;
		Collection<ConfigAttribute> attributes=null;
		if(object instanceof FilterInvocation){
			url = ((FilterInvocation)object).getRequestUrl();
		}else{
			url=(String)object;
		}
		  
	        int firstQuestionMarkIndex = url.indexOf("?");
	        if (firstQuestionMarkIndex != -1) {
	            url = url.substring(0, firstQuestionMarkIndex);
	        }
	
	  Iterator<String> ite = resourceMap.keySet().iterator();

	  while (ite.hasNext()) {
	   String resURL = ite.next();
	   if (urlMatcher.pathMatchesUrl(url, resURL)) {
		   attributes=resourceMap.get(resURL);
		   logger.info("Size====="+attributes.size());
		   return attributes;
	   }
	  }
	  return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
	
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return true;
	}
	
	private void loadResouceDefine() throws SQLException{
		resourceMap=new HashMap<String,Collection<ConfigAttribute>>();
		
		Collection<ConfigAttribute> configAttrCollection=null;
		logger.info("加载所有的角色资源信息");
		List<RoleResource> allRoleResourceList=this.roleResourceService.findAllRoleResouce();
		
		logger.error("加载所有的角色资源为null");
		
		for(RoleResource resRole : allRoleResourceList){
			
			Role roleBean=resRole.getRoleBean();
			Menu menuBean=resRole.getMenuBean();
			String urlString=menuBean.getUrl();
			if(resourceMap.containsKey(urlString)){
				configAttrCollection=resourceMap.get(urlString);
			}else{
				configAttrCollection=new ArrayList<ConfigAttribute>();
			}
			
			CustomerConfigAttribute customerConfigAttribute=new CustomerConfigAttribute();
			customerConfigAttribute.setAttribute("ROLE_"+roleBean.getRolename());
			configAttrCollection.add(customerConfigAttribute);
			
			if(StringUtils.hasText(urlString)){
				resourceMap.put(urlString,configAttrCollection);
			}
		}
		
	  
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
	  MySecurityMetadataSourceService.applicationContext=applicationContext;
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.roleResourceService=(RoleResourceServiceImpl) applicationContext.getBean("roleResourceServiceImpl");
		this.loadResouceDefine();
		
	}

	
	
	

}
