package com.zero2ipo.eeh.course.bizc.impl;

import com.zero2ipo.common.util.WeekDateUtils;
import com.zero2ipo.eeh.Timetable.bizc.ITimetableService;
import com.zero2ipo.eeh.Timetable.bo.TimetableBo;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.CourseBo;
import com.zero2ipo.eeh.course.bo.CourseConstants;
import com.zero2ipo.eeh.teacher.bizc.ITeacherService;
import com.zero2ipo.eeh.teacher.bo.TeacherBo;
import com.zero2ipo.eeh.time.bizc.ITimeService;
import com.zero2ipo.eeh.time.bo.TimeBo;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service("CourseService")
public class CourseServiceImpl extends IbatisBaseDao implements ICourseService {

    @Autowired
    @Qualifier("TimetableService")
    private ITimetableService TimetableService ;
    @Autowired
    @Qualifier("TimeService")
    private ITimeService TimeService ;
 @Autowired
    @Qualifier("TeacherService")
    private ITeacherService teacherService ;

    public final static String NAMESPACE="mobile.Course.";
    public final static String COMMON="Course";
    public final static String ADD=NAMESPACE+"add_"+COMMON;
    public final static String UPDATE=NAMESPACE+"upd_"+COMMON;
    public final static String DELETE=NAMESPACE+"del_"+COMMON;
    public final static String FINDALLLIST=NAMESPACE+"find"+COMMON+"List";
    public final static String FINDALLLISTCOUNT=NAMESPACE+"find"+COMMON+"ListCount";
    public final static String FINDBYID=NAMESPACE+"find"+COMMON+"ById";


    @Override
    public boolean add(CourseBo bo) {
        boolean flag=false;
        try{
            this.insert(ADD, bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(CourseBo bo) {
        boolean flag=false;
        try{
            this.update(UPDATE, bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(String ids) {
        boolean flag=false;
        try {
            Map map = new HashMap();
            map.put("id", ids.split(","));
            this.delete(DELETE, map);
            // 删除成功
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "del_Course 删除出错", e);
            throw new BaseException("删除出错！", e);
        }
        return flag;
    }

    @Override
    public List<CourseBo> findAllList(Map<String, Object> queryMap) {
        List<CourseBo> list = null;
        try {
            list = (List<CourseBo>) this.queryAll(FINDALLLIST, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public List<CourseBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<CourseBo> list = null;
        try {
            list = (List<CourseBo>) this.queryAll(FINDALLLIST, queryMap);
            int size=list.size();
            for(int i=0;i<size;i++){
                //String tbId=list.get(i).getTbId();
                //根据教学楼id查询教学楼名称
                //TeachingBuildingBo teachingBuildingBo=teachingBuildingService.findById(tbId);
                //list.get(i).setTeachingBuildingBo(teachingBuildingBo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        int count = 0;
        try {
            count = (Integer) this.query(FINDALLLISTCOUNT, queryMap);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserCouponListCount查询优惠券支付个数", e);
        }
        return count;
    }
    public CourseBo findById(String id){
        CourseBo bo=null;
        try {
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (CourseBo) this.query(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
    public  CourseBo getCourseBoByMap(Map<String,Object> queryMap){
        CourseBo bo=null;
        try {
            bo = (CourseBo) this.query(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;

    }

    /**
     * 获取当前的培优课程
     * @param classRoom
     * @return
     */
    public CourseBo getCurrentCourse(String classRoom){
        //String currentWeek= DateUtil.getWeek()
        Map<String,Object> queryMap=new HashMap<String, Object>();
        String week= WeekDateUtils.getWeekOfDate(new Date());
        queryMap.put("week",week);
        queryMap.put("classRoom",classRoom);
        List<CourseBo> list=findAllList(queryMap);
        CourseBo currentCourse = getCurrentCourse(list);
        return currentCourse;

    }

    /**
     * 根据课程集合获取当前时间课程
     * @param list
     * @return
     */
    private CourseBo getCurrentCourse(List<CourseBo> list) {
        CourseBo currentCourse=null;
        int total=list.size();
        for(int i=0;i<total;i++){
            CourseBo bo=list.get(i);
            String schoolTime=bo.getSchoolTime();
            String times[]=schoolTime.replace("-","-").split("-");
            if(times.length==2){
                String start=times[0];
                String end=times[1];
                String currentTime= DateUtil.getCurrentDate("HH:mm");
                int t1=currentTime.compareTo(start);
                int t2=currentTime.compareTo(end);
                if(t1>=0&&t2<=0){
                    //当前课程
                    currentCourse=bo;
                }
            }
        }
        return currentCourse;
    }

    /**
     * 根据当前教室查询当前日程课程
     * @param classRoom
     * @return
     */
    public CourseBo getCurrentRiChangCourse(String classRoom){
        List<CourseBo> list=null;
        Map<String,Object> queryMap=new HashMap<String, Object>();
        queryMap.put("gradeName",classRoom);
        String week= WeekDateUtils.getWeekOfDate(new Date());
        queryMap.put("week", CourseConstants.weekMap.get(week));
        List<TimetableBo> ll= TimetableService.findAllList(queryMap);
        //根据星期和教室查询出来的日常课程表肯定只有一个
        if(null!=ll&&ll.size()>0){
            TimetableBo timetableBo=ll.get(0);
            list=getCourseListByTimeTable(timetableBo);
        }
      CourseBo currentCourse = null;
        if(null!=list&&list.size()>0){
            currentCourse=getCurrentCourse(list);
        }
      return currentCourse;
    }
    /**
     * 日常课程表转化培优课list
     * @return
     */
    public List<CourseBo> getCourseListByTimeTable(TimetableBo timetableBo){
        List<CourseBo> list=new ArrayList<CourseBo>();
        String firstClass=timetableBo.getFirstClass();//第一节课
        String classRoom=timetableBo.getgradeName();
        String week=timetableBo.getWeek();
        //去时段表里查询第一节课上课时间
        CourseBo courseBo1=getCourseBoByKeMuAndClassRoom(firstClass,classRoom,1,week);
        list.add(courseBo1);
        //第二节课
        String secondClass=timetableBo.getSecondClass();
        CourseBo courseBo2=getCourseBoByKeMuAndClassRoom(secondClass,classRoom,2,week);
        list.add(courseBo2);
        //第3节课
        String threeClass=timetableBo.getThreeClass();
        CourseBo courseBo3=getCourseBoByKeMuAndClassRoom(threeClass,classRoom,3,week);
        list.add(courseBo3);
        //第4节课
        String fourClass=timetableBo.getFourClass();
        CourseBo courseBo4=getCourseBoByKeMuAndClassRoom(fourClass,classRoom,4,week);
        list.add(courseBo4);
        //第5节课
        String fiveClass=timetableBo.getFiveClass();
        CourseBo courseBo5=getCourseBoByKeMuAndClassRoom(fiveClass,classRoom,5,week);
        list.add(courseBo5);
        //第6节课
        String sixClass=timetableBo.getSixClass();
        CourseBo courseBo6=getCourseBoByKeMuAndClassRoom(sixClass,classRoom,6,week);
        list.add(courseBo6);
        //第7节课
        String sevenClass=timetableBo.getSevenClass();
        CourseBo courseBo7=getCourseBoByKeMuAndClassRoom(sevenClass,classRoom,7,week);
        list.add(courseBo7);
        //第8节课
        String eightClass=timetableBo.getEightClass();
        CourseBo courseBo8=getCourseBoByKeMuAndClassRoom(eightClass,classRoom,8,week);
        list.add(courseBo8);
        //第9节课
        String nineClass=timetableBo.getSecondClass();
        CourseBo courseBo9=getCourseBoByKeMuAndClassRoom(nineClass,classRoom,9,week);
        list.add(courseBo9);
        return list;

    }
    /**
     * 日常课程表转换为培优课
     * @param subjectId
     * @param classId
     * @return
     */
    public CourseBo getCourseBoByKeMuAndClassRoom(String subjectId,String classId,int index,String week){
        String schoolTime=getSchoolTimeByCourseName(index);
        CourseBo firstCourse=new CourseBo();
        firstCourse.setSchoolTime(schoolTime);
        firstCourse.setCourseName(subjectId);
        firstCourse.setWeek(week);
        //根据教室和课程名去教师表里面查询
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("subjectId",subjectId);//科目
        map.put("classId",classId);//授课教室
        List<TeacherBo> teacherBoList=teacherService.findAllList(map);
        //查询出来的只有一个
        if(null!=teacherBoList&&teacherBoList.size()>0){
            TeacherBo teacherBo=teacherBoList.get(0);
            if(!StringUtil.isNullOrEmpty(teacherBo)){
                firstCourse.setTeacher(teacherBo.getName());
            }
        }
        return firstCourse;
    }
    /**
     * 通过日常课程表的课程名称（第一节，第二节）去时段表里面查询第一节所对应的上课时间
     * @return
     */
    public String getSchoolTimeByCourseName(int index){
        String schoolTime="";
        Map<String,Object> queryMap=new HashMap<String, Object>();
        queryMap.put("name",CourseConstants.courses[index]);//课程名
        List<TimeBo> timeBoList= TimeService.findAllList(queryMap);
        if(null!=timeBoList&&timeBoList.size()>0){
            schoolTime=timeBoList.get(0).getTime();
        }
        return schoolTime;

    }
    public static void main(String args[]){
    String day=DateUtil.getCurrentDate("yyyy-MM-dd");
    String week= WeekDateUtils.getWeekOfDate(new Date());
    System.out.println(week);
}




}
