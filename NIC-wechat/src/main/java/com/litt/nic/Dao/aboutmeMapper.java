package com.litt.nic.Dao;

import com.litt.nic.pojo.aboutme;

public interface aboutmeMapper {
    int insert(aboutme record);

    int insertSelective(aboutme record);
}