package com.zero2ipo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @title :公共参数类
 * @author: zhengYunFei
 * @data: 2013-3-20
 */
public class Regexs {
    private Regexs(){};
    public static final String delhtml = "<([^>]*)>|\\s+";//去掉html标签以及空格
    public static final String substr = "^(.{140}).*$";//str.replaceAll(rex, "$1...")
    /**
	 * 控制的按钮级别的权限过滤：url截取
	 *@title
	 *@date 2014-9-30
	 *@author ZhengYunfei
	 * @param url
	 * @return
	 */
	public static String splitUrl(String url) {
		 Pattern p = Pattern.compile("/");
	      Matcher m = p.matcher(url);
	      List<Integer> list=new ArrayList<Integer>();
	      while (m.find()){
	    	 list.add(m.start());
	      }
	      int size=list.size();
	      int index=list.get(size-1);
	      return url.substring(0,index);
	}
    
}
