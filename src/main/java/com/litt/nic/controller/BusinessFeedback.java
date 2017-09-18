package com.litt.nic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.department;
import com.litt.nic.pojo.maintenance;
import com.litt.nic.pojo.manager;
import com.litt.nic.pojo.repair;
import com.litt.nic.pojo.status;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.service.IDepartmentService;
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
	@Autowired
	private IDepartmentService departmentService;

	private List<techsupport> techsupportList;
	private List<repair> repairList;
	private List<maintenance> mainTenList;

	List<department> departList = new ArrayList<department>();
	List<String> departNameList = new ArrayList<String>();

	/**
	 * 加载所有未完成的信息
	 */
	@RequestMapping(value = "/unfinishedlist")
	public String loadAllUnfinished(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("执行了未完成加载");
		techsupportList = techSupportService.findAllUnfinished();
		repairList = repairService.findAllUnfinished();
		mainTenList = mainTenanceService.findAllUnfinished();

		List<status> listStatus = statusService.findAllStatus();
		request.setAttribute("listStatus", listStatus);

		List<manager> listManager = managerService.selectAllManager();
		request.setAttribute("listManager", listManager);

		if (techsupportList.isEmpty() && repairList.isEmpty()
				&& mainTenList.isEmpty()) {
			return "/jsp/error/null";

		} else {
			getDPNameList(request);
			getTSLists(request, techsupportList);
			getRPLists(request, repairList);
			getMTLists(request, mainTenList);
			request.setAttribute("tsList", techsupportList);
			request.setAttribute("rpList", repairList);
			request.setAttribute("mtList", mainTenList);
			return "/WEB-INF/views/serviceDock/businessFadeback";
		}
	}

	/**
	 * 根据techsupport_id修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toUpdateStatus")
	public String updateStatus(HttpServletRequest request,
			HttpServletResponse response) {
		int techsupportId = 0;
		int repairId = 0;
		int maintenanceId = 0;
		status status = statusService
				.findByName(request.getParameter("status"));
		manager manager = managerService.findByName(request
				.getParameter("manager"));
		String[] techsupportIdArray = request
				.getParameterValues("techsupportId");
		String[] repairIdArray = request.getParameterValues("repairId");
		String[] maintenanceIdArray = request
				.getParameterValues("maintenanceId");
		String info = request.getParameter("content").trim();
		System.out.println(info);
		if (info.equals("")) {
			System.out.println("全是空格");
		} else {
			System.out.println("不为空吧，要反馈了啊");
			addinfo(request, techsupportIdArray, repairIdArray,
					maintenanceIdArray, info);
		}
		try {// 技术支持

			System.out.println(techsupportIdArray.length + "到底是几个数组");
			for (int i = 0; i < techsupportIdArray.length; i++) {
				techsupportId = Integer.parseInt(techsupportIdArray[i]);
				System.out
						.println("后台获取需要修改的技术支持id数组：" + techsupportIdArray[i]);
				System.out.println("此时的技术支持id为：" + techsupportId);
				techsupport techsupport = techSupportService
						.findById(techsupportId);
				request.setAttribute("techsupportId", techsupport
						.getTechsupportId().toString());

				System.out.println("========");
				String techsupportEndtime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date());

				System.out.println(status.getStatusName());

				techSupportService.updateEndTime(techsupportId,
						techsupportEndtime);
				System.out.println("更新了时间");
				if (status != null) {
					techSupportService.updateStatus_id(techsupportId,
							status.getStatusId());
					System.out.println("更新了状态");
				}
				if (manager != null) {
					techSupportService.updateManager_id(techsupportId,
							manager.getManagerId());
					System.out.println("更新了处理人");
				}
			}
			// return "redirect:unfinishedlist";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("技术支持出现异常");
		}
		try {// 报修

			// System.out.println("repairManagerArray="+repairManagerArray[0]);
			for (int i = 0; i < repairIdArray.length; i++) {
				repairId = Integer.parseInt(repairIdArray[i]);
				System.out.println("后台获取需要修改的设备报修id数组：" + repairIdArray[i]);

				String repair_endtime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date());
				request.setAttribute("repairId", repairId);
				List<status> listStatus = statusService.findAllStatus();
				request.setAttribute("listStatus", listStatus);
				List<manager> listManager = managerService.selectAllManager();
				request.setAttribute("listManager", listManager);
				if (status != null) {
					repairService.updateStatus_id(repairId,
							status.getStatusId());
					System.out.println("更新了状态");
				}
				if (manager != null) {
					repairService.updateManager_id(repairId,
							manager.getManagerId());
					System.out.println("更新了处理人");
				}
				repairService.updateEndTimeById(repairId, repair_endtime);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("设备报修出现异常");
		}
		try {// 日常维护

			for (int i = 0; i < maintenanceIdArray.length; i++) {
				maintenanceId = Integer.parseInt(maintenanceIdArray[i]);
				System.out
						.println("后台获取需要修改的日常维修id数组：" + maintenanceIdArray[i]);
				String maintenance_endtime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date());
				System.out.println("maintenanceId" + maintenanceId);
				request.setAttribute("maintenanceId", maintenanceId);
				List<status> listStatus = statusService.findAllStatus();
				request.setAttribute("listStatus", listStatus);
				List<manager> listManager = managerService.selectAllManager();
				request.setAttribute("listManager", listManager);
				if (status != null) {
					mainTenanceService.updateStatus_id(maintenanceId,
							status.getStatusId());
					System.out.println("日常维护更新状态");
				}
				if (manager != null) {
					mainTenanceService.updateManager_id(maintenanceId,
							manager.getManagerId());
					System.out.println("日常维护更新处理人");
				}
				mainTenanceService.updateEndTimeById(maintenanceId,
						maintenance_endtime);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("日常维护出现异常");

		}

		return "redirect:unfinishedlist";
	}

	/**
	 * 搜索表单，多条件搜索
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/searchLists")
	public String searchByInfo(HttpServletRequest request,
			HttpServletResponse response) {
		List<status> listStatus = statusService.findAllStatus();
		request.setAttribute("listStatus", listStatus);

		List<manager> listManager = managerService.selectAllManager();
		request.setAttribute("listManager", listManager);

		String key = request.getParameter("key");
		String value = request.getParameter("val");
		request.setAttribute("typekey", key);
		request.setAttribute("typeval", value);
		System.out.println("key=" + key + "value=" + value);
		if (key != null) {

			if (key.equals("业务类型")) {
				System.out.println("业务类型-------------");
				switch (value) {
				case "技术支持":
					techsupportList = techSupportService.findAllUnfinished();
					getTSLists(request, techsupportList);
					break;
				case "设备报修":
					repairList = repairService.findAllUnfinished();
					getRPLists(request, repairList);
					break;
				case "日常运维":
					mainTenList = mainTenanceService.findAllUnfinished();
					getMTLists(request, mainTenList);
					break;
				default:
					break;
				}
				return "/WEB-INF/views/serviceDock/businessFadeback";
			} else {
				System.out.println("qita=------------------");
				techsupportList = techSupportService
						.findUnFinishedTSByMultiInfo(key, value);
				getTSLists(request, techsupportList);
				repairList = repairService.findUnfinishedRPByMultiInfo(key,
						value);
				getRPLists(request, repairList);
				mainTenList = mainTenanceService.selectUnFinishedByMuliInfo(
						key, value);
				getMTLists(request, mainTenList);

				getDPNameList(request);

				return "/WEB-INF/views/serviceDock/businessFadeback";
			}

		} else {
			System.out.println("空值的情况-----------");
			return loadAllUnfinished(request, response);
		}
	}

	/**
	 * 跳转到反馈信息页面
	 * 
	 * @return
	 * @throws IOException
	 */

	@SuppressWarnings("unused")
	@RequestMapping(value = "/toaddinfo")
	public String toaddinfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// try {
		/*
		 * int techsupportId = Integer.parseInt(request
		 * .getParameter("techsupportId"));
		 */

		String[] techIdArray = request.getParameterValues("techsupportId");

		List<Integer> teachIdList = new ArrayList<Integer>();
		if (techIdArray != null) {
			for (int i = 0; i < techIdArray.length; i++) {

				teachIdList.add(Integer.parseInt(techIdArray[i]));
				System.out.println("teachId=" + techIdArray[i]);
			}
			request.setAttribute("teachIdList", teachIdList);
		}
		/*
		 * } catch (Exception e) { } try {
		 */
		/* int repairId = Integer.parseInt(request.getParameter("repairId")); */

		String[] rpIdArray = request.getParameterValues("repairId");

		List<Integer> rpIdList = new ArrayList<Integer>();
		if (rpIdArray != null) {
			for (int i = 0; i < rpIdArray.length; i++) {
				rpIdList.add(Integer.parseInt(rpIdArray[i]));
				System.out.println(rpIdArray[i]);
			}

			request.setAttribute("rpIdList", rpIdList);
		}
		/*
		 * } catch (Exception e) { }
		 */
		/* try { */
		/*
		 * int maintenanceId = Integer.parseInt(request
		 * .getParameter("maintenanceId"));
		 */
		String[] mtIdArray = request.getParameterValues("maintenanceId");

		List<Integer> mtIdList = new ArrayList<Integer>();
		if (mtIdArray != null) {
			for (int i = 0; i < mtIdArray.length; i++) {
				mtIdList.add(Integer.parseInt(mtIdArray[i]));
				System.out.println(mtIdArray[i]);
			}

			request.setAttribute("mtIdList", mtIdList);
		}
		/*
		 * } catch (Exception e) { }
		 */
		if ((techIdArray == null) && (rpIdArray == null) && (mtIdArray == null)) {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请选择需要反馈的信息后，再点击反馈');");
			out.println("history.back();");
			out.println("</script>");
			return "redirect:unfinishedlist";
		} else {
			return "/WEB-INF/views/work/feedback_info";
		}
	}

	/**
	 * 提交反馈信息
	 * 
	 */
	@RequestMapping(value = "/addinfo")
	public String addinfo(HttpServletRequest request, String[] techIdArray,
			String[] rpIdArray, String[] mtIdArray, String info) {
		/* try { */
		System.out.println("不为空，并且真该反馈");

		System.out.println(info);
		/* int id = Integer.parseInt(request.getParameter("techsupportId")); */
		int techsupportId = 0;
		techIdArray = request.getParameterValues("techsupportId");
		if (techIdArray != null) {
			for (int i = 0; i < techIdArray.length; i++) {
				System.out.println("techId=" + techIdArray[i]);
				techsupportId = Integer.parseInt(techIdArray[i]);

				System.out.println("技术支持-------" + info + techsupportId);
				techSupportService.updateFeedback(techsupportId, info);
			}

		}
		/*
		 * } catch (Exception e) {
		 * 
		 * } try {
		 */
		// String info = request.getParameter("note");
		// int repairId = Integer.parseInt(request.getParameter("repairId"));
		int repairId = 0;
		rpIdArray = request.getParameterValues("repairId");
		if (rpIdArray != null) {
			for (int i = 0; i < rpIdArray.length; i++) {
				repairId = Integer.parseInt(rpIdArray[i]);
				System.out.println(rpIdArray[i]);
				System.out.println("维修-------" + info + repairId);
				repairService.updateFeedback(repairId, info);
			}
		}

		/*
		 * } catch (Exception e) {
		 * 
		 * } try {
		 */
		// String info = request.getParameter("note");
		// int id = Integer.parseInt(request.getParameter("maintenanceId"));
		mtIdArray = request.getParameterValues("maintenanceId");
		int maintenanceId = 0;
		if (mtIdArray != null) {
			for (int i = 0; i < mtIdArray.length; i++) {
				maintenanceId = Integer.parseInt(mtIdArray[i]);
				System.out.println(mtIdArray[i]);
				System.out.println("运维-------" + info + maintenanceId);
				mainTenanceService.updateFeedback(maintenanceId, info);
			}
		}

		/*
		 * } catch (Exception e) {
		 * 
		 * }
		 */
		return "redirect:unfinishedlist";

	}

	/**
	 * 查找今天已完成的任务列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/finishedlist")
	public String finishedlist(HttpServletRequest request,
			HttpServletResponse response) {

		// 获取到当前的年月日,转换成对应的格式
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		System.out.println(dateString.toString());
		techsupportList = techSupportService.findByEnd(dateString);
		// System.out.println("----"+techsupportList.get(0).getStatusId());
		repairList = repairService.findByEnd(dateString);

		mainTenList = mainTenanceService.findByEnd(dateString);
		if (techsupportList.isEmpty() && repairList.isEmpty()
				&& mainTenList.isEmpty()) {
			return "/jsp/error/null";

		} else {

			getTSLists(request, techsupportList);
			getRPLists(request, repairList);
			getMTLists(request, mainTenList);
			request.setAttribute("tsList", techsupportList);
			request.setAttribute("rpList", repairList);
			request.setAttribute("mtList", mainTenList);
			return "/WEB-INF/views/serviceDock/finished";
		}
	}

	public void getTSLists(HttpServletRequest request,
			List<techsupport> techSupportList) {
		List<String> tsStatusList = new ArrayList<String>();
		List<String> tsUserList = new ArrayList<String>();
		List<String> tsManagerList = new ArrayList<String>();
		// try {
		for (int i = 0; i < techSupportList.size(); i++) {
			tsStatusList.add(statusService.findById(
					techSupportList.get(i).getStatusId()).getStatusName());

			System.out.println(tsStatusList.get(i)
					+ "ppppppppppppppppppp"
					+ techSupportList.get(i).getStatusId()
					+ "---------"
					+ statusService.findById(
							techSupportList.get(i).getStatusId())
							.getStatusName());

			tsUserList.add(userService.findById(
					techSupportList.get(i).getUserId()).getUserName());
			if (techSupportList.get(i).getManagerId() != null) {
				System.out.println("处理人==="
						+ managerService.findById(
								techSupportList.get(i).getManagerId())
								.getManagerName());
				tsManagerList
						.add(managerService.findById(
								techSupportList.get(i).getManagerId())
								.getManagerName());
			} else {
				tsManagerList.add("");
			}
		}
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsList", techSupportList);
		request.setAttribute("tsLen", techSupportList.size());
		System.out.println(techSupportList.size() + "++++++++++++++++++");
		request.setAttribute("tsUser", tsUserList);
		System.out.println(tsUserList.size() + "000000-------000000000000");
		request.setAttribute("tsManagerList", tsManagerList);
		/*
		 * } catch (Exception e) { System.out.println("该业务尚未分配处理人"); }
		 */
	}

	public void getRPLists(HttpServletRequest request, List<repair> repairList) {
		try {
			List<String> rpStatusList = new ArrayList<String>();
			List<String> rpUserList = new ArrayList<String>();
			List<String> rpManagerList = new ArrayList<String>();
			for (int i = 0; i < repairList.size(); i++) {
				rpStatusList.add(statusService.findById(
						repairList.get(i).getStatusId()).getStatusName());
				rpUserList.add(userService.findById(
						repairList.get(i).getUserId()).getUserName());
				if (repairList.get(i).getManagerId() != null) {
					rpManagerList.add(managerService.findById(
							repairList.get(i).getManagerId()).getManagerName());
				} else {
					rpManagerList.add("");
				}
			}
			System.out.println("rpList=" + repairList);
			request.setAttribute("rpList", repairList);
			request.setAttribute("rpStatus", rpStatusList);
			request.setAttribute("rpUser", rpUserList);
			request.setAttribute("rpLen", repairList.size());
			request.setAttribute("rpManagerList", rpManagerList);
		} catch (Exception e) {
			System.out.println("该业务尚未分配处理人");
		}
	}

	public void getMTLists(HttpServletRequest request,
			List<maintenance> mainTenList) {
		List<String> mtStatusList = new ArrayList<String>();
		List<String> mtUserList = new ArrayList<String>();
		List<String> mtManagerList = new ArrayList<String>();
		for (int i = 0; i < mainTenList.size(); i++) {
			mtStatusList.add(statusService.findById(
					mainTenList.get(i).getStatusId()).getStatusName());
			mtUserList.add(userService.findById(mainTenList.get(i).getUserId())
					.getUserName());
			if (mainTenList.get(i).getManagerId() != null) {
				System.out.println("处理人不为空"
						+ managerService.findById(
								mainTenList.get(i).getManagerId())
								.getManagerName());
				mtManagerList.add(managerService.findById(
						mainTenList.get(i).getManagerId()).getManagerName());
			} else {
				mtManagerList.add("");
			}
		}
		request.setAttribute("mtList", mainTenList);
		request.setAttribute("mtStatus", mtStatusList);
		request.setAttribute("mtUser", mtUserList);
		request.setAttribute("mtManagerList", mtManagerList);
	}

	public void getDPNameList(HttpServletRequest request) {
		departNameList.clear();
		departList = departmentService.findAllInfo();
		for (int i = 0; i < departList.size(); i++) {
			departNameList.add(departList.get(i).getDepartmentName());
		}
		request.setAttribute("dpNameList", departNameList);
	}
}
