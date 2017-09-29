package com.litt.wechat.Util.Menu;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;

import com.litt.wechat.Dispatcher.EventDispatcher;
import com.litt.wechat.Menu.Button;
import com.litt.wechat.Menu.ClickButton;
import com.litt.wechat.Menu.Menu;
import com.litt.wechat.Menu.ViewButton;
import com.litt.wechat.Util.HttpUtils;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

import javafx.event.EventDispatchChain;

public class WebContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("最新消息");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button12 = new ViewButton();
		button12.setName("最新公告");
		button12.setType("view");
		button12.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/news/shownotice");

		
		
		ViewButton button21 = new ViewButton();
		button21.setName("完善个人信息");
		button21.setType("view");
//		button21.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
//				+ "/user/loadInfo");
		String Info_URL=PropertiesReadUtils.getWechatString("rootdirectory")+ "/user/load";
		button21.setUrl(WeixinUtil.getOAuth2().replace("REDIRECT_URL", Info_URL));
		

		ViewButton button22 = new ViewButton();
		button22.setName("提交业务");
		button22.setType("view");
		String commitUrl=PropertiesReadUtils.getWechatString("rootdirectory")+ "/work/loadWork";
		button22.setUrl(WeixinUtil.getOAuth2().replace("REDIRECT_URL",commitUrl));
		
		ViewButton button23 = new ViewButton();
		button23.setName("查看反馈信息");
		button23.setType("view");
		String checkUrl=PropertiesReadUtils.getWechatString("rootdirectory")+ "/work/loadCheck";
		button23.setUrl(WeixinUtil.getOAuth2().replace("REDIRECT_URL",checkUrl));

		ViewButton button24 = new ViewButton();
		button24.setName("我要留言");
		button24.setType("view");
		String suggestUrl=PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/jsp/suggest_info.jsp";
		button24.setUrl(WeixinUtil.getOAuth2().replace("REDIRECT_URL",suggestUrl));

		ViewButton button3 = new ViewButton();
		button3.setName("关于我们");
		button3.setType("view");
		button3.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/aboutNIC/extract");

		Button button2 = new Button();
		button2.setName("业务对接");
		button2.setSub_button(new Button[] { button21, button22, button23,
				button24 });

		Button button1 = new Button();
		button1.setName("查看消息");
		button1.setSub_button(new Button[] { button11, button12 });

		menu.setButton(new Button[] { button1, button2, button3 });

		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		try {
			System.out.println(WeixinUtil.getAccessToken().getAccessToken()
					.toString());
		} catch (ParseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 这里为请求接口的url +号后面的是token，这里就不做过多对token获取的方法解释
		String url;
		try {
			url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
					+ WeixinUtil.getAccessToken().getAccessToken();
			String rs = HttpUtils.sendPostBuffer(url, jsonMenu.toString());
			System.out.println(rs);
		} catch (ParseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

} 
