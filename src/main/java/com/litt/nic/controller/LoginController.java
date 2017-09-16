package com.litt.nic.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.manager;
import com.litt.nic.service.IManagerService;

/**
 * 业务对接
 * 
 * @author litt
 * 
 */
@Controller
@RequestMapping(value = "/manager")
public class LoginController {

	@Autowired
	private IManagerService managerService;
	private HttpSession session;

	/**
	 * 搜索全部信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login")
	public String managerLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		session = request.getSession();

		String name = request.getParameter("username");
		String psw = request.getParameter("password");
		String msg = "用户名或密码有误！";
		manager managers = managerService.findByNamePsw(name, psw);
		request.getSession().setAttribute("loginmanager", managers);
		if (managers != null) {
			if (managers.getManagerTyp() == 0)
				return "/WEB-INF/views/manager/managerindex";
			else {
				return "/index";
			}

		} else {
			request.setAttribute("msg", msg);
			return "/login";
		}
	}
}
