package com.litt.wechat.Util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.wechat.Controller.WechatSecurity;
import com.litt.wechat.Menu.ClickButton;
import com.litt.wechat.Menu.ViewButton;
import com.litt.wechat.Util.Token.TokenThread;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wechat")
public class MenuMain {

	private static Logger logger = Logger.getLogger(WechatSecurity.class);
	 
	public void makeMenu(){
		System.out.println("定义菜单了啊");
		ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");
         
         
        ViewButton vbt=new ViewButton();
        vbt.setUrl("http://www.cuiyongzhi.com");
        vbt.setName("博客");
        vbt.setType("view");
         
        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);
         
         
        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);
         
        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
        button.add(cbt);
         
        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+TokenThread.accessToken.getAccessToken();
         
        try{
            String rs=HttpUtils.sendPostBuffer(url, menujson.toString());
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
     
    }
}
	
