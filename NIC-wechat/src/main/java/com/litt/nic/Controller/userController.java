package com.litt.nic.Controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.Service.userService;
import com.litt.nic.pojo.user;

@Controller
@RequestMapping("/user")
public class userController {
	
	@Autowired
	private userService userservice;
	@RequestMapping("/load")
	public String display(HttpServletRequest request,Model model){
		
//		Integer userId = Integer(request.getParameter("id"));
//		String userName = request.getParameter("name");
//		String userTelephone=request.getParameter("tele");
//		String userDepartment=request.getParameter("dapart");
		
//		user userin=new user(userId,userName,userTelephone,userDepartment);
//		user userinformation=new user(5,null,"1111","1","123");
		
//		user userinformation = this.userservice.selectByPrimaryKey(userId);
//		this.userservice.deleteByPrimaryKey(userId);
//		this.userservice.insert(userinformation);
//        System.out.println(userName);
//		user userinformation=this.userservice.selectByName(userName);
		
//        this.userservice.deleteByName(userName);
		
//		userservice.updateByPrimaryKeySelective(userinformation);
//		userservice.insertSelective(userinformation);
//		user userinformation=userservice.selectByConditionOne(userin);
//		List<user> list=new ArrayList<user>();
//		list=userservice.selectByCondition(userin);
//		for (user user : list) {
//			System.out.println(user.getUserId());
//			System.out.println(user.getUserName());
//			System.out.println(user.getUserTelephone());
//			System.out.println(user.getUserDepartment());
//		}
//		return "WEB-INF/username";

//		request.setAttribute("Keeping_WayType", Keeping_WayType);
//		model.addAttribute("list",list);
		
//		model.addAttribute("user", userinformation); 
		
		
		List<user> userlist=userservice.selectAllUser();
		request.setAttribute("userlist", userlist);
        return "WEB-INF/userlist";  
		
	}
	private Integer Integer(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}
