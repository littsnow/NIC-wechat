package com.litt.wechat.Util;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.wechat.Controller.WechatSecurity;
import com.litt.wechat.Menu.ClickButton;
import com.litt.wechat.Menu.ViewButton;
import com.litt.wechat.Util.Token.WeixinUtil;

@Controller
@RequestMapping("/wechat")
public class MenuMain {

	private static Logger logger = Logger.getLogger(WechatSecurity.class);

	public static void main(String[] args) throws ParseException, IOException {
		WeixinUtil weixinUtil = new WeixinUtil();
		System.out.println("定义菜单了啊");
		// ClickButton cbt = new ClickButton();
		// cbt.setKey("image");
		// cbt.setName("关于我们");
		// cbt.setType("click");
		//
		// ViewButton vbt = new ViewButton();
		// vbt.setUrl("http://www.cuiyongzhi.com");
		// vbt.setName("最新消息");
		// vbt.setType("view");
		//
		// // ClickButton bx = new ClickButton();
		// // cbt.setKey("image");
		// // cbt.setName("设备报修");
		// // cbt.setType("click");
		// //
		// // ClickButton yw = new ClickButton();
		// // cbt.setKey("image");
		// // cbt.setName("日常运维");
		// // cbt.setType("click");
		// //
		// // ClickButton jz = new ClickButton();
		// // cbt.setKey("image");
		// // cbt.setName("技术支持");
		// // cbt.setType("click");
		//
		// // JSONArray sub_button = new JSONArray();
		// // sub_button.add(jz);
		// // sub_button.add(yw);
		// // sub_button.add(bx);
		//
		// JSONObject buttonOne = new JSONObject();
		// buttonOne.put("name", "业务办理");
		// buttonOne.put("sub_button", sub_button);

		// JSONArray button = new JSONArray();
		// button.add(vbt);
		// button.add(buttonOne);
		// button.add(cbt);

		ClickButton cbt = new ClickButton();
		cbt.setKey("image");
		cbt.setName("关于我们");
		cbt.setType("click");

		ViewButton vbt = new ViewButton();
		vbt.setUrl("http://www.cuiyongzhi.com");
		vbt.setName("最新消息");
		vbt.setType("view");

		JSONArray sub_button = new JSONArray();
		sub_button.add(vbt);
		sub_button.add(cbt);

		JSONObject buttonOne = new JSONObject();
		buttonOne.put("name", "业务对接");
		buttonOne.put("sub_button", sub_button);

		JSONArray button = new JSONArray();
		button.add(vbt);
		button.add(buttonOne);
		button.add(cbt);

		JSONObject menujson = new JSONObject();
		menujson.put("button", button);
		System.out.println(menujson);
		System.out.println(weixinUtil.getAccessToken().getAccessToken()
				.toString());
		// 这里为请求接口的url +号后面的是token，这里就不做过多对token获取的方法解释
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ weixinUtil.getAccessToken().getAccessToken();

		try {
			String rs = HttpUtils.sendPostBuffer(url, menujson.toString());
			System.out.println(rs);
		} catch (Exception e) {
			System.out.println("请求错误！");
		}
	}
}
