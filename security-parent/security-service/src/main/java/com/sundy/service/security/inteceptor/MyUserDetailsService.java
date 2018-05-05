package com.sundy.service.security.inteceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sundy.core.Role;
import com.sundy.core.User;
import com.sundy.service.impl.RoleServiceImpl;
import com.sundy.service.impl.UserServiceImpl;
import com.sundy.service.inter.IRoleService;
import com.sundy.service.inter.IUserService;

public class MyUserDetailsService implements UserDetailsService,ApplicationContextAware{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IRoleService roleService;
	private IUserService userService;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException{
		
		logger.info("进入MyUserDetailsService **************** username=="+username);
		User user=null;
		try{
			 user=this.userService.findUserByUserName(username);
		}catch(Exception es){
			logger.error("MyUserDetailsService 抛出异常.."+es);
		}
		
		if(null== user){
			 throw new UsernameNotFoundException("用户名称["+username+"]未找到!");
		}
		 Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 List<Role> userRoles=this.roleService.findRoleByUserName(username);
		 for (Role userRole : userRoles) {
	            // 和WebSecurityMetadataSource中的SecurityConfig参数对应
			 SimpleGrantedAuthority grantAuthority=new SimpleGrantedAuthority("ROLE_"+userRole.getRolename());
	          
			 authorities.add(grantAuthority);
	        }
		 user.setAuthorities(authorities);
		return user;
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
	  this.roleService=(RoleServiceImpl)applicationContext.getBean("roleServiceImpl");
	  this.userService=(UserServiceImpl)applicationContext.getBean("userServiceImpl");
	}
	
	
	

}
