package com.litt.nic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.managerMapper;
import com.litt.nic.pojo.manager;
import com.litt.nic.service.IManagerService;
@Service
public class ManagerServiceImpl implements IManagerService{
@Autowired
private managerMapper managerMapper;
	
	public manager findById(int id) {
		// TODO Auto-generated method stub
		return managerMapper.selectByPrimaryKey(id);
	}

}
