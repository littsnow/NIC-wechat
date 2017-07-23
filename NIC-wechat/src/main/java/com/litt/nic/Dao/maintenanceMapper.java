package com.litt.nic.Dao;

import com.litt.nic.pojo.maintenance;

public interface maintenanceMapper {
    int deleteByPrimaryKey(Integer maintenanceId);

    int insert(maintenance record);

    int insertSelective(maintenance record);

    maintenance selectByPrimaryKey(Integer maintenanceId);

    int updateByPrimaryKeySelective(maintenance record);

    int updateByPrimaryKey(maintenance record);
}