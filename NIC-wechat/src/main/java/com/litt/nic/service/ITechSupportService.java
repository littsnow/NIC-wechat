package com.litt.nic.service;

import java.util.List;

import com.litt.nic.pojo.techsupport;

public interface ITechSupportService {
	List<techsupport> findAllTS();

	List<techsupport> findByMutilInfo(String key, String val);

	/**
	 * 查询所有业务未完成的信息
	 */
	public List<techsupport> findAllUnfinished();

	/**
	 * 修改状态
	 */
	public void updateStatus_id(int techsupport_id, int status_id);

	/**
	 * 修改负责人
	 * 
	 * @param techsupport_id
	 * @param manager_id
	 */
	public void updateManager_id(int techsupport_id, int manager_id);

	public techsupport findById(Integer techsupportId);

	public void updateEndTime(int techsupport_id, String endtime);
}
