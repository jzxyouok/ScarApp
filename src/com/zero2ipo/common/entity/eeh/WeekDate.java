package com.zero2ipo.common.entity.eeh;

/**
 * Created by Administrator on 2016/3/11.
 */
public class WeekDate {
    private String week;//星期如周一，周二
    private String date;//日期2016-03-11标准日期
    private String monthAndDay;//日期3月11日
    private String isCurrent;//是否是当前日期0不是1：是

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonthAndDay() {
        return monthAndDay;
    }

    public void setMonthAndDay(String monthAndDay) {
        this.monthAndDay = monthAndDay;
    }

    public String getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(String isCurrent) {
        this.isCurrent = isCurrent;
    }
}
