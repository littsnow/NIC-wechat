package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.techsupportMapper;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.service.ITechSupportService;
@Service
public class TechSupporServicetImpl implements ITechSupportService{

	@Autowired
	private techsupportMapper techSupportMapper;
	
	public List<techsupport> findAllTS() {
		
		return techSupportMapper.selectAllTS();
	}

	public List<techsupport> findByMutilInfo(String key, String val) {
		// TODO Auto-generated method stub
		return techSupportMapper.selectTSMultiInfo(key, val);
	}

}
