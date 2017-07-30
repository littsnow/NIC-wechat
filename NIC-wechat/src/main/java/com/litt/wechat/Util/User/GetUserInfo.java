package com.litt.wechat.Util.User;

import net.sf.json.JSONObject;

import com.litt.wechat.Message.UserInfo;
import com.litt.wechat.Util.CommonUtil;

/**
 * ClassName: GetUseInfo
 * 
 * @Description: 获取微信用户信息
 * @author dapengniao
 * @date 2016年3月18日 下午2:00:52
 */
public class GetUserInfo {
	/**
	 * @Description: 通过openid获取用户微信信息
	 * @param @param openid
	 * @param @return
	 * @param @throws Exception
	 * @author dapengniao
	 * @date 2016年3月18日 下午2:01:30
	 */
	// public static HashMap<String, String> Openid_userinfo(String openid)
	// throws Exception {
	// HashMap<String, String> params = new HashMap<String, String>();
	// // params.put("access_token",
	// // GlobalConstants.getInterfaceUrl("access_token")); // 定时器中获取到的token
	// params.put(
	// "access_token",
	// "gc7OtRR5Vw2FcsCu01D7m--uMAz-G1Rc-Fwp64M20FcT176TchQCA92nO84qMJufp3jsfx_CIHpHgzY6mL4ifBocEeX3zQIZ1B_r9VyD5ToBRVgAFAFVZ");
	// params.put("openid", openid); // 需要获取的用户的openid
	// params.put("lang", "zh_CN");
	// String subscribers = HttpUtils
	// .sendGet(
	// "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID",
	// params);
	// System.out.println("subscribers:" + subscribers);
	// params.clear();
	// // 这里返回参数只取了昵称、头像、和性别
	// params.put("nickname",
	// JSONObject.fromObject(subscribers).getString("nickname")); // 昵称
	// params.put("headimgurl",
	// JSONObject.fromObject(subscribers).getString("headimgurl")); // 图像
	// params.put("sex", JSONObject.fromObject(subscribers).getString("sex"));
	// // 性别
	// return params;
	// }

	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return WeixinUserInfo
	 */
	public static UserInfo getUserInfo(String accessToken, String openId) {
		UserInfo weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinUserInfo = new UserInfo();
				// 用户的标识
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				// 用户关注时间
				weixinUserInfo.setSubscribeTime(jsonObject
						.getString("subscribe_time"));
				// 昵称
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				weixinUserInfo.setCity(jsonObject.getString("city"));
				// 用户的语言，简体中文为zh_CN
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				// 用户头像
				weixinUserInfo
						.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe()) {
					System.out
							.println("用户{}已取消关注" + weixinUserInfo.getOpenId());
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					System.out.println("获取用户信息失败 errcode:{} errmsg:{}"
							+ errorCode + errorMsg);
				}
			}
		}
		return weixinUserInfo;
	}

}
