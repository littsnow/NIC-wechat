package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.repairMapper;
import com.litt.nic.pojo.repair;
import com.litt.nic.service.IRepairService;
@Service
public class RepairServiceImpl implements IRepairService{
@Autowired
	private repairMapper repairMap;
	public List<repair> findAllRP() {
		// TODO Auto-generated method stub
		return repairMap.selectAllRP();
	}
	public List<com.litt.nic.pojo.repair> findByMutilInfo(String key, String value) {
		// TODO Auto-generated method stub
		return repairMap.selectRPMultiInfo(key, value);
	}

}
