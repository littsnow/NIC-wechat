package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.techsupportMapper;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.pojo.type;
import com.litt.nic.service.ITechSupportService;

@Service
public  class TechSupporServicetImpl implements ITechSupportService {

	@Autowired
	private techsupportMapper techSupportMapper;

	public List<techsupport> findAllTS() {

		return techSupportMapper.selectAllTS();
	}

	public List<techsupport> findByMutilInfo(String key, String val) {
		// TODO Auto-generated method stub
		return techSupportMapper.selectTSMultiInfo(key, val);
	}

	@Override
	public List<techsupport> findAllUnfinished() {
		List<techsupport> List = techSupportMapper.findAllUnfinished();
		for (techsupport techsupport : List) {

			System.out.println("未完成的状态为：" + techsupport.getStatusId());
		}
		System.out.println("执行了service的方法");
		return List;
	}

	@Override
	public techsupport findById(Integer techsupportId) {

		return techSupportMapper.selectByPrimaryKey(techsupportId);
	}

	@Override
	public void updateStatus_id(int techsupport_id, int status_id) {
		techSupportMapper.updateStatus_id(techsupport_id, status_id);

	}

	@Override
	public void updateManager_id(int techsupport_id, int manager_id) {

		techSupportMapper.updateManager_id(techsupport_id, manager_id);
	}

	@Override
	public void updateEndTime(int techsupport_id, String endtime) {
		techSupportMapper.updateEndTime(techsupport_id, endtime);

	}

	@Override
	public List<techsupport> findUnFinishedTSByMultiInfo(String key, String val) {
		// TODO Auto-generated method stub
		return techSupportMapper.selectUnFinishedTSByMultiInfo(key, val);
	}

	public void updateFeedback(int id, String info) {

		techSupportMapper.updateFeedback(id, info);
	}

	@Override
	public List<techsupport> findByEnd(String dateString) {
		// TODO Auto-generated method stub
		System.out.println("reservice");
		return techSupportMapper.selectfinish(dateString);
	}

	@Override
	public void addtech(techsupport techsupport) {

		techSupportMapper.insert(techsupport);
	}

	@Override
	public List<techsupport> findFeedback(Integer userId, int i) {

		return techSupportMapper.findFeedback(userId, i);
	}

	@Override
	public List<techsupport> findByType(String type) {
		// TODO Auto-generated method stub
		return techSupportMapper.selectByType(type);
	}

	
}