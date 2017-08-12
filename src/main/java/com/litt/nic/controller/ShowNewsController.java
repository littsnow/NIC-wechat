package com.litt.nic.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.litt.nic.pojo.information;
import com.litt.nic.service.IInformationService;
import com.litt.wechat.Util.HttpUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

@Controller
@RequestMapping(value = "/shownews")
public class ShowNewsController {
	// private static Logger logger = Logger.getLogger(SendToAll.class);
	@Autowired
	private IInformationService informationService;

	/**
	 * 跳转到发布新消息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toshownews")
	public String toshownews() {

		return "/WEB-INF/views/work/shownews";

	}

	/**
	 * 添加最新消息
	 * 
	 * @param FilePath
	 * 
	 * @return
	 * @throws Exception
	 * @throws ParseException
	 */
	@RequestMapping(value = "/addnews", method = RequestMethod.GET)
	public String addnews(HttpServletRequest request,
			HttpServletResponse response, String FilePath)
			throws ParseException, Exception {
		String str=request.getParameter("file");
	
		
		String media_id = "";
		String msg = "";
		information information = new information();
		String access_token = WeixinUtil.getAccessToken().getAccessToken();
		information.setInformationTitle(request.getParameter("title"));
		information.setInformationContent(request.getParameter("content"));
		information.setInformationDescription(request
				.getParameter("description"));
		information.setInformationTime(new Date());

		informationService.addnews(information);

		// // 对图文消息
		// NewsMessage newmsg = new NewsMessage();
		// newmsg.setToUserName("oR1Tmwd4hAWdiSZU3R8zQkS4mYTk");
		// // newmsg.setFromUserName(mpid);
		// newmsg.setCreateTime(new Date().getTime());
		// newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		// Article article = new Article();
		// article.setDescription("版权所有：太原工业学院网络与信息中心"); // 图文消息的描述
		// article.setPicUrl("http://34a8d271.ngrok.io/NIC-wechat/images/tg.png");
		// // 图文消息图片地址
		// article.setTitle("【信息快讯】   点击查看学院官网发布的最新信息！"); // 图文消息标题
		// article.setUrl("http://34a8d271.ngrok.io/NIC-wechat/news/extract");
		// // 图文url链接
		// List<Article> list = new ArrayList<Article>();
		// list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
		// newmsg.setArticleCount(list.size());
		// newmsg.setArticles(list);
		// msg = MessageUtil.newsMessageToXml(newmsg);
		// String sendPreUrl =
		// "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token="
		// + access_token;
		// HttpUtils.sendXmlPost(sendPreUrl, msg.toString());
		// // 响应消息
		// PrintWriter out = response.getWriter();
		// out.print(msg);
		// out.close();
		// 2、上传消息的缩略图==========================================
		// String post0 = SendToAll.send("image",
		// "E://NIC-wechat//WebContent//images//11.jpg", access_token);
		// Map map0 = JSONObject.fromObject(post0);
		// 3、得到缩略图的media_id==========================================
		// media_id = (String) map0.get("media_id");
		// 4、收集文章所需参数，并转码==========================================
		String title = new String("qqqqqqqqqq".getBytes("utf-8"), "ISO-8859-1");
		String author = new String("litt".getBytes("utf-8"), "ISO-8859-1");
		String digest = new String("qqqqqqqqq".getBytes("utf-8"), "ISO-8859-1");
		String content = new String("qqqqqqqqqq".getBytes("utf-8"),
				"ISO-8859-1");

		// 5、上传图文消息==========================================
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="
				+ access_token;
		msg = "{\"articles\": {\"thumb_media_id\":\"" + media_id
				+ "\", \"author\":\"" + author + "\",\"title\":\"" + title
				+ "\",\"content_source_url\":\""
				+ "http://34a8d271.ngrok.io/NIC-wechat/news/extract"
				+ "\", \"content\":\"" + content + "\",\"digest\":\"" + digest
				+ "\"}}";
		System.out.println(msg);
		String post = HttpUtils.sendPostBuffer(url, msg);
		Map json2Map1 = JSONObject.fromObject(post);
		// 6、得到图文消息media_id==========================================
		media_id = (String) json2Map1.get("media_id");

		// 7、 单独发给该用户进行预览==========================================

		String sendPreUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token="
				+ access_token;
		String sendPreMsg = "{\"touser\":\"" + "oR1Tmwd4hAWdiSZU3R8zQkS4mYTk"
				+ "\",\"mpnews\":{\"media_id\":\"" + media_id
				+ "\"},\"msgtype\":\"mpnews\"}";
		String sendPreRes = HttpUtils.sendPostBuffer(sendPreUrl, sendPreMsg);
		return "/WEB-INF/views/work/shownews";

	}

}
