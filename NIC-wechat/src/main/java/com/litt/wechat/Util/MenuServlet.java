package com.litt.wechat.Util;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.litt.wechat.Controller.WechatSecurity;
import com.litt.wechat.Menu.ClickButton;
import com.litt.wechat.Menu.ViewButton;
import com.litt.wechat.Util.Token.TokenThread;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MenuServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(WechatSecurity.class);
	 
	public static void main(String[] args) {
	     
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
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+"upeDW-2pWrHgLx3fGqgsvAvf-HkQBA--5uHOo9OW16uNdL9zNPnnuIN01UDFXh_5d-QdcnBxux9tXigFwm1z0SInbdkXEKa1pMhTqaZVxK7sCPj7421YQGI0v3evwiwiWALjAHASWH";
         
        try{
            String rs=HttpUtils.sendPostBuffer(url, menujson.toString());
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
     
    }
 
}