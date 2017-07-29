package com.litt.nic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.Dao.repairMapper;
import com.litt.nic.pojo.department;
import com.litt.nic.pojo.maintenance;
import com.litt.nic.pojo.repair;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.service.IDepartmentService;
import com.litt.nic.service.IMainTenanceService;
import com.litt.nic.service.IManagerService;
import com.litt.nic.service.IRepairService;
import com.litt.nic.service.IStatusService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;

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
	@Autowired
	private IUserService userService;
	@Autowired
	private IDepartmentService departmentService;
	
	private List<techsupport> techSupportList;
	private List<repair> repairList;
	private List<maintenance> mainTenList;
	
	List<String> tsStatusList = new ArrayList<String>();
	List<String> tsManagerList = new ArrayList<String>();
	List<String> tsUserList=new ArrayList<String>();
	
	List<String> rpStatusList=new ArrayList<String>();
	List<String> rpManagerList=new ArrayList<String>();
	List<String> rpUserList=new ArrayList<String>();
	
	List<String> mtStatusList=new ArrayList<String>();
	List<String> mtManagerList=new ArrayList<String>();
	List<String> mtUserList=new ArrayList<String>();
	
	List<department> departList=new ArrayList<department>();
	List<String> departNameList=new ArrayList<String>();
	
	/**
	 * 搜索全部信息
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value="/showList")
	public String ShowServiceList(HttpServletRequest request, HttpServletResponse response)
	{
		 departList=departmentService.findAllInfo();
		 
		 techSupportList=techSupportService.findAllTS();
		 repairList=repairService.findAllRP();
		 mainTenList=mainTenanceService.findAllMT();
		if(techSupportList.isEmpty()&&repairList.isEmpty()&&mainTenList.isEmpty())
		{
			return "/jsp/error/null";
			
		}else{
			getDPNameList(request,departList);
		 getMTLists(request, mainTenList);
		getRPLists(request, repairList);
		getTSLists(request, techSupportList);
		return "/WEB-INF/views/serviceDock/serviceDockList";
		}
			
	
	}
	/**
	 * 搜索表单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/searchLists")
	public String searchByInfo(HttpServletRequest request,HttpServletResponse response) 
	{
	    String key=request.getParameter("key");
		String value=request.getParameter("val");
		System.out.println("key="+key+"value="+value);
		if(key!=null)
		{
			//key是否匹配给定的正则表达式
			if(key.equals("service"))
			{
				switch (value) {
				case "技术支持":
					techSupportList=techSupportService.findByMutilInfo(key, value);
					getTSLists(request,techSupportList);
					break;
				case "设备报修":
					 repairList=repairService.findByMutilInfo(key, value);
					 getRPLists(request, repairList);
					 break;
				case "日常运维":
					mainTenList=mainTenanceService.findByMutiInfo(key, value);
					getMTLists(request, mainTenList);
				default:
					break;
				}
				return "/WEB-INF/views/serviceDock/serviceDockList"; 
			}else
			{
				
				techSupportList=techSupportService.findByMutilInfo(key, value);
				 repairList=repairService.findByMutilInfo(key, value);
				 mainTenList=mainTenanceService.findByMutiInfo(key, value);
				 getTSLists(request,techSupportList);
				 getRPLists(request, repairList);
				 getMTLists(request, mainTenList);
				 departList=departmentService.findAllInfo();
				 getDPNameList(request,departList);
				 
			}
		return "/WEB-INF/views/serviceDock/serviceDockList"; 
	}else
	  {
		return ShowServiceList(request,response);
	   }
	}

	public void getTSLists(HttpServletRequest request,List<techsupport> techSupportList)
	{
		for(int i=0;i<techSupportList.size();i++)
		{
			tsStatusList.add(statusService.findById(techSupportList.get(i).getStatusId()).getStatusName());
			tsManagerList.add(managerService.findById(techSupportList.get(i).getManagerId()).getManagerName());
			tsUserList.add(userService.findById(techSupportList.get(i).getUserId()).getUserName());
		}
		System.out.println("tsList="+techSupportList);
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsManager", tsManagerList);
		request.setAttribute("tsList", techSupportList);
		request.setAttribute("tsUser", tsUserList);
		request.setAttribute("tsLen", techSupportList.size());
	}
	public void getRPLists(HttpServletRequest request,List<repair> repairList)
	{
		 for(int i=0;i<repairList.size();i++)
			{
				rpStatusList.add(statusService.findById(repairList.get(i).getStatusId()).getStatusName());
				rpManagerList.add(managerService.findById(repairList.get(i).getManagerId()).getManagerName());
				rpUserList.add(userService.findById(repairList.get(i).getUserId()).getUserName());
			}
		 System.out.println("rpList="+repairList);
			request.setAttribute("rpList", repairList);
			request.setAttribute("rpStatus", rpStatusList);
			request.setAttribute("rpManager", rpManagerList);
			request.setAttribute("rpUser", rpUserList);
			request.setAttribute("rpLen", repairList.size());
	}
	public void getMTLists(HttpServletRequest request,List<maintenance> mainTenList)
	{
		for(int i=0;i<mainTenList.size();i++)
		{
			mtManagerList.add(managerService.findById(mainTenList.get(i).getManagerId()).getManagerName());
			mtStatusList.add(statusService.findById(mainTenList.get(i).getStatusId()).getStatusName());
			mtUserList.add(userService.findById(mainTenList.get(i).getUserId()).getUserName());
		}
		request.setAttribute("mtList", mainTenList);
		request.setAttribute("mtStatus", mtStatusList);
		request.setAttribute("mtManager", mtManagerList);
		request.setAttribute("mtUser", mtUserList);
		request.setAttribute("mtLen", mainTenList.size());
		
	}
	public void getDPNameList(HttpServletRequest request,List<department> departList)
	{
		for(int i=0;i<departList.size();i++)
		{
			departNameList.add(departList.get(i).getDepartmentName());
		}
		request.setAttribute("dpNameList", departNameList);
	}
	

}
