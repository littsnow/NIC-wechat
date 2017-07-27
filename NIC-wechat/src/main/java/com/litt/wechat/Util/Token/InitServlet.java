package com.litt.wechat.Util.Token;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.wechat.Util.GlobalConstants;

/**
 * 在Servlet的init()方法中启动一个线 程
 * 在线程的run()方法中通过死循环+Thread.sleep()的方式定期获取access_token，
 * 将获取到的 access_token保存在public static修饰的变量中
 */
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public void init() throws ServletException {
        // 获取web.xml中配置的参数
        TokenThread.appid = "wxb90edb0753e2644e";
        TokenThread.appsecret = "d4624c36b6795d1d99dcf0547af5443d";

        log.info("weixin api appid:{}", TokenThread.appid);
        log.info("weixin api appsecret:{}", TokenThread.appsecret);

        // 未配置appid、appsecret时给出提示
        if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
            log.error("appid and appsecret configuration error, please check carefully.");
        } else {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }
}
