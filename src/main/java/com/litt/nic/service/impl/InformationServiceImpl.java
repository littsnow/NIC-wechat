package com.litt.nic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.informationMapper;
import com.litt.nic.pojo.information;
import com.litt.nic.service.IInformationService;

@Service
public class InformationServiceImpl implements IInformationService {

	@Autowired
	private informationMapper informationMapper;

	@Override
	public void addnews(information information) {
		informationMapper.insert(information);
	}

}
