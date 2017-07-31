package com.litt.wechat.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.user;
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

	private user user;

	/**
	 * 搜索全部信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/adduser")
	public String addUser(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");

		String openid = EventDispatcher.openid;
		System.out.println("openid+=" + openid);
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
		return null;
	}

}
