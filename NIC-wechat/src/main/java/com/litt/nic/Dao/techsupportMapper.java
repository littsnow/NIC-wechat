package com.litt.nic.Dao;

import com.litt.nic.pojo.techsupport;

public interface techsupportMapper {
    int deleteByPrimaryKey(Integer techsupportId);

    int insert(techsupport record);

    int insertSelective(techsupport record);

    techsupport selectByPrimaryKey(Integer techsupportId);

    int updateByPrimaryKeySelective(techsupport record);

    int updateByPrimaryKey(techsupport record);
}