package com.litt.nic.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.maintenance;
import com.litt.nic.pojo.manager;
import com.litt.nic.pojo.repair;
import com.litt.nic.pojo.status;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.service.IMainTenanceService;
import com.litt.nic.service.IManagerService;
import com.litt.nic.service.IRepairService;
import com.litt.nic.service.IStatusService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;

/**
 * 业务反馈
 * 
 * @author Liar
 * 
 */
@Controller
@RequestMapping(value = "/feedback")
public class BusinessFeedback {

	@Autowired
	private ITechSupportService techSupportService;
	@Autowired
	private IRepairService repairService;
	@Autowired
	private IMainTenanceService mainTenanceService;

	@Autowired
	private IStatusService statusService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IManagerService managerService;

	private List<techsupport> techsupportList;
	private List<repair> repairList;
	private List<maintenance> mainTenList;

	List<String> tsStatusList = new ArrayList<String>();
	List<String> tsManagerList = new ArrayList<String>();

	List<String> rpStatusList = new ArrayList<String>();
	List<String> rpManagerList = new ArrayList<String>();

	List<String> tsUserList = new ArrayList<String>();
	List<String> rpUserList = new ArrayList<String>();

	List<String> mtStatusList = new ArrayList<String>();
	List<String> mtManagerList = new ArrayList<String>();
	List<String> mtUserList = new ArrayList<String>();

	/**
	 * 加载所有未完成的信息
	 */
	@RequestMapping(value = "/unfinishedlist")
	public String loadAllUnfinished(HttpServletRequest request,
			HttpServletResponse response) {
		techsupportList = techSupportService.findAllUnfinished();
		repairList = repairService.findAllUnfinished();
		mainTenList = mainTenanceService.findAllUnfinished();

		List<status> listStatus = statusService.findAllStatus();
		request.setAttribute("listStatus", listStatus);

		List<manager> listManager = managerService.selectAllManager();
		request.setAttribute("listManager", listManager);

		getTSLists(request, techsupportList);
		getRPLists(request, repairList);
		getMTLists(request, mainTenList);
		request.setAttribute("tsList", techsupportList);
		request.setAttribute("rpList", repairList);
		request.setAttribute("mtList", mainTenList);
		return "/WEB-INF/views/serviceDock/businessFadeback";
	}

	/**
	 * 根据techsupport_id修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toUpdateStatus")
	public String updateStatus(HttpServletRequest request,
			HttpServletResponse response) {
		try {// 技术支持
			int techsupportId = Integer.parseInt(request
					.getParameter("techsupportId"));
			System.out.println("此时的技术支持id为：" + techsupportId);
			techsupport techsupport = techSupportService
					.findById(techsupportId);
			request.setAttribute("techsupportId", techsupport
					.getTechsupportId().toString());

			System.out.println("========");
			String techsupportEndtime = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date());
			String status_name = request.getParameter("status_name");
			System.out.println(techsupportEndtime + "--" + status_name);
			String manage_name = request.getParameter("manager_name");
			System.out.println(techsupportEndtime + "--" + status_name + "===="
					+ manage_name + "---");
			status status = statusService.findByName(status_name);
			System.out.println(status.getStatusName());
			manager manager = managerService.findByName(manage_name);
			techSupportService.updateEndTime(techsupportId, techsupportEndtime);
			techSupportService.updateStatus_id(techsupportId,
					status.getStatusId());
			techSupportService.updateManager_id(techsupportId,
					manager.getManagerId());
			return "redirect:showUnfinishedList";
		} catch (Exception e) {
			System.out.println("传递参数不是techsupportId");
		}
		try {// 报修
			int repairId = Integer.parseInt(request.getParameter("repairId"));
			System.out.println("此时的设备保修id为：" + repairId);
			String techsupportEndtime = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date());
			request.setAttribute("repairId", repairId);
			List<status> listStatus = statusService.findAllStatus();
			request.setAttribute("listStatus", listStatus);
			List<manager> listManager = managerService.selectAllManager();
			request.setAttribute("listManager", listManager);
			System.out.println("========");
			String status_name = request.getParameter("status_name");
			String manage_name = request.getParameter("manager_name");
			System.out.println("--" + status_name + "====" + manage_name
					+ "---");
			status status = statusService.findByName(status_name);
			System.out.println("status id" + status.getStatusId());
			manager manager = managerService.findByName(manage_name);
			System.out.println("manager id" + manager.getManagerId());
			repairService.updateStatus_id(repairId, status.getStatusId());
			repairService.updateManager_id(repairId, manager.getManagerId());
			return "redirect:showUnfinishedList";
		} catch (Exception e) {
			System.out.println("传递参数不是repairId");
		}
		try {// 日常维护
			int maintenanceId = Integer.parseInt(request
					.getParameter("maintenanceId"));
			System.out.println("maintenanceId" + maintenanceId);
			request.setAttribute("maintenanceId", maintenanceId);
			List<status> listStatus = statusService.findAllStatus();
			request.setAttribute("listStatus", listStatus);
			List<manager> listManager = managerService.selectAllManager();
			request.setAttribute("listManager", listManager);
			String status_name = request.getParameter("status_name");
			String manage_name = request.getParameter("manager_name");
			System.out.println("--" + status_name + "====" + manage_name
					+ "---");
			status status = statusService.findByName(status_name);
			manager manager = managerService.findByName(manage_name);
			mainTenanceService.updateStatus_id(maintenanceId,
					status.getStatusId());
			mainTenanceService.updateManager_id(maintenanceId,
					manager.getManagerId());
			System.out.println("========");
			return "redirect:showUnfinishedList";
		} catch (Exception e) {
			System.out.println("传递参数不是maintenanceId");
		}
		return null;
	}

	public void getTSLists(HttpServletRequest request,
			List<techsupport> techSupportList) {
		for (int i = 0; i < techSupportList.size(); i++) {
			tsStatusList.add(statusService.findById(
					techSupportList.get(i).getStatusId()).getStatusName());
			tsManagerList.add(managerService.findById(
					techSupportList.get(i).getManagerId()).getManagerName());
			tsUserList.add(userService.findById(
					techSupportList.get(i).getUserId()).getUserName());
		}
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsManager", tsManagerList);
		request.setAttribute("tsList", techSupportList);
		request.setAttribute("tsLen", techSupportList.size());
		System.out.println(techSupportList.size());
		request.setAttribute("tsUser", tsUserList);
	}

	public void getRPLists(HttpServletRequest request, List<repair> repairList) {
		rpManagerList.clear();
		for (int i = 0; i < repairList.size(); i++) {
			System.out.println("循环========" + rpManagerList.size());
			rpStatusList.add(statusService.findById(
					repairList.get(i).getStatusId()).getStatusName());
			rpManagerList.add(managerService.findById(
					repairList.get(i).getManagerId()).getManagerName());
			rpUserList.add(userService.findById(repairList.get(i).getUserId())
					.getUserName());
		}
		request.setAttribute("rpList", repairList);
		request.setAttribute("rpStatus", rpStatusList);
		request.setAttribute("rpManager", rpManagerList);
		request.setAttribute("rpLen", rpManagerList.size());
		System.out.println("re========" + rpManagerList.size());
		request.setAttribute("rpUser", rpUserList);
	}

	public void getMTLists(HttpServletRequest request,
			List<maintenance> mainTenList) {
		for (int i = 0; i < mainTenList.size(); i++) {
			mtManagerList.add(managerService.findById(
					mainTenList.get(i).getManagerId()).getManagerName());
			mtStatusList.add(statusService.findById(
					mainTenList.get(i).getStatusId()).getStatusName());
			mtUserList.add(userService.findById(mainTenList.get(i).getUserId())
					.getUserName());
		}
		request.setAttribute("mtList", mainTenList);
		request.setAttribute("mtStatus", mtStatusList);
		request.setAttribute("mtManager", mtManagerList);
		request.setAttribute("mtUser", mtUserList);
	}
}
