package com.litt.nic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.Dao.repairMapper;
import com.litt.nic.pojo.maintenance;
import com.litt.nic.pojo.repair;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.service.IMainTenanceService;
import com.litt.nic.service.IManagerService;
import com.litt.nic.service.IRepairService;
import com.litt.nic.service.IStatusService;
import com.litt.nic.service.ITechSupportService;

/**
 * 业务对接
 * @author ljx
 *
 */
@Controller
@RequestMapping(value="/serviceDock")
public class ServiceDockController {
	
	@Autowired
	private ITechSupportService techSupportService;
	@Autowired
	private IRepairService repairService;
	@Autowired
	private IMainTenanceService mainTenanceService;
	@Autowired
	private IStatusService statusService;
	@Autowired
	private IManagerService managerService;
	
	@SuppressWarnings("null")
	@RequestMapping(value="/showList")
	public String ShowServiceList(HttpServletRequest request, HttpServletResponse response)
	{
		List<techsupport> techSupportList=techSupportService.findAllTS();
		List<repair> repairList=repairService.findAllRP();
		List<maintenance> mainTenList=mainTenanceService.findAllMT();
		List<String> tsStatusList = new ArrayList<String>();
		List<String> tsManagerList = new ArrayList<String>();
		
		List<String> rpStatusList=new ArrayList<String>();
		List<String> rpManagerList=new ArrayList<String>();
		
		List<String> mtStatusList=new ArrayList<String>();
		List<String> mtManagerList=new ArrayList<String>();
		for(int i=0;i<techSupportList.size();i++)
		{
			tsStatusList.add(statusService.findById(techSupportList.get(i).getStatusId()).getStatusName());
			tsManagerList.add(managerService.findById(techSupportList.get(i).getManagerId()).getManagerName());
		}
		
		for(int i=0;i<repairList.size();i++)
		{
			rpStatusList.add(statusService.findById(repairList.get(i).getStatusId()).getStatusName());
			rpManagerList.add(managerService.findById(repairList.get(i).getManagerId()).getManagerName());
		}
		
		for(int i=0;i<mainTenList.size();i++)
		{
			mtManagerList.add(managerService.findById(mainTenList.get(i).getManagerId()).getManagerName());
			mtStatusList.add(statusService.findById(mainTenList.get(i).getStatusId()).getStatusName());
		}
		
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsManager", tsManagerList);
		request.setAttribute("tsList", techSupportList);
		request.setAttribute("rpList", repairList);
		request.setAttribute("rpStatus", rpStatusList);
		request.setAttribute("rpManager", rpManagerList);
		request.setAttribute("mtList", mainTenList);
		request.setAttribute("mtStatus", mtStatusList);
		request.setAttribute("mtManager", mtManagerList);
		
		return "/WEB-INF/views/serviceDock/serviceDockList"; 
	}

}
