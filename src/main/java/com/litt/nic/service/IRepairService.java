package com.litt.nic.service;

import java.util.List;

import com.litt.nic.pojo.repair;

public interface IRepairService {
	List<repair> findAllRP();

	List<repair> findByMutilInfo(String key, String value);

	/**
	 * 查询未完成的
	 * 
	 * @return
	 */
	List<repair> findAllUnfinished();

	repair findById(int id);

	/**
	 * 修改状态
	 * 
	 * @param repairId
	 * @param status_id
	 */
	void updateStatus_id(int repairId, int status_id);

	/**
	 * 修改负责人
	 * 
	 * @param repairId
	 * @param manager_id
	 */
	List<repair> findUnfinishedRPByMultiInfo(String key, String val);

	void updateManager_id(int repairId, int manager_id);

	void updateFeedback(int id, String info);

	List<repair> findByEnd(String dateString);

	void addrepair(com.litt.nic.pojo.repair repair);
}
