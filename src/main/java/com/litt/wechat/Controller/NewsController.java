package com.litt.wechat.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.information;
import com.litt.wechat.Util.Splider.ExtractNewInfomationUtil;
import com.litt.wechat.Util.Splider.ExtractUtil;
import com.litt.wechat.Util.Splider.RuleUtil;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

	@RequestMapping(value = "/extract")
	public String extractInformation(HttpServletRequest request,
			HttpServletResponse response) {
		List<information> extracts = null;

		RuleUtil rule = new RuleUtil(
				"http://wlzx.tit.edu.cn/list.jsp?urltype=tree.TreeTempUrl&wbtreeid=1013",
				new String[] {}, new String[] {}, "winstyle930566043_18579",
				RuleUtil.CLASS, RuleUtil.GET);
		try {
			extracts = ExtractNewInfomationUtil.extract(rule);
			// rule.print(extracts);//后台打印测试
			System.out.println("连接地址：" + extracts.get(0).getInformationLink());
			System.out.println("标题:" + extracts.get(0).getInformationTitle());
			System.out.println("时间：" + extracts.get(0).getInformationTime());

		} catch (Exception e) {
			System.out.println("参数异常");
		}

		RuleUtil ruleArtical = new RuleUtil(extracts.get(0)
				.getInformationLink(), new String[] {}, new String[] {},
				"winstyle930566043_18580", RuleUtil.CLASS, RuleUtil.GET);
		try {
			String extractsnews = ExtractUtil.extract(ruleArtical);
			request.setAttribute("str", extractsnews);
			System.out.println(extractsnews);
			// return extracts;
		} catch (Exception e) {
			System.out.println("参数异常");
		}

		return "/jsp/hotnews";
	}
}
