package com.litt.wechat.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.litt.nic.pojo.user;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Util.OauthCode_GetUseInfo;

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
	 * @throws IOException
	 */
	@RequestMapping(value = "/adduser")
	public String addUser(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		// GetUserInfo.getUserInfo(WeixinUtil.getAccessToken().getAccessToken(),
		// openid);

		user.setUserName(name);
		user.setUserTelephone(telephone);

		userService.addUser(name, telephone);

		return null;
	}

	/**
	 * @Description: 微信授权登录
	 * @param @param request
	 * @param @param response
	 * @param @param code
	 * @param @param state
	 * @author dapengniao
	 * @date 2016年4月26日 上午9:40:18
	 */
	@RequestMapping("/weixinOauth")
	public void weiXinOauth(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "state", required = true) String state) {
		System.out.println("Code=============" + code
				+ "==========state=======" + state);
		try {
			// 用code取得微信用户的基本信息
			OauthCode_GetUseInfo weixin = new OauthCode_GetUseInfo(code);
			Map<String, String> wmap = weixin.getUserInfo();
			System.out.println("用户昵称================================="
					+ wmap.get("nickname"));

		} catch (Exception e) {
		}
	}
}
