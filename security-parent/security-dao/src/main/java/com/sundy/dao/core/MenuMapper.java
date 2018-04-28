package com.sundy.dao.core;

import java.util.List;

import com.sundy.core.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    public List<Menu> loadMenuByParentId(Integer parentId);
}