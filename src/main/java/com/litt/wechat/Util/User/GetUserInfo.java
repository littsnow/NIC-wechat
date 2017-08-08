package com.litt.wechat.Util.User;

import net.sf.json.JSONObject;

import com.litt.nic.pojo.user;
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
	 * 获取用户信息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return WeixinUserInfo
	 */
	public static user getUserInfo(String accessToken, String openId) {
		user weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinUserInfo = new user();
				// 用户的标识
				weixinUserInfo.setUserOpenid(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				weixinUserInfo.setUserSubscribe(jsonObject.getInt("subscribe"));
				// 昵称
				weixinUserInfo
						.setUserNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				weixinUserInfo.setUserSex(jsonObject.getInt("sex"));
				// 用户所在国家
				weixinUserInfo.setUserCountry(jsonObject.getString("country"));
				// 用户所在省份
				weixinUserInfo
						.setUserProvince(jsonObject.getString("province"));
				// 用户所在城市
				weixinUserInfo.setUserCity(jsonObject.getString("city"));
				// 用户的语言，简体中文为zh_CN
				weixinUserInfo
						.setUserLanguage(jsonObject.getString("language"));
				// 用户头像
				weixinUserInfo.setUserHeadimgurl(jsonObject
						.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getUserSubscribe()) {
					System.out.println("用户{}已取消关注"
							+ weixinUserInfo.getUserOpenid());
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
