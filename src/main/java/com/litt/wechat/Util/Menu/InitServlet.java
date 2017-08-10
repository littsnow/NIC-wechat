package com.litt.wechat.Util.Menu;

import java.io.IOException;

import javax.servlet.http.HttpServlet;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;

import com.litt.wechat.Menu.Button;
import com.litt.wechat.Menu.ClickButton;
import com.litt.wechat.Menu.Menu;
import com.litt.wechat.Menu.ViewButton;
import com.litt.wechat.Util.HttpUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

public class InitServlet extends HttpServlet {
	public void creatMenu() throws ParseException, IOException {
		System.out.println("123=======================");
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("最新消息");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button21 = new ViewButton();
		button21.setName("完善个人 信息");
		button21.setType("view");
		button21.setUrl("http://19a3bbe8.ngrok.io/NIC-wechat/user/loadInfo");

		ViewButton button22 = new ViewButton();
		button22.setName("提交业务");
		button22.setType("view");
		button22.setUrl("http://19a3bbe8.ngrok.io/NIC-wechat/jsp/work_info.jsp");

		ViewButton button31 = new ViewButton();
		button31.setName("关于我们123");
		button31.setType("view");
		button31.setUrl("http://wlzx.tit.edu.cn/info/news/content/1083.htm");

		Button button = new Button();
		button.setName("业务对接");
		button.setSub_button(new Button[] { button21, button22 });

		menu.setButton(new Button[] { button11, button, button31 });

		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		System.out.println(WeixinUtil.getAccessToken().getAccessToken()
				.toString());
		// 这里为请求接口的url +号后面的是token，这里就不做过多对token获取的方法解释
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ WeixinUtil.getAccessToken().getAccessToken();

		try {
			String rs = HttpUtils.sendPostBuffer(url, jsonMenu.toString());
			System.out.println(rs);
		} catch (Exception e) {
			System.out.println("请求错误！");
		}
	}
}
