package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.maintenanceMapper;
import com.litt.nic.pojo.maintenance;
import com.litt.nic.service.IMainTenanceService;

@Service
public class MainTenanceServiceImpl implements IMainTenanceService {
	@Autowired
	private maintenanceMapper mainTenanceMapper;

	public List<maintenance> findAllMT() {
		// TODO Auto-generated method stub
		return mainTenanceMapper.selectAllMT();
	}

	public List<com.litt.nic.pojo.maintenance> findByMutiInfo(String key,
			String val) {
		// TODO Auto-generated method stub
		return mainTenanceMapper.selectMTMultiInfo(key, val);
	}

	/**
	 * 查询未完成的
	 * 
	 * @return
	 */
	@Override
	public List<maintenance> findAllUnfinished() {
		return mainTenanceMapper.findAllUnfinished();
	}

	@Override
	public void updateStatus_id(int maintenanceId, int status_id) {
		mainTenanceMapper.updateStatus_id(maintenanceId, status_id);
	}

	@Override
	public void updateManager_id(int maintenanceId, int manager_id) {
		mainTenanceMapper.updateManager_id(maintenanceId, manager_id);
	}

	@Override
	public List<maintenance> selectUnFinishedByMuliInfo(String key, String val) {
		return mainTenanceMapper.selectUnFinishedByMuliInfo(key, val);
	}

	public void updateFeedback(int id, String info) {

		mainTenanceMapper.updateFeedback(id, info);
	}

	@Override
	public List<maintenance> findByEnd(String dateString) {
		// TODO Auto-generated method stub
		return mainTenanceMapper.selectfinish(dateString);
	}

	@Override
	public void addmaintenance(maintenance maintenance) {
		// TODO Auto-generated method stub
		mainTenanceMapper.insert(maintenance);
	}

	@Override
	public void updateEndTimeById(int maintenanceId, String endtime) {
		mainTenanceMapper.updateEndTime(maintenanceId, endtime);

	}

	@Override
	public List<maintenance> findFeedback(Integer userId, int i) {

		return mainTenanceMapper.findFeedback(userId, i);
	}

}
