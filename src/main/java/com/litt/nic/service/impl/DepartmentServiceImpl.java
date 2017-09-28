package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.departmentMapper;
import com.litt.nic.pojo.department;
import com.litt.nic.service.IDepartmentService;
@Service
public  class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private  departmentMapper departmentmapper;
	@Override
	public List<department> findAllInfo() {
		return departmentmapper.selectAllInfo();
	}
	
	

}
