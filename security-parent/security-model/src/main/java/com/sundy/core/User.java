package com.sundy.core;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name; //用户名称
    private String password;
    private  String username; //账号名称
    private String gender;
    private  boolean accountNonExpired; //账号是否过期
    private  boolean accountNonLocked; //账号是否锁定
    private  boolean credentialsNonExpired;
    private  boolean enabled;
    private Collection<GrantedAuthority> authorities;
    private List<Menu> userMenuList;
    private List<Role> role;
    
    
    @Override  
    public boolean equals(Object obj) {  
        if (obj instanceof User && this.hashCode() == obj.hashCode()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
      
    @Override  
    public int hashCode() {  
        return this.username.hashCode() + this.password.hashCode();  
    }


	public List<Menu> getUserMenuList() {
		return userMenuList;
	}


	public void setUserMenuList(List<Menu> userMenuList) {
		this.userMenuList = userMenuList;
	}


	public List<Role> getRole() {
		return role;
	}


	public void setRole(List<Role> role) {
		this.role = role;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}


	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}


	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


	
}