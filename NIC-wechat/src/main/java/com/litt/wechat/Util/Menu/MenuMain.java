package com.litt.wechat.Util.Menu;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.wechat.Controller.WechatSecurity;
import com.litt.wechat.Menu.Button;
import com.litt.wechat.Menu.CommonButton;
import com.litt.wechat.Menu.ComplexButton;
import com.litt.wechat.Menu.Menu;
import com.litt.wechat.Util.HttpUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

@Controller
@RequestMapping("/wechat")
public class MenuMain {

	private static Logger logger = Logger.getLogger(WechatSecurity.class);

	public static void main(String[] args) throws ParseException, IOException {
		WeixinUtil weixinUtil = new WeixinUtil();
		System.out.println("定义菜单了啊");

		CommonButton btn11 = new CommonButton();
		btn11.setName("最新消息");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn21 = new CommonButton();
		btn21.setName("技术支持");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("设备报修");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("日常运维");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("业务反馈");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn31 = new CommonButton();
		btn31.setName("关于我们");
		btn31.setType("click");
		btn31.setKey("31");

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("业务对接");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { btn11, mainBtn2, btn31 });
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		System.out.println(weixinUtil.getAccessToken().getAccessToken()
				.toString());
		// 这里为请求接口的url +号后面的是token，这里就不做过多对token获取的方法解释
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ weixinUtil.getAccessToken().getAccessToken();

		try {
			String rs = HttpUtils.sendPostBuffer(url, jsonMenu.toString());
			System.out.println(rs);
		} catch (Exception e) {
			System.out.println("请求错误！");
		}
	}
}
