package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.statusMapper;
import com.litt.nic.pojo.status;
import com.litt.nic.service.IStatusService;

@Service
public class StatusServiceImpl implements IStatusService {

	@Autowired
	private statusMapper statusmapper;

	public status findById(int id) {

		return statusmapper.selectByPrimaryKey(id);
	}

	@Override
	public List<status> findAllStatus() {

		return statusmapper.findAllStatus();
	}

	@Override
	public status findByName(String name) {
		return statusmapper.findByName(name);
	}

}
