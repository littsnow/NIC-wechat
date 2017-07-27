package com.litt.nic.Dao;

import java.util.List;

import com.litt.nic.pojo.manager;

public interface managerMapper {
	//根据ID删除
    int deleteByPrimaryKey(Integer managerId);
    //插入
    int insert(manager record);

    int insertSelective(manager record);
    //根据ID查询
    manager selectByPrimaryKey(Integer managerId);
    //查找所有管理员
    List<manager> selectAllManager();
    //有选择的插入
    int updateByPrimaryKeySelective(manager record);
  
    int updateByPrimaryKey(manager record);
}