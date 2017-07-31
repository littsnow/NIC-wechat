package com.litt.wechat.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.maintenance;
import com.litt.nic.pojo.repair;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.pojo.user;
import com.litt.nic.service.IMainTenanceService;
import com.litt.nic.service.IRepairService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Dispatcher.EventDispatcher;

/**
 * 用户身份信息的完善
 * 
 * @author litt
 * 
 */
@Controller
@RequestMapping(value = "/work")
public class WorkController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITechSupportService techSupportService;
	@Autowired
	private IRepairService repairService;
	@Autowired
	private IMainTenanceService mainTenanceService;

	private techsupport techsupport;
	private repair repair;
	private maintenance maintenance;

	private user user;

	@RequestMapping(value = "/addwork")
	public String addWork(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		// 获取当前提交信息的联系人
		String openid = EventDispatcher.openid;
		user = userService.findByOpenid(openid);
		System.out.println(user.getUserName() + "===================");
		System.out.println("openid==========================" + openid);
		int status = Integer.parseInt(request.getParameter("worktype"));
		System.out.println("业务类型是几呢======" + status);
		String uptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		System.out.println("当前时间是：" + uptime);
		// 提交信息
		switch (status) {
		case 1:
			System.out.println(user.getUserDepartment());

			// techsupport = new techsupport(null, user.getUserId(),
			// user.getUserDepartment(),
			// request.getParameter("devicename"),
			// request.getParameter("location"),
			// request.getParameter("description"), null, null, uptime,
			// null, 1, null);
			techsupport techsupport = new techsupport();
			techsupport.setTechsupportDepartment(user.getUserDepartment());
			techsupport.setTechsupportDescribe(request
					.getParameter("description"));
			techsupport
					.setTechsupportLocation(request.getParameter("location"));
			techsupport.setStatusId(1);
			techsupport.setTechsupportDevicename(request
					.getParameter("devicename"));
			techsupport.setTechsupportUptime(uptime);
			techsupport.setUserId(user.getUserId());
			techSupportService.addtech(techsupport);
			break;
		case 2:

			repair repair = new repair();
			repair.setRepairDepartment(user.getUserDepartment());
			repair.setRepairDescribe(request.getParameter("description"));
			repair.setRepairLocation(request.getParameter("location"));
			repair.setStatusId(1);
			repair.setRepairDevicename(request.getParameter("devicename"));
			repair.setUserId(user.getUserId());
			repair.setRepairUptime(uptime);
			repairService.addrepair(repair);

			break;
		case 3:
			maintenance maintenance = new maintenance();
			maintenance.setMaintenanceDepartment(user.getUserDepartment());
			maintenance.setMaintenanceDescribe(request
					.getParameter("description"));
			maintenance.setMaintenanceDevicename(request
					.getParameter("devicename"));
			maintenance
					.setMaintenanceLocation(request.getParameter("location"));
			maintenance.setStatusId(1);
			maintenance.setUserId(user.getUserId());
			maintenance.setMaintenanceUptime(uptime);
			mainTenanceService.addmaintenance(maintenance);
			break;

		default:
			break;
		}

		return null;

	}

	public techsupport getTechsupport() {
		return techsupport;
	}

	public void setTechsupport(techsupport techsupport) {
		this.techsupport = techsupport;
	}

}
