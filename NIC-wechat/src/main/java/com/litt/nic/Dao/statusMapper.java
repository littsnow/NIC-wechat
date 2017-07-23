package com.litt.nic.Dao;

import com.litt.nic.pojo.status;

public interface statusMapper {
    int deleteByPrimaryKey(Integer statusId);

    int insert(status record);

    int insertSelective(status record);

    status selectByPrimaryKey(Integer statusId);

    int updateByPrimaryKeySelective(status record);

    int updateByPrimaryKey(status record);
}