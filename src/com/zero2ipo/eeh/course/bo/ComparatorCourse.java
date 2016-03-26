package com.zero2ipo.eeh.course.bo;

import java.util.Comparator;

public class ComparatorCourse implements Comparator{

    public  int compare(Object arg0, Object arg1) {
        CourseBo user0=(CourseBo)arg0;
        CourseBo user1=(CourseBo)arg1;
        //比较上课时间
        int flag=0;
        if(flag==0){
            return user0.getSchoolTime().compareTo(user1.getSchoolTime());
        }else{
            return flag;
        }
    }

}
