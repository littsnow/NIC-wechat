package com.litt.wechat.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.litt.wechat.Message.resp.Article;
import com.litt.wechat.Message.resp.NewsMessage;

public class Test {

	public static void main(String[] args) {
		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName("oR1Tmwd4hAWdiSZU3R8zQkS4mYTk");
		// newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

		System.out.println("==============这是图片消息！");
		Article article = new Article();
//		article.setDescription("这是图文消息1123"); // 图文消息的描述
//		article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); // 图文消息图片地址
//		article.setTitle("图文消息1"); // 图文消息标题
//		article.setUrl("http://cf887ed4.ngrok.io/NIC-wechat/login.jsp"); // 图文url链接
		List<Article> list = new ArrayList<Article>();
		list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
		newmsg.setArticleCount(list.size());
		newmsg.setArticles(list);
		MessageUtil.newsMessageToXml(newmsg);
	}

	public void test() {

	}

}
