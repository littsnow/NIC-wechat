package com.litt.nic.service;

import java.util.List;

import com.litt.nic.pojo.manager;

public interface IManagerService {
	manager findById(int id);

	List<manager> selectAllManager();

	manager findByNamePsw(String name, String psw);

	void insert(manager model);

	void deleteByPrimaryKey(Integer managerId);

	manager selectByPrimaryKey(Integer managerId);

	void updateByPrimaryKeySelective(manager model);

	manager findByName(String manage_name);
}
