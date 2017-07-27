package com.litt.nic.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.managerMapper;
import com.litt.nic.Service.managerService;
import com.litt.nic.pojo.manager;
@Service("managerService")
public class managerServiceImpl implements managerService{
	
	@Autowired
	managerMapper managermapper; 
	@Override
	public int insert(manager record) {
		return managermapper.insert(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer managerId) {
		return managermapper.deleteByPrimaryKey(managerId);
	}
	@Override
	public List<manager> selectAllManager() {
		return managermapper.selectAllManager();
	}
	@Override
	public int updateByPrimaryKeySelective(manager record) {
		return managermapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public manager selectByPrimaryKey(Integer managerId) {
		return managermapper.selectByPrimaryKey(managerId);
	}

}
