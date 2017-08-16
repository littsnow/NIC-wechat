package com.litt.wechat.Util.Menu;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import com.litt.wechat.Controller.WechatSecurity;
import com.litt.wechat.Menu.Button;
import com.litt.wechat.Menu.ClickButton;
import com.litt.wechat.Menu.Menu;
import com.litt.wechat.Menu.ViewButton;
import com.litt.wechat.Util.HttpUtils;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

public class MenuMain {

	private static Logger logger = Logger.getLogger(WechatSecurity.class);

	public static void main(String[] args) throws ParseException, IOException {
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("最新消息");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button23 = new ViewButton();
		button23.setName("完善个人信息");
		button23.setType("view");
		button23.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/user/loadInfo");

		ViewButton button24 = new ViewButton();
		button24.setName("查看反馈信息");
		button24.setType("view");
		button24.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/work/showmsg");

		ViewButton button22 = new ViewButton();
		button22.setName("提交业务");
		button22.setType("view");
		button22.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/jsp/work_info.jsp");

		ViewButton button31 = new ViewButton();
		button31.setName("关于我们");
		button31.setType("view");
		button31.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/aboutNIC/extract");

		Button button21 = new Button();
		button21.setName("业务对接");
		button21.setSub_button(new Button[] { button23, button24, button22 });

		menu.setButton(new Button[] { button11, button21, button31 });

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
