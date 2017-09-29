package com.litt.nic.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.service.ITechSupportService;

@Controller
@RequestMapping(value = "/load")
public class LoadPicNote {

	@Autowired
	private ITechSupportService techSupportService;

	@RequestMapping("/picture")
	public String loadpicture(HttpServletRequest request,
			HttpServletResponse response, String pName, String note,String phone)
			throws IOException {
		note = new String(note.getBytes("iso8859-1"), "utf-8");
		System.out.println(pName + "     " + note+" "+phone);
		request.setCharacterEncoding("UTF-8");// 传值编码
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("pic", pName);
        request.setAttribute("phone", phone);
		request.setAttribute("note", note);
		return "/jsp/loadpicture";
	}
}
