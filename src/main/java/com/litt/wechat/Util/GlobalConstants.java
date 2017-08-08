package com.litt.wechat.Util;

import java.util.Properties;

public class GlobalConstants {
 
    public static Properties interfaceUrlProperties;
 
/**
 * 
 * @Description: TODO
 * @param @param key
 * @param @return   
 * @date 2015年10月13日 下午4:59:14
 */
    public static String getInterfaceUrl(String key) {
        return (String) interfaceUrlProperties.get(key);
    }
     
         
     
}