package com.sundy.service.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.sundy.core.Menu;
import com.sundy.core.Role;
import com.sundy.core.User;
import com.sundy.service.inter.IMenuService;
import com.sundy.service.inter.IRoleService;
import com.sundy.service.inter.IUserService;


public class CustomRealm extends AuthorizingRealm {
	  @Autowired
      private IMenuService menuService;
	  @Autowired
      private IRoleService roleService;
	  @Autowired
      private IUserService userService;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		    String username = (String)principals.getPrimaryPrincipal();  
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
	        
	        Set<String> roleDesc=new HashSet<String>();
	        
	        Set<String> permissionDesc=new HashSet<String>();
	        
	        List<Role> roleList=this.roleService.findRoleByUserName(username);
	        
	        for(Role role : roleList){
	        	String roleName=role.getRolename();
	        	roleDesc.add(roleName);
	        }
	        
	        List<Menu> menuList=this.menuService.findMenuByUserName(username);
	        
	        for(Menu menu : menuList){
	        	String menuName=menu.getUrl();
	        	permissionDesc.add(menuName);
	        }
	        authorizationInfo.setRoles(roleDesc);  
	        authorizationInfo.setStringPermissions(permissionDesc);
	        return authorizationInfo;  
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		    String username = (String)token.getPrincipal();  
	        User user = userService.findUserByUserName(username);  
	        if(user == null) {  
	            throw new UnknownAccountException();//没找到帐号  
	        }  
	        if(Boolean.TRUE.equals(user.isAccountNonLocked())) {  
	            throw new LockedAccountException(); //帐号锁定  
	        }  
	        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
	                user.getUsername(), //用户名  
	                user.getPassword(), //密码  
	                getName()  //realm name  
	        );  
	        return authenticationInfo; 
	}

}
