package com.litt.wechat.Dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import com.litt.wechat.Message.resp.Article;
import com.litt.wechat.Message.resp.ImageMessage;
import com.litt.wechat.Message.resp.NewsMessage;
import com.litt.wechat.Message.resp.TextMessage;
import com.litt.wechat.Util.MessageUtil;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;

/**
 * ClassName: EventDispatcher
 * 
 * @Description: 事件消息业务分发器
 * @date 2016年3月7日 下午4:04:41
 */
public class EventDispatcher {

	private static Logger logger = Logger.getLogger(EventDispatcher.class);

	public static String openid;

	public static String processEvent(Map<String, String> map)
			throws ParseException, IOException {
		openid = map.get("FromUserName"); // 用户openid
		System.out.println("openid5:"+openid);
		String mpid = map.get("ToUserName"); // 公众号原始ID
		// 图片消息
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);

		// 普通文本消息
		TextMessage txtmsg = new TextMessage();
		txtmsg.setToUserName(openid);
		txtmsg.setFromUserName(mpid);
		txtmsg.setCreateTime(new Date().getTime());
		txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

		// 对图文消息
		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //
			// 关注事件
			System.out.println("=======222=======这是关注事件！" + openid.toString());

			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                      "
					+ "回复  '1'  完善个人信息                    "
					+ "回复  '2'  提交相关业务                    " + "回复  '3'  提交留言信息");

			return MessageUtil.textMessageToXml(txtmsg);

		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			System.out.println("==============这是取消关注事件！");
		}
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");

			Article article = new Article();
			article.setDigest("版权所有：太原工业学院网络与信息中心"); // 图文消息的描述
			article.setPicUrl(PropertiesReadUtils
					.getWechatString("rootdirectory")
					+ PropertiesReadUtils.getWechatString("picurl"));
			// 图文消息图片地址
			article.setTitle("【信息快讯】   点击查看学院官网发布的最新信息！"); // 图文消息标题
			article.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
					+ "/news/extract");
			// 图文url链接
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			return MessageUtil.newsMessageToXml(newmsg);

		}

		return null;
	}


}
