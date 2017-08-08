package com.litt.nic.Dao;

import java.util.List;

import com.litt.nic.pojo.manager;

public interface managerMapper {
	int deleteByPrimaryKey(Integer managerId);

	int insert(manager record);

	int insertSelective(manager record);

	manager selectByPrimaryKey(Integer managerId);

	manager selectByNamePsw(String name, String psw);

	int updateByPrimaryKeySelective(manager record);

	int updateByPrimaryKey(manager record);

	List<manager> selectAllManager();

	/**
	 * 按名字查
	 */
	manager findByName(String name);
}
