package com.litt.nic.Dao;

import com.litt.nic.pojo.suggest;

public interface suggestMapper {
    int deleteByPrimaryKey(Integer suggestId);

    int insert(suggest record);

    int insertSelective(suggest record);

    suggest selectByPrimaryKey(Integer suggestId);

    int updateByPrimaryKeySelective(suggest record);

    int updateByPrimaryKey(suggest record);
}