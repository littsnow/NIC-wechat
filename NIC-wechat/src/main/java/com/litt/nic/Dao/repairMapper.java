package com.litt.nic.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.litt.nic.pojo.repair;

public interface repairMapper {
	int deleteByPrimaryKey(Integer repairId);

	int insert(repair record);

	int insertSelective(repair record);

	repair selectByPrimaryKey(Integer repairId);

	int updateByPrimaryKeySelective(repair record);

	int updateByPrimaryKey(repair record);

	List<repair> selectAllRP();

	List<repair> selectRPMultiInfo(@Param("key") String key,
			@Param("val") String val);

	/**
	 * 查询未完成的
	 * 
	 * @return
	 */
	public List<repair> findAllUnfinished();

	/**
	 * 修改状态
	 * 
	 * @param repairId
	 * @param status_id
	 */
	public void updateStatus_id(int repairId, int status_id);

	/**
	 * 修改负责人
	 * 
	 * @param repairId
	 * @param manager_id
	 */
	public void updateManager_id(int repairId, int manager_id);
	
	List<repair> selectUnfinishedRPByMultiInfo(@Param("key") String key,
			@Param("val") String val);
}