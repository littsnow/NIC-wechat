package com.litt.nic.Dao;

import com.litt.nic.pojo.repair;

public interface repairMapper {
    int deleteByPrimaryKey(Integer repairId);

    int insert(repair record);

    int insertSelective(repair record);

    repair selectByPrimaryKey(Integer repairId);

    int updateByPrimaryKeySelective(repair record);

    int updateByPrimaryKey(repair record);
}