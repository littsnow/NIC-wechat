package com.litt.nic.Dao;

import com.litt.nic.pojo.information;

public interface informationMapper {
    int deleteByPrimaryKey(Integer informationId);

    int insert(information record);

    int insertSelective(information record);

    information selectByPrimaryKey(Integer informationId);

    int updateByPrimaryKeySelective(information record);

    int updateByPrimaryKey(information record);
}