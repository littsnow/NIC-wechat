package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.techsupport;
import com.litt.nic.pojo.user;
import com.litt.nic.service.IStatusService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Util.Token.WeixinUtil;

/**
 * 提交报修信息
 * 
 * @author litt
 * 
 */
@Controller
@RequestMapping(value = "/work")
public class WorkController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITechSupportService techSupportService;

	@Autowired
	private IStatusService statusService;
	private user user = null;

	private List<techsupport> techsupports = null;

	@RequestMapping(value = "/addwork")
	public String addWork(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String pictureName = request.getParameter("filename");
		System.out.println("文件名字=" + pictureName);
		// 获取当前提交信息的联系人
		// String openid = EventDispatcher.openid;
		String openid = request.getParameter("openid");
		System.out.println("openid" + openid);

		user = userService.findByOpenid(openid);
		System.out.println(user.getUserName() + "===================");
		System.out.println("openid2" + openid);

		String uptime = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date());
		System.out.println("当前时间是：" + uptime);

		// 获取表单信息
		String type = request.getParameter("worktype");
		String desc = request.getParameter("description");
		String location = request.getParameter("location");
		String devicename = request.getParameter("devicename");
		if ("0".equals(type) || "".equals(desc) || "".equals(location)
				|| "".equals(devicename)) {
			System.out.println("有空值，");
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请把信息填写完整！');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			// 提交信息

			System.out.println(user.getUserDepartment());
			techsupport techsupport = new techsupport();
			techsupport.setTechsupportDepartment(user.getUserDepartment());
			techsupport.setTechsupportDescribe(desc);
			techsupport.setTechsupportLocation(location);
			techsupport.setStatusId(1);
			techsupport.setTechsupportDevicename(devicename);
			techsupport.setTechsupportUptime(uptime);
			techsupport.setTechsupportEndtime(uptime);
			techsupport.setUserId(user.getUserId());
			techsupport.setType(type);
			// 只保存了图片的名字
			if ("null".equals(pictureName)) {
				System.out.println("用户没提交照片");
			} else {
				techsupport.setTechsupportPicture(pictureName);
			}
			techSupportService.addtech(techsupport);

			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('我们已经收到您的请求，请耐心等待处理结果！');");
			out.println("</script>");
		}
		request.setAttribute("openid", openid);
		return "/jsp/work_info";

	}

	@RequestMapping("/config")
	public void getConfig(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		response.setCharacterEncoding("UTF-8");
		// 获取appId
		String appId = WeixinUtil.getAppid();
		// GlobalConstants.getInterfaceUrl("appid");
		System.out.println(appId);
		// 获取页面路径(前端获取时采用location.href.split('#')[0]获取url)
		String url = request.getParameter("url");
		System.out.println("url=" + url);
		// 获取access_token
		String access_token = WeixinUtil.getAccessToken().getAccessToken();
		System.out.println("access_token=" + access_token);
		// 获取ticket
		String jsapi_ticket = WeixinUtil.getTickect(access_token);
		System.out.println("jsapi_ticket=" + jsapi_ticket);
		// 获取Unix时间戳(java时间戳为13位,所以要截取最后3位,保留前10位)
		String timestamp = String.valueOf(System.currentTimeMillis())
				.substring(0, 10);
		String noncestr = UUID.randomUUID().toString();
		System.out.println(timestamp + "noncestr=" + noncestr);
		// 创建有序的Map用于创建签名串
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("jsapi_ticket", jsapi_ticket);
		params.put("timestamp", timestamp);
		params.put("nonceStr", noncestr);
		params.put("url", url);
		// 注意这里参数名必须全部小写，且必须有序
		// String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" +
		// noncestr + "&timeStamp=" + timestamp + "&url=" + url;
		// 签名
		String signature = WeixinUtil.getSignature(jsapi_ticket, timestamp,
				noncestr, url);
		System.out.println(signature);
		// 得到签名再组装到Map里
		params.put("signature", signature);
		// 传入对应的appId
		params.put("appId", appId);
		// 组装完毕，回传
		try {
			JSONArray jsonArray = JSONArray.fromObject(params);
			// System.out.println(jsonArray.toString());
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("js-sdk配置错误");
		}
	}

	/**
	 * 保存图片
	 * 
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping("/savePicture")
	public void savePicture(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String mediaId = request.getParameter("mediaId");
		System.out.println("mediaId=" + mediaId);
		// 保存图片 路径 PathKit.getWebRootPath() + "/vehicleupload/" + filename;
		String filename = WeixinUtil.saveImageToDisk(mediaId);
		// 打印文件名
		System.out.println(filename);
		// 返回jquery 参数
		response.getWriter().print(filename);
	}

	@RequestMapping("/toadd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String openid = request.getParameter("openid");
		System.out.println(openid + "-------------");
		user DataBaseUser = userService.findByOpenid(openid);

		// 数据库不存在此人
		if (DataBaseUser == null) {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请完善个人信息后再提交相关业务信息！');");
			// out.println("history.back();");
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("openid", openid);
			return "/jsp/work_info";
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/loadWork")
	public String loadWokeJsp(HttpServletRequest request,
			HttpServletResponse response, String code) throws IOException {
		String openid = WeixinUtil.getOpenid(code);
		System.out.println("--------");
		request.setAttribute("openid", openid);
		user DataBaseUser = userService.findByOpenid(openid);
		// return "redirect:/work/showmsg?openid="+openid;
		if (DataBaseUser == null) {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请完善个人信息后再提交相关业务信息！');");
			// out.println("history.back();");
			out.println("</script>");
			return "forward:/user/loadInfo?openid="+openid;
		} else {
			request.setAttribute("openid", openid);
			return "/jsp/work_info";
		}
	}

	@RequestMapping("/loadCheck")
	public String loadCheckJsp(HttpServletRequest request,
			HttpServletResponse response, String code) {
		String openid = WeixinUtil.getOpenid(code);
		System.out.println("--------");
		// request.setAttribute("openid", openid);
		return "redirect:/work/showmsg?openid=" + openid;

	}

	/**
	 * 查看反馈消息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/showmsg")
	public String showmsg(HttpServletRequest request,
			HttpServletResponse response, String openid) throws Exception {
		System.out.println("这是Showmsg方法");
		System.out.println("openid===123======" + openid);
		request.setAttribute("openid", openid);
		user = userService.findByOpenid(openid);

		if (user != null) {
			System.out.println("user=========================="
					+ user.toString());
			// 根据用户id、未完成状态id 查找该用户提交并且有反馈的业务
			techsupports = techSupportService.findFeedback(user.getUserId());

			// 根据状态id查找对应的状态名称
			if (techsupports.isEmpty()) {
				return "/jsp/error/feedbacknull";

			} else {

				getTSLists(request, techsupports);
				// 显示信息（密码提交的业务处于什么状态+反馈内容）
				request.setAttribute("techlist", techsupports);

				return "/jsp/showmsg_info";
			}
		} else {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请完善个人信息后再查看相关信息！');");
			// out.println("history.back();");
			out.println("</script>");
			return "forward:/user/loadInfo?openid="+openid;
		}

	}

	public void getTSLists(HttpServletRequest request,
			List<techsupport> techSupportList) {
		List<String> tsStatusList = new ArrayList<String>();

		for (int i = 0; i < techSupportList.size(); i++) {
			tsStatusList.add(statusService.findById(
					techSupportList.get(i).getStatusId()).getStatusName());
		}
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsLen", techSupportList.size());
	}

}
