package com.litt.nic.Dao;

import com.litt.nic.pojo.manager;

public interface managerMapper {
    int deleteByPrimaryKey(Integer managerId);

    int insert(manager record);

    int insertSelective(manager record);

    manager selectByPrimaryKey(Integer managerId);

    int updateByPrimaryKeySelective(manager record);

    int updateByPrimaryKey(manager record);
}