package com.litt.nic.Dao;

import com.litt.nic.pojo.type;

public interface typeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(type record);

    int insertSelective(type record);

    type selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(type record);

    int updateByPrimaryKey(type record);
}