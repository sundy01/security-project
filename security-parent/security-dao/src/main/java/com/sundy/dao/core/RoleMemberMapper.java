package com.sundy.dao.core;

import com.sundy.core.RoleMember;

public interface RoleMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMember record);

    int insertSelective(RoleMember record);

    RoleMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleMember record);

    int updateByPrimaryKey(RoleMember record);
}