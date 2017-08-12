package com.litt.wechat.Dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.litt.wechat.Message.resp.Article;
import com.litt.wechat.Message.resp.NewsMessage;
import com.litt.wechat.Message.resp.TextMessage;
import com.litt.wechat.Util.MessageUtil;

/**
 * ClassName: MsgDispatcher
 * 
 * @Description: 消息业务处理分发器
 * @date 2016年3月7日 下午4:04:21
 */
public class MsgDispatcher {
	public static String processMessage(Map<String, String> map) {
		String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID

		// 普通文本消息
		TextMessage txtmsg = new TextMessage();
		txtmsg.setToUserName(openid);
		txtmsg.setFromUserName(mpid);
		txtmsg.setCreateTime(new Date().getTime());
		txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		System.out.println("来了哦");
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			System.out.println("==============这是文本消息！" + openid);
			txtmsg.setContent("你好，这里是栗童童个人账号！");

			return MessageUtil.textMessageToXml(txtmsg);
		}

		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
			System.out.println("==============这是图片消息！");
			Article article = new Article();
//			article.setDescription("这是图文消息1"); // 图文消息的描述
//			article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); // 图文消息图片地址
//			article.setTitle("图文消息1"); // 图文消息标题
//			article.setUrl("http://cf887ed4.ngrok.io/NIC-wechat/login.jsp"); // 图文url链接
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			return MessageUtil.newsMessageToXml(newmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			System.out.println("==============这是链接消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			System.out.println("==============这是位置消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			System.out.println("==============这是语音消息！");
		}

		return null;
	}
}
