package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.suggest;
import com.litt.nic.pojo.user;
import com.litt.nic.service.ISuggestService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Dispatcher.EventDispatcher;

@Controller
@RequestMapping(value = "/suggest")
public class SuggestController {

	private user user;

	@Autowired
	private IUserService userService;
	@Autowired
	private ISuggestService suggestService;

	@RequestMapping(value = "/addsuggest")
	public String addSuggest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		suggest suggest = new suggest();
		String openid = EventDispatcher.openid;
		user = userService.findByOpenid(openid);
		System.out.println(user.getUserName() + "===================");
		System.out.println("openid==========================" + openid);
		suggest.setSuggestTitle(request.getParameter("title"));
		suggest.setSuggestContent(request.getParameter("content"));
		String nowtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		suggest.setSuggestTime(nowtime);
		suggest.setUserId(user.getUserId());

		suggestService.addsuggest(suggest);
		response.setContentType("text/html; charset=UTF-8"); // 转码
		PrintWriter out = response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('提交成功！');");
		out.println("</script>");

		return "/jsp/suggest_info";
	}
}
