package com.litt.wechat.Dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import com.litt.wechat.Message.resp.Article;
import com.litt.wechat.Message.resp.ImageMessage;
import com.litt.wechat.Message.resp.NewsMessage;
import com.litt.wechat.Util.MessageUtil;
import com.litt.wechat.Util.User.GetUserInfo;

/**
 * ClassName: EventDispatcher
 * 
 * @Description: 事件消息业务分发器
 * @date 2016年3月7日 下午4:04:41
 */
public class EventDispatcher {

	private static Logger logger = Logger.getLogger(EventDispatcher.class);

	public static String processEvent(Map<String, String> map)
			throws ParseException, IOException {
		String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID

		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);

		// 对图文消息
		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
			System.out.println("==============这是关注事件！" + openid.toString());
			try {
				System.out.println("jinlaile-------------------");
				HashMap<String, String> userinfo = GetUserInfo
						.Openid_userinfo(openid);
				System.out
						.println("userinfo.hashCode():" + userinfo.hashCode());
				Article article = new Article();
				article.setDescription("欢迎来到栗童童的账户"); // 图文消息的描述
				article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.get("nickname") + ",你好！"); // 图文消息标题
				article.setUrl("http://www.cuiyongzhi.com"); // 图文url链接
				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("====代码有问题额☺！");
				logger.error(e, e);
			}

		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			System.out.println("==============这是取消关注事件！");
		}
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
		}

		return null;
	}
}
