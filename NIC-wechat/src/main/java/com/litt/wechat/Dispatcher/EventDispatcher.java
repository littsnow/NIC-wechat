package com.litt.wechat.Dispatcher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.litt.wechat.Message.resp.Image;
import com.litt.wechat.Message.resp.ImageMessage;
import com.litt.wechat.Util.HttpPostUploadUtil;
import com.litt.wechat.Util.MessageUtil;

import net.sf.json.JSONObject;

/**
 * ClassName: EventDispatcher
 * @Description: 事件消息业务分发器
 * @date 2016年3月7日 下午4:04:41
 */
public class EventDispatcher {
    public static String processEvent(Map<String, String> map) {
    	String openid = map.get("FromUserName"); // 用户openid
    	String mpid = map.get("ToUserName"); // 公众号原始ID
    	ImageMessage imgmsg = new ImageMessage();
    	imgmsg.setToUserName(openid);
    	imgmsg.setFromUserName(mpid);
    	imgmsg.setCreateTime(new Date().getTime());
    	imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);
    	if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
    	    System.out.println("==============这是关注事件！");
    	    Image img = new Image();
    	    HttpPostUploadUtil util=new HttpPostUploadUtil();
    	    String filepath="E:\\NIC\\1.jpg";  
    	    Map<String, String> textMap = new HashMap<String, String>();  
    	    textMap.put("name", "testname");  
    	    Map<String, String> fileMap = new HashMap<String, String>();  
    	    fileMap.put("userfile", filepath); 
    	    String mediaidrs = util.formUpload(textMap, fileMap);
    	    System.out.println(mediaidrs);
    	    String mediaid=JSONObject.fromObject(mediaidrs).getString("media_id");
    	    img.setMediaId(mediaid);
    	    imgmsg.setImage(img);
    	    return MessageUtil.imageMessageToXml(imgmsg);
    	}
         
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
        }
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            System.out.println("==============这是自定义菜单点击事件！");
        }
         
        return null;
    }
}
