package com.litt.nic.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.litt.nic.pojo.maintenance;

public interface maintenanceMapper {
	 int deleteByPrimaryKey(Integer maintenanceId);

	    int insert(maintenance record);

	    int insertSelective(maintenance record);

	    maintenance selectByPrimaryKey(Integer maintenanceId);

	    int updateByPrimaryKeySelective(maintenance record);

	    int updateByPrimaryKey(maintenance record);
    
    List<maintenance> selectAllMT();
    List<maintenance> selectMTMultiInfo(@Param("key")String key,@Param("val")String val);
}