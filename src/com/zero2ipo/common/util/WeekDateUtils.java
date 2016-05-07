package com.zero2ipo.common.util;

import com.zero2ipo.common.entity.eeh.WeekDate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

public class WeekDateUtils {
    public static String weeks[]={"周一","周二","周三","周四","周五","周六","周日"};
    public static String ONE="1";
    public static String ZERO="0";
    /**
     * 获取本周日期
     * @return
     */
    public  static List<WeekDate> getCurrentWeekDate()  {
        List<WeekDate> list=new ArrayList<WeekDate>();
        try{
            Calendar cal = Calendar.getInstance();
            int date = cal.get(Calendar.DAY_OF_MONTH);
            int n = cal.get(Calendar.DAY_OF_WEEK);
            if (n == 1) {
                n = 7;
            } else {
                n = n - 1;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat mdFormat = new SimpleDateFormat("MM月dd日");
            for (int i = 1; i <= 7; i++) {
                cal.set(Calendar.DAY_OF_MONTH, date + i - n);
                WeekDate weekDate=new WeekDate();
                weekDate.setWeek(weeks[i-1]);
                weekDate.setDate(sdf.format(cal.getTime()));
                weekDate.setMonthAndDay(mdFormat.format(cal.getTime()));
                if(n==i){
                    weekDate.setIsCurrent(ONE);
                }else{
                    weekDate.setIsCurrent(ZERO);
                }
                list.add(weekDate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
    /** * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }
    public static void main(String[] args){
        //今天是2014-12-25 星期四
        String weekOfDate = null;
        weekOfDate = getWeekOfDate(null);
        System.out.println(weekOfDate);
        //输出 星期四

        /*Date date = new Date();
        date.setDate(24);
        weekOfDate = getWeekOfDate(date);
        System.out.println(weekOfDate);*/
        //输出 星期三
    }

    /**
     * 获取当前时间：时分
     * @return
     * @throws DataFormatException
     */
    public static String getDateNowHm() throws DataFormatException {
        try {
            SimpleDateFormat e = new SimpleDateFormat("HH:mm");
            e.setLenient(false);
            return e.format(new java.util.Date());
        } catch (Exception var1) {
            throw new DataFormatException("源数据格式错误");
        }
    }
    //获得当前时间和当前时间前多少分钟的时间
    public static String getBeforeMinutes(int minute){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar c = new GregorianCalendar();
        Date date = new Date();
        System.out.println("系统当前时间      ："+df.format(date));
        c.setTime(date);//设置参数时间
        c.add(Calendar.MINUTE,-minute);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
        date=c.getTime(); //这个时间就是日期往后推一天的结果
        String str = df.format(date);
       return str;
    }
    //获得当前时间和当前时间前多少分钟的时间
    public static String getAfterMinutes(int minute){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar c = new GregorianCalendar();
        Date date = new Date();
        System.out.println("系统当前时间      ："+df.format(date));
        c.setTime(date);//设置参数时间
        c.add(Calendar.MINUTE,+minute);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
        date=c.getTime(); //这个时间就是日期往后推一天的结果
        String str = df.format(date);
        return str;
    }

}
