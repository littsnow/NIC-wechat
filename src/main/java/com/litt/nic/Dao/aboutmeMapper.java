package com.litt.nic.Dao;

import com.litt.nic.pojo.aboutme;

public interface aboutmeMapper {
    int deleteByPrimaryKey(Integer aboutmeId);

    int insert(aboutme record);

    int insertSelective(aboutme record);

    aboutme selectByPrimaryKey(Integer aboutmeId);

    int updateByPrimaryKeySelective(aboutme record);

    int updateByPrimaryKey(aboutme record);
}