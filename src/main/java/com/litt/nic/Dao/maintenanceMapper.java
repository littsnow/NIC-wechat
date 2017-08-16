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

	List<maintenance> selectMTMultiInfo(@Param("key") String key,
			@Param("val") String val);

	/**
	 * 查询未完成的
	 * 
	 * @return
	 */
	public List<maintenance> findAllUnfinished();

	/**
	 * 修改状态
	 * 
	 * @param maintenanceId
	 * @param status_id
	 */
	public void updateStatus_id(int maintenanceId, int status_id);

	/**
	 * 修改负责人
	 * 
	 * @param maintenanceId
	 * @param manager_id
	 */
	public void updateManager_id(int maintenanceId, int manager_id);

	void updateEndTime(int maintenanceId, String endtime);

	List<maintenance> selectUnFinishedByMuliInfo(@Param("key") String key,
			@Param("val") String val);

	void updateFeedback(int id, String info);

	List<maintenance> selectfinish(String dateString);

	List<maintenance> findFeedback(Integer userId, int i);
}