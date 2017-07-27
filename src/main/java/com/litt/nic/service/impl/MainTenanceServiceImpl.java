package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.maintenanceMapper;
import com.litt.nic.pojo.maintenance;
import com.litt.nic.service.IMainTenanceService;
@Service
public class MainTenanceServiceImpl implements IMainTenanceService{
   @Autowired
	private maintenanceMapper mainTenanceMapper;

	public List<maintenance> findAllMT() {
		// TODO Auto-generated method stub
		return mainTenanceMapper.selectAllMT();
	}

	public List<com.litt.nic.pojo.maintenance> findByMutiInfo(String key, String val) {
		// TODO Auto-generated method stub
		return mainTenanceMapper.selectMTMultiInfo(key, val);
	}
	
}
