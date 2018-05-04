package com.sundy.dao.core;

import java.util.List;

import com.sundy.core.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    public List<Role> findRoleByUserName(String userName);
    
    public List<Role> findRoleByMenuId(Integer menuId);
    
}