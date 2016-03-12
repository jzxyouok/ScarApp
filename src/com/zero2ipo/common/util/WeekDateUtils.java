package com.zero2ipo.common.util;

import com.zero2ipo.common.entity.eeh.WeekDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeekDateUtils {
    public static String weeks[]={"周一","周二","周三","周四","周五","周六","周日"};
    public static String ONE="1";
    public static String ZERO="0";
    public static void main(String[] args)  {

    }

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
}
