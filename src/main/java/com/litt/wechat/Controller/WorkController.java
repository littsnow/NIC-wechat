package com.litt.wechat.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.pojo.maintenance;
import com.litt.nic.pojo.repair;
import com.litt.nic.pojo.techsupport;
import com.litt.nic.pojo.user;
import com.litt.nic.service.IMainTenanceService;
import com.litt.nic.service.IRepairService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Dispatcher.EventDispatcher;
import com.litt.wechat.Util.Token.WeixinUtil;

import net.sf.json.JSONArray;

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
	private IRepairService repairService;
	@Autowired
	private IMainTenanceService mainTenanceService;


	private user user;

	@RequestMapping(value = "/addwork")
	public String addWork(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		String pictureName=request.getParameter("filename");
		System.out.println("文件名字="+pictureName);
		// 获取当前提交信息的联系人
		String openid = EventDispatcher.openid;
		user = userService.findByOpenid(openid);
		System.out.println(user.getUserName() + "===================");
		System.out.println("openid==========================" + openid);
		int status = Integer.parseInt(request.getParameter("worktype"));
		System.out.println("业务类型是几呢======" + status);
		String uptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("当前时间是：" + uptime);
		// 提交信息
		switch (status) {
		case 1:
			System.out.println(user.getUserDepartment());

			// techsupport = new techsupport(null, user.getUserId(),
			// user.getUserDepartment(),
			// request.getParameter("devicename"),
			// request.getParameter("location"),
			// request.getParameter("description"), null, null, uptime,
			// null, 1, null);
			techsupport techsupport = new techsupport();
			techsupport.setTechsupportDepartment(user.getUserDepartment());
			techsupport.setTechsupportDescribe(request.getParameter("description"));
			techsupport.setTechsupportLocation(request.getParameter("location"));
			techsupport.setStatusId(1);
			techsupport.setTechsupportDevicename(request.getParameter("devicename"));
			techsupport.setTechsupportUptime(uptime);
			techsupport.setUserId(user.getUserId());
			//只保存了图片的名字
			if("null".equals(pictureName)){
				System.out.println("用户没提交照片");
			}else{
				techsupport.setTechsupportPicture(pictureName);
			}
			techSupportService.addtech(techsupport);
			break;
		case 2:

			repair repair = new repair();
			repair.setRepairDepartment(user.getUserDepartment());
			repair.setRepairDescribe(request.getParameter("description"));
			repair.setRepairLocation(request.getParameter("location"));
			repair.setStatusId(1);
			repair.setRepairDevicename(request.getParameter("devicename"));
			repair.setUserId(user.getUserId());
			repair.setRepairUptime(uptime);
			//只保存了图片的名字
			if("null".equals(pictureName)){
				System.out.println("用户没提交照片");
			}else{
				repair.setRepairPicture(pictureName);
			}
			repairService.addrepair(repair);

			break;
		case 3:
			maintenance maintenance = new maintenance();
			maintenance.setMaintenanceDepartment(user.getUserDepartment());
			maintenance.setMaintenanceDescribe(request.getParameter("description"));
			maintenance.setMaintenanceDevicename(request.getParameter("devicename"));
			maintenance.setMaintenanceLocation(request.getParameter("location"));
			maintenance.setStatusId(1);
			maintenance.setUserId(user.getUserId());
			maintenance.setMaintenanceUptime(uptime);
			//只保存了图片的名字
			if("null".equals(pictureName)){
				System.out.println("用户没提交照片");
			}else{
				maintenance.setMaintenancePicture(pictureName);
			}
			mainTenanceService.addmaintenance(maintenance);
			break;

		default:
			break;
		}

		return null;

	}


	@RequestMapping("/config")
	public void getConfig(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
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
		String timestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
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
		String signature = WeixinUtil.getSignature(jsapi_ticket, timestamp, noncestr, url);
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
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping("/savePicture")
	public void savePicture(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		String mediaId = request.getParameter("mediaId");
		System.out.println("mediaId=" + mediaId);
		// 保存图片 路径 PathKit.getWebRootPath() + "/vehicleupload/" + filename;
		String filename = saveImageToDisk(mediaId);
		//打印文件名
		System.out.println(filename);
		//返回jquery 参数
		response.getWriter().print(filename);
	}

	/**
	 * 保存图片至服务器
	 * 
	 * @param mediaId
	 * @return 文件名
	 * @throws IOException
	 * @throws ParseException
	 */
	public String saveImageToDisk(String mediaId) throws ParseException, IOException {
		String filename = "";
		InputStream inputStream = WeixinUtil.downloadMedia(mediaId);
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
			// 服务器存图路径
			// String path = PathKit.getWebRootPath() + "/vehicleupload/";
			String path = System.getProperty("catalina.home") + "/webapps" + "/download";
			filename = System.currentTimeMillis() + UUID.randomUUID().toString() + ".jpg";
			fileOutputStream = new FileOutputStream(path + "/" + filename);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filename;
	}
}
