package com.litt.nic.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.litt.nic.pojo.article;
import com.litt.nic.service.IArticleService;
import com.litt.wechat.Util.Token.WeixinUtil;

@Controller
@RequestMapping(value = "/shownews")
public class ShowNewsController {
	// private static Logger logger = Logger.getLogger(SendToAll.class);
	
	@Autowired
	private IArticleService articleService;
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
	@RequestMapping(value = "/addnews")
	public String addnews(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) throws ParseException, Exception {
		
		InputStream is = file.getInputStream();
		//保存图片路径
		String targetFile = System.getProperty("catalina.home") + "/webapps" + "/download"+"/ManageUpload/"+UUID.randomUUID().toString() + ".jpg";
		System.out.println(is);
		System.out.println(targetFile);
		try {
			OutputStream out = new FileOutputStream(targetFile);
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = is.read(bytes)) != -1) {
				out.write(bytes, 0, len);
			}
			is.close();
			out.close();
		} catch (Exception e) {
			System.out.println("保存图片失败");
		}
		System.out.println("保存图片成功"); 
		//上传图片到微信服务器
		// 图文消息缩略图的media_id
		String media_id=WeixinUtil.upload(new File(targetFile));
		//由media_id下载到服务器(tomcat)
		String picName=WeixinUtil.saveImageToDisk(media_id);

		String title = request.getParameter("title");
		//
		String content = request.getParameter("content");
		// digest 图文消息的描述，如本字段为空，则默认抓取正文前64个字
		String desc = request.getParameter("description");
		System.out.println(title + ",   " + content + " ," + desc);
		article articlePojo=new article();
		articlePojo.setThumbMediaId(picName);
		articlePojo.setAuthor("太原工业网络与信息中心处");
		articlePojo.setTitle(title);
		articlePojo.setContentSourceUrl("http://wlzx.tit.edu.cn/");
		articlePojo.setContent(content);
		articlePojo.setDigest(desc);
		articlePojo.setShowCoverPic(1);
		articleService.save(articlePojo);
		
		
		//进入微信群发图文消息controller
		return "redirect:/send/message";
	}

}
