/**
 * Copyright (c) 2008 CEPRI,Inc.All rights reserved.
 * Created by 2008-4-18 
 */
package com.zero2ipo.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.FieldPosition;

/**
 * @title :数据工具
 * @description :数据类型转化常用操作包
 * @author: zhengYunFei
 * @data: 2008-4-30
 */
public class DataTypeTranUtil {

    /***************************************************************************
     * java数值类型转换 1,提供数据类相互转换操作 2,提供所以String类的转换操作
     **************************************************************************/
    private static DecimalFormat df = new DecimalFormat();

    /** String转换成int * */
    public static int toInt(String str) {
        int rv = 0;
        try {
            rv = Integer.parseInt(str);
        } catch (Exception ex) {
        }
        return rv;
    }

    /** double数格式转换为String，参数n为小数点保留的位数 * */
    public static String roundData(double v, int n) {
        String fstr = "###,##0";
        if (n > 0)
            fstr += ".";
        for (int i = 0; i < n; i++)
            fstr += "0";
        DecimalFormat df = new DecimalFormat(fstr);
        return df.format(v).toString();
    }

    /** 该函数主要提供其他类型转变为带两位小数的转变 * */
    public static String getShort(String tStr) {
        try {
            df.applyPattern("#,##0.00");
            return format(tStr);
        } catch (Exception e) {
            return tStr;
        }
    }

    /** 该函数主要提供其他类型到整形的转变 * */
    public static String getInt(String tStr) {
        try {
            df.applyPattern("#00");
            return format(tStr);
        } catch (Exception e) {
            return tStr;
        }
    }

    /** 字符串以给定的格式进行转换 * */
    public static String getF(String tStr, String formate) {
        try {
            df.applyPattern(formate);
            return format(tStr);
        } catch (Exception e) {
            return tStr;
        }
    }

    /** 字符串格式化处理 * */
    public static String format(String tStr) {
        StringBuffer sb = new StringBuffer();
        df.format(Double.valueOf(tStr).doubleValue(), sb, new FieldPosition(
                df.INTEGER_FIELD));
        return sb.toString();
    }

    /** int转换为long * */
    public static long int2long(int tInt) {
        return Long.valueOf(String.valueOf(tInt)).longValue();
    }

    /** String转换为long * */
    public static long toLong(String tStr) {
        try {
            return Long.parseLong(tStr);
        } catch (Exception e) {
            return -1;
        }
    }

    /** double转换为int * */
    public static int double2int(double tDouble) {
        return (new Double(tDouble)).intValue();
    }

    /** 字符串到int的转化 * */
    public static int Str2Int(String tStr) {
        int re = 0;
        try {
            re = Integer.valueOf(tStr).intValue();
        } catch (Exception e) {
            re = 0;
        }
        return re;
    }

    /** int转换为字符串的转化 * */
    public static String Int2Str(int sInt) {
        String re = "";
        re = String.valueOf(sInt);
        return re;
    }

    /** 字符串转换为double * */
    public static double Str2Double(String tStr) {
        double re = 0;
        try {
            re = Double.valueOf(tStr).doubleValue();
        } catch (Exception e) {
            re = 0;
        }
        return re;
    }

    /** object转换为字符串 * */
    public static String getString(Object a) {
        if (a == null)
            return "";
        else
            return a.toString();
    }

    /** object转换为字符串 * */
    public static String getString(Object a, String re) {
        if (a == null)
            return re;
        else
            return a.toString();
    }

    /** 将String 转换为Long 如果产生异常，返回0 * */
    public static long Str2Long(String a) {
        try {
            return (Long.valueOf(a)).longValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /** 将String 转换为BigDecimal 如果产生异常，返回0 * */
    public static BigDecimal Str2BigDecimal(String a) {
        try {
            return (new BigDecimal(a));
        } catch (Exception e) {
            return BigDecimal.valueOf(0);
        }
    }

    private String HanDigiStr[] = new String[] { "零", "壹", "贰", "叁", "肆", "伍",
            "陆", "柒", "捌", "玖" };

    private String HanDiviStr[] = new String[] { "", "拾", "佰", "仟", "万", "拾",
            "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰",
            "仟", "万", "拾", "佰", "仟" };

    private String moneyToHanStr(String NumStr) { // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
        String RMBStr = "";
        boolean lastzero = false;
        boolean hasvalue = false; // 亿、万进位前有数值标记
        int len, n;
        len = NumStr.length();
        if (len > 15)
            return "数值过大!";
        for (int i = len - 1; i >= 0; i--) {
            if (NumStr.charAt(len - i - 1) == ' ')
                continue;
            n = NumStr.charAt(len - i - 1) - '0';
            if (n < 0 || n > 9)
                return "输入含非数字字符!";

            if (n != 0) {
                if (lastzero)
                    RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
                // 除了亿万前的零不带到后面
                // if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) ) //
                // 如十进位前有零也不发壹音用此行
                //if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
                RMBStr += HanDigiStr[n];
                RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
                hasvalue = true; // 置万进位前有值标记

            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
                    RMBStr += HanDiviStr[i]; // “亿”或“万”
            }
            if (i % 8 == 0)
                hasvalue = false; // 万进位前有值标记逢亿复位
            lastzero = (n == 0) && (i % 4 != 0);
        }

        if (RMBStr.length() == 0)
            return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
        return RMBStr;
    }

    public String NumToRMBStr(double val) {
        String SignStr = "";
        String TailStr = "";
        long fraction, integer;
        int jiao, fen;

        if (val < 0) {
            val = -val;
            SignStr = "负";
        }
        if (val > 99999999999999.999 || val < -99999999999999.999)
            return "数值位数过大!";
        // 四舍五入到分
        long temp = Math.round(val * 100);
        integer = temp / 100;
        fraction = temp % 100;
        jiao = (int) fraction / 10;
        fen = (int) fraction % 10;
        if (jiao == 0 && fen == 0) {
            TailStr = "整";
        } else {
            TailStr = HanDigiStr[jiao];
            if (jiao != 0){
                //TailStr += "角";
            	TailStr += "角";
            	if(fen == 0){
                	TailStr += "整";
                }
            }
            if (integer == 0 && jiao == 0) // 零元后不写零几分
                TailStr = "";
            if (fen != 0)
                TailStr += HanDigiStr[fen] + "分";
        }

        // 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
        // if( !integer ) return SignStr+TailStr;

        return "￥" + SignStr + moneyToHanStr(String.valueOf(integer)) + "元"
                + TailStr;
    }
}
