/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2010-7-13 
 */
package com.zero2ipo.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @title :验证工具类
 * @author: zhengYunFei
 * @data: 2010-7-27
 */
public class ValidationUtil {

    /**
     * @功能 数字校验
     * @参数 Object obj
     */
    public static boolean isNum(Object obj) {
    	return obj.toString().matches("^\\d*$");
    }

    /**
     * @功能 空值校验
     * @参数 Object obj
     * @返回值 true ""
     */
    public static boolean isEmpty(Object obj) {
    	return (obj == null) || obj.toString().matches("^\\s*$");
    	//"null"校验  || (obj.toString().replaceAll("(^\\s*)|(\\s*$)", "").matches("^(?i)null$"))
    }
    /**
     * 
     * @功能 Email校验
     * @参数 Object obj
     * @返回值 boolean
     */
    public static boolean checkEmail(Object obj) {
		if(!isEmpty(obj)){
		    return obj.toString().matches("^([a-zA-Z0-9]+[_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|.|-]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,3}$");
		}
		return false;
    }
    /**
     * @功能 用户名验证  
     * @描述 以字母开头，然后可以是数字  下划线  ，不能有汉字 特殊符号 长度必须在 5-20位之间
     * @参数 Object obj
     * @返回值 boolean
     */
     public static boolean checkUserName(Object obj){
         if(!isEmpty(obj)){
             return obj.toString().matches("^[a-zA-Z]\\w{4,19}$");
         }
         return false;
     }
     /**
      * @功能 用户密码验证  
      * @描述   长度必须在 6-16位之间  可以是 数子 字母 下划线 特殊符号	
      * @参数 Object obj
      * @返回值 boolean
      */
      public static boolean checkPassword(Object obj){
          if(!isEmpty(obj)){
              return obj.toString().matches("^.{6,16}$");
          }
          return false;
      }
      /**
       * @功能 年龄验证
       * @描述 年龄范围在1到119之间	
       * @参数 Object obj
       * @返回值 boolean
       */
       public static boolean checkAge(Object obj){
           if(!isEmpty(obj)){
               int age = Integer.parseInt(obj.toString());
               return age>0 && age <120;
           }
           return false;
       }
       
       /**
        * @功能 邮编验证
        * @描述	六位数字 /^[0-9]{6}$/
        * @参数 Object obj
        * @返回值 boolean
        */
        public static boolean checkZipCode(Object obj){
            if(!isEmpty(obj)){
        	return obj.toString().trim().matches("\\d{6}$");
            }
            return false;
        }
        /**
         * @功能 QQ验证
         * @描述 全部为数据 不以0开始 5-13位数字
         * @参数 Object obj
         * @返回值 boolean
         */
         public static boolean checkQQ(Object obj){
             if(!isEmpty(obj)){ 
         	return obj.toString().trim().matches("^[1-9]\\d{4,12}$");
             }
             return false;
         }
         /**
          * 
          * @description:身份证号验证 
          * @param obj
          * @return：
          */
         public static boolean checkIdCard(Object obj){
             if(!isEmpty(obj)){
                 return obj.toString().trim().matches("^\\d{15}(\\d{2}[A-Za-z0-9])?$");
             }
             return false;
         }
       
       /**
        * @功能 日期格式验证
        * @描述 	
        * @参数 Object obj
        * @返回值 boolean
        */
        public static boolean checkDate(Object obj){
            boolean flag = false ;
            if(!isEmpty(obj)){
        	    String[] parse = new String[]{"yyyy-MM-dd kk:mm:ss",
                                  		  "yyyy-MM-dd",
                                  		  "yyyy-MM",
                                  		  "yyyy-M-d kk:mm:ss",
                                  		  "yyyy-M-d",
                                  		  "yyyy-M",
                                  		  "yyyyMMdd"
                                	    	};
        	    String dateStr =obj.toString().replaceAll("/", "-");
        	    for (int i = 0; i < parse.length; i++) {
        		if(dateStr.trim().length() == parse[i].length() && getDateByModel(dateStr,parse[i])!=null){
        		    flag = true;
        		    break;
        		}
        	    }
            }
            return flag;
        }
    /**
     * @功能 根据指定格式将时间字符串格式化
     * @描述 时区为东八区(china)
     * @参数 Object obj
     * @返回值 boolean
     */
      public static Date getDateByModel(String dateStr,String model){
	    Date date = null;
            DateFormat dateFormat = new SimpleDateFormat(model,Locale.CHINA);
            dateFormat.setLenient(false);// 不设置时间自动转化 例如 25点->1点 
            try {
		date = dateFormat.parse(dateStr);
	    } catch (ParseException e) {
	    } 
            return date;
      }
      
      /**
       * @功能 身份证验证 15位或18位
       * @描述 18位 六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码<br/>
       * 三位数字顺序码：对同年、月、日出生的人员编定的顺序号  这三位数的第三位数 奇数分给男性，偶数分给女性<br/>
       * 第十八位数字的计算方法为：<br/>
       * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2<br/>
       * 2.将这17位数字和系数相乘的结果相加。<br/>
       * 3.用加出来和除以11，看余数是多少？<br/>
       * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。<br/>
       * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。<br/>
       *  例如：某男性的身份证号码是34052419800101001X。我们要看看这个身份证是不是合法的身份证。<br/>
       *  首先：我们得出，前17位的乘积和是189<br/>
       *  然后：用189除以11得出的结果是17 + 2/11，也就是说余数是2。<br/>
       * 最后：通过对应规则就可以知道余数2对应的数字是x。所以，这是一个合格的身份证号码。<br/>
       * 15位身份证 与18位的区别:1. 18位 1898 15位 98 2.没有最后一位验证码
       * @参数 Object obj
       * @返回值 boolean
       */
      public static Boolean checkId(Object obj){
	  if(!isEmpty(obj)){
	      String id = obj.toString();
	      if(id.matches("(\\d{15})|(\\d{17}(\\d|(?i)x))")){
		  if(id.length()==15){
		      return true;
		  }
		  return id.substring(obj.toString().length()-1).equalsIgnoreCase(doVerify(id)+"") ; 
	      } 
          }
          return false;
      }
      /**
       * @description:身份证加权验证
       * @param args :
       */
      public static char doVerify(String id){ 
      	char pszSrc[]=id.toCharArray(); 
      	int iS = 0; 
      	int iW[]={7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2}; 
      	char szVerCode[] = new char[]{'1','0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; 
      	for(int i=0;i<17;i++){ 
      	iS += (int)(pszSrc[i]-'0') * iW[i]; 
      	} 
      	int iY = iS%11; 
      	return szVerCode[iY]; 
       }
      
      /**
       * @功能 检查手机号
       * @描述 以1开始 一共11位
       * @参数 Object obj
       * @返回值 boolean
       */
      public static Boolean checkPhone(Object obj){
		if(!isEmpty(obj)){
			return obj.toString().matches("(13|14|15|18)\\d{9}");
		}
	  	return false;
      }
      
      /**
       * @功能 电话号码校验（座机）
       * @描述  区号(3|4位)-座机号(8位|7位)-分机号(3位)
       * @参数 Object obj
       * @返回值 boolean
       */
      public static Boolean checkTel(Object obj){
		  if(!isEmpty(obj)){
		      return obj.toString().matches("((\\d{3}|\\d{4})-(\\d{8}|\\d{7}))(-\\d{3}){0,3}");
	      }
		  return false;
      }
      
      /**
       * @功能 替换特殊字符为全角字符,或者将原有特殊字符前加转义符,包括以下字符【.,'"\】
       * @参数 String str
       * @参数 boolean flag true：转换为全角字符；false：将原有特殊字符前加转义符
       * @返回值 转换特殊字符后的字符串
       * @author: JiJianMing
       */
      public static String replaceSpecialChar(String str, boolean flag) {
          if (str == null) {
              return str;
          }
          String[] oldChars = new String[] { "\\", ".", ",", "'", "\"" };
          String[] newChars = null;
          if (flag) {
              newChars = new String[] { "＼", "．", "，", "＇", "＂" };
          } else {
              newChars = new String[] { "\\\\\\\\", "\\\\.", "\\\\,","\\\\'", "\\\\\"" };
          }
          Pattern pattern = null;
          Matcher matcher = null;
          for (int i = 0; i < oldChars.length; i++) {
              pattern = Pattern.compile("\\" + oldChars[i] + "+");
              matcher = pattern.matcher(str);
              str = matcher.replaceAll(newChars[i]);
          }
          return str;
      }
}
