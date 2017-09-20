package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.department;
import com.litt.nic.pojo.user;
import com.litt.nic.service.IDepartmentService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Dispatcher.EventDispatcher;
import com.litt.wechat.Util.Token.WeixinUtil;
import com.litt.wechat.Util.User.GetUserInfo;

/**
 * 用户身份信息的完善
 * 
 * @author litt
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IDepartmentService departmentService;

	private user user;

	/**
	 * 加载完善用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadInfo")
	public String loadInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 加载页面
		List<department> listDepartment = departmentService.findAllInfo();
		for (department department : listDepartment) {
			System.out.println(department.getDepartmentName());
		}
		request.setAttribute("listDepartment", listDepartment);
		// 判断
		String openid = request.getParameter("openid");
		System.out.println("openid=" + openid);
		user DataBaseUser = userService.findByOpenid(openid);
		// 数据库已存在此人
		if (DataBaseUser != null) {
			System.out.println(DataBaseUser.getUserName());
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('此用户名已存在,请不要重复绑定！');");
			out.println("history.back();");
			out.println("</script>");

		}
		request.setAttribute("openid", openid);
		return "/jsp/user_info";
	}

	/**
	 * 搜索全部信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/adduser")
	public String addUser(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {

		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String depart = request.getParameter("department");
		System.out.println("depart----" + depart);
		if ("0".equals(depart) || "".equals(name) || "".equals(telephone)) {
			System.out.println("depart----" + depart);
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请把信息填写完整！');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			String openid = request.getParameter("openid");
			System.out.println("openid=" + openid);
			user DataBaseUser = userService.findByOpenid(openid);
			// 数据库已存在此人
			if (DataBaseUser != null) {
				System.out.println(DataBaseUser.getUserName());
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('此用户名已存在,请不要重复绑定！');");
				out.println("history.back();");
				out.println("</script>");
			} else {
				user = GetUserInfo.getUserInfo(WeixinUtil.getAccessToken()
						.getAccessToken(), openid);
				user.setUserName(request.getParameter("name"));
				user.setUserTelephone(request.getParameter("telephone"));
				user.setUserDepartment(request.getParameter("department"));

				System.out.println("OpenID：" + user.getUserOpenid());
				System.out.println("关注状态：" + user.getUserSubscribe());
				System.out.println("关注时间：" + user.getUserSubscribetime());
				System.out.println("昵称：" + user.getUserNickname());
				System.out.println("性别：" + user.getUserSex());
				System.out.println("国家：" + user.getUserCountry());
				System.out.println("省份：" + user.getUserProvince());
				System.out.println("城市：" + user.getUserCity());
				System.out.println("语言：" + user.getUserLanguage());
				System.out.println("头像：" + user.getUserHeadimgurl());

				// 添加微信用户到数据库
				userService.addUser(user);
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('添加成功！');");
				// out.println("history.back();");
				out.println("</script>");
				request.setAttribute("openid", openid);
				return "/jsp/work_info";
			}

		}
		System.out.println("name=" + name + "tele" + telephone);
		return null;
	}

}
