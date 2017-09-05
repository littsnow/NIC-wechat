package com.litt.nic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.suggest;
import com.litt.nic.service.ISuggestService;
import com.litt.nic.service.IUserService;

@Controller
@RequestMapping(value = "/suggest")
public class ShowSuggestController {

	@Autowired
	private ISuggestService suggestService;
	@Autowired
	private IUserService userService;
	List<String> userList = new ArrayList<String>();;

	/**
	 * 跳转到管理留言页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toshowsuggest")
	public String toshow(HttpServletRequest request,
			HttpServletResponse response) {
		List<suggest> suggests = suggestService.searchAll();

		getUserNameList(request, suggests);
		request.setAttribute("suggests", suggests);
		return "/WEB-INF/views/work/showsuggest";

	}

	/**
	 * 删除留言
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String deleteSuggest(HttpServletRequest request,
			HttpServletResponse response) {

		suggestService.deletesuggest(Integer.parseInt(request
				.getParameter("suggestId")));
		return "redirect:/suggest/toshowsuggest";

	}

	/**
	 * 批量删除留言
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/batchdelete")
	public String deleteLotSuggest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int suggestId = 0;

		String[] suggestIdArray = request.getParameterValues("suggestId");
		for (int i = 0; i < suggestIdArray.length; i++) {
			System.out.println(suggestIdArray[i] + "---------------");
			suggestId = Integer.parseInt(suggestIdArray[i]);
			suggestService.deletesuggest(suggestId);
		}
		return "redirect:/suggest/toshowsuggest";

	}

	public void getUserNameList(HttpServletRequest request,
			List<suggest> suggesttList) {
		for (int i = 0; i < suggesttList.size(); i++) {

			userList.add(userService.findById(suggesttList.get(i).getUserId())
					.getUserName());

			request.setAttribute("user", userList);
		}
	}

}
