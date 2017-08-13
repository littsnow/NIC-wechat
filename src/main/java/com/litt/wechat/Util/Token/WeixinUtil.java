package com.litt.wechat.Util.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.litt.wechat.Message.Token;
import com.litt.wechat.Util.SignUtil;

/**
 * ΢�Ź�����
 * 
 * @author Stephen
 * 
 */
public class WeixinUtil {
	private static final String APPID = "wxb90edb0753e2644e";
	private static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//新增临时素材,媒体文件在微信后台保存时间为3天，即3天后media_id失效。
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 获取js-ticket
	 */
	private static final String REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	/**
	 * 下载多媒体接口
	 */
	private static final String DOWNLOAD_URL = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	/**
	 * get����
	 * 
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException,
			IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	/**
	 * POST����
	 * 
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url, String outStr)
			throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr, "UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}

	/**
	 * ��ȡaccessToken
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static Token getAccessToken() throws ParseException, IOException {
		Token token = new Token();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace(
				"APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			token.setAccessToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}

	/**
	 * 获取jsapi_ticket
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public static String getTickect(String access_token) throws ParseException,
			IOException {
		String ticket = "";
		String url = REQUEST_URL.replace("ACCESS_TOKEN", access_token);
		// 获取网页授权凭证
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			try {
				ticket = jsonObject.getString("ticket");
			} catch (Exception e) {

				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取jsapi_ticket失败 errcode:" + errorCode
						+ "," + "errmsg:" + errorMsg);
			}
		}
		return ticket;
	}

	// 获得签名js signature
	public static String getSignature(String jsapi_ticket, String timestamp,
			String nonce, String jsurl) throws IOException {
		/****
		 * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
		 * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
		 * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
		 * URL 转义。即 signature=sha1(string1)。
		 * **如果没有按照生成的key1=value&key2=value拼接的话会报错
		 */
		String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket,
				"timestamp=" + timestamp, "noncestr=" + nonce, "url=" + jsurl };
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat("&" + paramArr[1])
				.concat("&" + paramArr[2]).concat("&" + paramArr[3]);
		System.out.println("拼接之后的sign为:" + content);
		String gensignature = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对拼接后的字符串进行 sha1 加密
			byte[] digest = md.digest(content.toString().getBytes());
			gensignature = SignUtil.byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将 sha1 加密后的字符串与 signature 进行对比
		if (gensignature != null) {
			return gensignature;// 返回signature
		} else {
			return "false";
		}
	}

	/**
	 * 获取临时素材（微信服务器）
	 * 
	 * @param mediaId
	 *            媒体文件id，即微信服务器id
	 * @throws IOException
	 * @throws ParseException
	 */
	public static InputStream downloadMedia(String mediaId)
			throws ParseException, IOException {
		String accessToken = WeixinUtil.getAccessToken().getAccessToken();
		// 拼接请求地址
		String requestUrl = DOWNLOAD_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("MEDIA_ID", mediaId);
		InputStream is = null;
		try {
			URL urlGet = new URL(requestUrl);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			// 获取文件转化为byte流
			is = http.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}
	
	/**
	 * 保存图片至服务器
	 * 
	 * @param mediaId
	 * @return 文件名
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String saveImageToDisk(String mediaId) throws ParseException, IOException {
		String filename = "";
		InputStream inputStream = WeixinUtil.downloadMedia(mediaId);
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
			// 服务器存图路径
			// String path = PathKit.getWebRootPath() + "/vehicleupload/";
			String path = System.getProperty("catalina.home") + "/webapps" + "/download"+"/downloadFromWeixin";
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
	
	/**
	 * 微信服务器素材上传（图片）
	 * @param filePath 图片路径
	 * @return media_id 图文所需要的图片id
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String upload(File filePath) throws ParseException, IOException{
//		String pictureNmae="150210970713612799761-1350-47fc-9988-b6d7e1ba9968.jpg";
//		String path = System.getProperty("catalina.home") + "/webapps" + "/download/";
//		File file=(filePath);
		System.out.println(filePath);
		JSONObject jsonObject=uploadMedia(filePath,WeixinUtil.getAccessToken().getAccessToken(),"image");
		String mid=jsonObject.getString("media_id");
		System.out.println("mid="+mid);
		return mid;
	}
	/**
     * 微信服务器素材上传
     * @param file  表单名称media
     * @param token access_token
     * @param type  type只支持四种类型素材(video/image/voice/thumb)
     */
    public static JSONObject uploadMedia(File file, String token, String type) {
        if(file==null||token==null||type==null){
            return null;
        }
        if(!file.exists()){
            System.out.println("上传文件不存在,请检查!");
            return null;
        }
        String url = UPLOAD_URL;
        JSONObject jsonObject = null;
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Connection", "Keep-Alive");
        post.setRequestHeader("Cache-Control", "no-cache");
        FilePart media = null;
        HttpClient httpClient = new HttpClient();
        //信任任何类型的证书
        Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443); 
        Protocol.registerProtocol("https", myhttps);

        try {
            media = new FilePart("media", file);
            Part[] parts = new Part[] { new StringPart("access_token", token),
                    new StringPart("type", type), media };
            MultipartRequestEntity entity = new MultipartRequestEntity(parts,
                    post.getParams());
            post.setRequestEntity(entity);
            int status = httpClient.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String text = post.getResponseBodyAsString();
                jsonObject = JSONObject.fromObject(text);
              //  System.out.println("url="+jsonObject.getString("url"));
                System.out.println("jsob="+jsonObject.toString());
            } else {
            	System.out.println("upload Media failure status is:" + status);
            }
        } catch (FileNotFoundException execption) {
        	System.out.println(execption);
        } catch (HttpException execption) {
        	System.out.println(execption);
        } catch (IOException execption) {
        	System.out.println(execption);
        }
        return jsonObject;
    }
	
    
	public static int createMenu(String token, String menu)
			throws ParseException, IOException {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	public static JSONObject queryMenu(String token) throws ParseException,
			IOException {
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}

	public static int deleteMenu(String token) throws ParseException,
			IOException {
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		int result = 0;
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	public static String getAppid() {
		return APPID;
	}

}
