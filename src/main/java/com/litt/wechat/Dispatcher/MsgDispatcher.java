package com.litt.wechat.Dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;

import com.litt.nic.pojo.user;
import com.litt.wechat.Message.resp.Article;
import com.litt.wechat.Message.resp.NewsMessage;
import com.litt.wechat.Message.resp.TextMessage;
import com.litt.wechat.Util.MessageUtil;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Token.WeixinUtil;
import com.litt.wechat.Util.User.GetUserInfo;

/**
 * ClassName: MsgDispatcher
 * 
 * @Description: 消息业务处理分发器
 * @date 2016年3月7日 下午4:04:21
 */
public class MsgDispatcher {
	public static String processMessage(Map<String, String> map)
			throws ParseException, IOException {
		String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID

		// 普通文本消息
		TextMessage txtmsg = new TextMessage();
		txtmsg.setToUserName(openid);
		txtmsg.setFromUserName(mpid);
		txtmsg.setCreateTime(new Date().getTime());
		txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		System.out.println("来了哦");

		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		user userinfo = GetUserInfo.getUserInfo(WeixinUtil.getAccessToken()
				.getAccessToken(), openid);

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			if (map.get("Content").equals("1")) {

				Article article = new Article();
				article.setDigest("欢迎使用太原工业学院网络信息管理中心业务对接系统"); // 图文消息的描述
				article.setPicUrl(userinfo.getUserHeadimgurl()); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请您先完善您的个人信息，方便提交业务信息。");
				// 图文消息标题
				article.setUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/user/loadInfo?openid=" + openid); // 图文url链接

				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			} else if (map.get("Content").equals("2")) {
				Article article = new Article();
				article.setDigest("点击提交业务信息"); // 图文消息的描述
				article.setPicUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/images/tijiao.jpg"); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请点击这里提交业务信息。");
				// 图文消息标题
				article.setUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/work/toadd?openid=" + openid); // 图文url链接
				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			} else if (map.get("Content").equals("3")) {
				Article article = new Article();
				article.setDigest("点击提交留言信息"); // 图文消息的描述
				article.setPicUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/images/liuyan.jpg"); //
				// 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请点击这里提交留言信息。");
				// 图文消息标题
				article.setUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/suggest/toadd?openid=" + openid);
				// 图文url链接
				List<Article> list = new ArrayList<Article>();
				list.add(article);
				// 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			}

			System.out.println("==============这是文本消息！" + openid);
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                      "
					+ "回复  '1'  完善个人信息                    "
					+ "回复  '2'  提交相关业务                    "
					+ "回复  '3'  我要留言                            ");

			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
			System.out.println("==============这是图片消息！");
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                      "
					+ "回复  '1'  完善个人信息                    "
					+ "回复  '2'  提交相关业务                    "
					+ "回复  '3'  我要留言                            ");

			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			System.out.println("==============这是链接消息！");
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                      "
					+ "回复  '1'  完善个人信息                    "
					+ "回复  '2'  提交相关业务                    "
					+ "回复  '3'  我要留言                            ");
			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			System.out.println("==============这是位置消息！");
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                      "
					+ "回复  '1'  完善个人信息                    "
					+ "回复  '2'  提交相关业务                    "
					+ "回复  '3'  我要留言                            ");
			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			System.out.println("==============这是语音消息！" + openid);
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                      "
					+ "回复  '1'  完善个人信息                    "
					+ "回复  '2'  提交相关业务                    "
					+ "回复  '3'  我要留言                            ");
			return MessageUtil.textMessageToXml(txtmsg);
		}

		return null;
	}
}
