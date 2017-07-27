package com.litt.nic.Service;

import java.util.List;

import com.litt.nic.pojo.manager;

public interface managerService {
	int insert(manager record);
	int deleteByPrimaryKey(Integer managerId);
	int updateByPrimaryKeySelective(manager record);
	List<manager> selectAllManager();
	manager selectByPrimaryKey(Integer managerId);
}
