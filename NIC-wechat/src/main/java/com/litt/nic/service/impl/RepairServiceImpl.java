package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.repairMapper;
import com.litt.nic.pojo.repair;
import com.litt.nic.service.IRepairService;

@Service
public class RepairServiceImpl implements IRepairService {
	@Autowired
	private repairMapper repairMap;

	public List<repair> findAllRP() {
		// TODO Auto-generated method stub
		return repairMap.selectAllRP();
	}

	public List<com.litt.nic.pojo.repair> findByMutilInfo(String key,
			String value) {
		return repairMap.selectRPMultiInfo(key, value);
	}

	@Override
	public List<repair> findAllUnfinished() {
		return repairMap.findAllUnfinished();
	}

	@Override
	public repair findById(int id) {
		return repairMap.selectByPrimaryKey(id);
	}

	@Override
	public void updateStatus_id(int repairId, int status_id) {
		repairMap.updateStatus_id(repairId, status_id);

	}

	@Override
	public void updateManager_id(int repairId, int manager_id) {
		repairMap.updateManager_id(repairId, manager_id);
	}

}
