package com.litt.nic.service;

import java.util.List;

import com.litt.nic.pojo.status;

public interface IStatusService {
	status findById(int id);

	public List<status> findAllStatus();

	public status findByName(String name);
}
