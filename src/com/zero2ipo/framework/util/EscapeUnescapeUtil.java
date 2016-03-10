package com.zero2ipo.framework.util;
/**
 * 加密解密
 * @author Administrator
 *
 */
public class EscapeUnescapeUtil {
	
	/***
	* 加密方法
	* 描述：code 待加密的js代码
	* 调用方式：compile(code)
	**/
	public static String compile(String src){
		String encode = "";
		for(int i=0;i<src.length();i++){
			encode = encode+(char)(src.charAt(i)+src.length());
		}
		return escape(encode);
	}
	/***
	* 解密方法
	* 描述：code 加密后的js代码
	* 调用方式：uncompile(code)
	**/
	public static String uncompile(String src){
		src=unescape(src);
		String uncode = "";
		for(int i=0;i<src.length();i++){
			uncode = uncode+(char)(src.charAt(i)-src.length());
		}
		return uncode;
	}
	
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			//if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
			if (isChar(String.valueOf(j)))//判断是否是数字或者字母
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	//判断字符串是否为只包括字母和数字 
    public   static   boolean   isChar(String   validString){ 
            byte[]   tempbyte=validString.getBytes(); 
            for(int   i=0;i <validString.length();i++)   { 
                    //     by=tempbyte[i]; 
                    if((tempbyte[i] <48)||((tempbyte[i]> 57)&(tempbyte[i] <65))||(tempbyte[i]> 122)||((tempbyte[i]> 90)&(tempbyte[i] <97)))   { 
                            return   false; 
                    } 
            } 
            return   true; 
    }

	//判断字符串是否只包括字母
	public   static   boolean   isLetter(String   validString){ 
        byte[]   tempbyte=validString.getBytes(); 
        for(int   i=0;i <validString.length();i++)   { 
                //by=tempbyte[i]; 
                if((tempbyte[i] <65)||(tempbyte[i]> 122)||((tempbyte[i]> 90)&(tempbyte[i] <97)))   { 
                        return   false; 
                } 
        } 
        return   true; 
    }
	
    //判断是否为数字组成的字串 
    public   static   boolean   isNumber(String   validString){ 
            byte[]   tempbyte=validString.getBytes(); 
            for(int   i=0;i <validString.length();i++)   { 
                    //by=tempbyte[i]; 
                    if((tempbyte[i] <48)||(tempbyte[i]> 57)){ 
                            return   false; 
                    } 
            } 
            return   true; 
    }
    /**
	public static void main(String [] args){
		String oo = "http://10.85.24.54:8080/jsp/obo/onlinepay/buyelec.jsp";
		String o = compile(oo);
		System.out.println(o);
	}
    **/
}
