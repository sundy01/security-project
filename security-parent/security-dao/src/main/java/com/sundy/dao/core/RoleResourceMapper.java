package com.sundy.dao.core;

import java.util.List;

import com.sundy.core.RoleResource;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
    
    public List<RoleResource> findAllRoleResource();
}