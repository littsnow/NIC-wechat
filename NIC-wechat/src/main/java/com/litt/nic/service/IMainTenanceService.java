package com.litt.nic.service;

import java.util.List;

import com.litt.nic.pojo.maintenance;

public interface IMainTenanceService {
	List<maintenance> findAllMT();

	List<maintenance> findByMutiInfo(String key, String val);

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

	List<maintenance> selectUnFinishedByMuliInfo(String key, String val);

	void updateFeedback(int id, String info);

	List<maintenance> findByEnd(String dateString);

	void addmaintenance(com.litt.nic.pojo.maintenance maintenance);
}
