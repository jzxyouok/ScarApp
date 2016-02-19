package com.zero2ipo.framework.util;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.zero2ipo.framework.log.BaseLog;
/**
 * @title: Ip 操作工具类 
 * @deprecated:  Ip 操作工具类 
 * @author: zhengYunFei
 * @date : 2013-08-19 12:30
 */
public class IpUtil {
	/**
     *  获取请求IP地址
     * @param request 请求
     * @return IP地址
     * @throws java.net.UnknownHostException
     */
    public static String getIp(HttpServletRequest request){
    	String ip = null;
        try{
        	ip = request.getHeader("X-Real-IP");
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            	ip = request.getHeader("x-forwarded-for");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-Ip");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-Ip");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }
            if("0:0:0:0:0:0:0:1".equals(ip)){
                ip="127.0.0.1";
            }
        }catch(Exception ex){
            BaseLog.e(IpUtil.class, "getIp 获取Ip错误", ex);
        }
        return ip;
    }
}
