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
	private techsupportMapper techSupport;
	
	public List<techsupport> findAllTS() {
		
		return techSupport.selectAllTS();
	}

}
