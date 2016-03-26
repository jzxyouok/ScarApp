package com.zero2ipo.eeh.course.web;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.eeh.Timetable.bizc.ITimetableService;
import com.zero2ipo.eeh.Timetable.bo.TimetableBo;
import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.ComparatorCourse;
import com.zero2ipo.eeh.course.bo.CourseBo;
import com.zero2ipo.eeh.course.bo.CourseConstants;
import com.zero2ipo.eeh.teacher.bizc.ITeacherService;
import com.zero2ipo.eeh.teacher.bo.TeacherBo;
import com.zero2ipo.eeh.time.bizc.ITimeService;
import com.zero2ipo.eeh.time.bo.TimeBo;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 教学楼ctrl
 * Created by Administrator on 2016/2/24.
 */
@Controller
@RequestMapping("/Course")
public class CourseCtrl extends BaseCtrl {
    @Autowired
    @Qualifier("CourseService")
    private ICourseService CourseService ;

    @Autowired
    @Qualifier("classRoomService")
    private IClassRoomService classRoomService;
    @Autowired
    @Qualifier("TimetableService")
    private ITimetableService TimetableService ;
    @Autowired
    @Qualifier("TeacherService")
    private ITeacherService teacherService ;
    @Autowired
    @Qualifier("TimeService")
    private ITimeService TimeService;
    /**
     * 初始化页面
     * @return
     */
    @RequestMapping("forInit.html")
    public ModelAndView forInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/Course/list.jsp");
        //查询所有班级
       // getSelectGradeList(mv);
        return mv;
    }
    /**
     * 初始化页面
     * @return
     */
    @RequestMapping("peiyouke.html")
    public ModelAndView peiyouke() {
        ModelAndView mv = new ModelAndView("/eeh/Course/pewClass.jsp");
        return mv;
    }
    /**
     * 初始化json数据
     * @return
     */
    @RequestMapping("findAllList.html")
    @ResponseBody
    public Map<String,Object> findAllList(CourseBo bo,int weekIndex){
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        String classRoom="";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if(!StringUtil.isNullOrEmpty(bo)){
                String kemu=bo.getKemu();
                 classRoom=bo.getClassRoom();
                String seatSetStatus=bo.getSeatSetStatus();
                String week=bo.getWeek();
                if(!StringUtil.isNullOrEmpty(kemu)){
                    map.put("kemu",kemu);
                }
                if(!StringUtil.isNullOrEmpty(week)){
                    map.put("week",week);
                }
                if(!StringUtil.isNullOrEmpty(classRoom)){
                    map.put("classRoom",classRoom);
                }
                if(!StringUtil.isNullOrEmpty(seatSetStatus)){
                    map.put("seatSetStatus",seatSetStatus);
                }
            }
            int total=0;
            total=CourseService.getTotal(map);
            List<CourseBo> list= null;
            if(total>0){//查询培优课程
                list = CourseService.findAllList(map);
                List<CourseBo> richangCourseList=getRiChangCourseList(weekIndex, classRoom);
                list.addAll(richangCourseList);
                ComparatorCourse comparator=new ComparatorCourse();
                Collections.sort(list, comparator);
            }else{//培优课程不存在，再查询日常课程
                list = getRiChangCourseList(weekIndex, classRoom);
            }
            jsonMap.put("result", list);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "forLinkTypeinitAjax.html:管理人分类信息初始化有误", e);
        }
        return jsonMap;
    }

    /**
     * 查询日常课程列表
     * @param weekIndex
     * @param classRoom
     * @return
     */
    private List<CourseBo> getRiChangCourseList(int weekIndex, String classRoom) {
        List<CourseBo> list=null;
        Map<String,Object> queryMap=new HashMap<String, Object>();
        queryMap.put("gradeName",classRoom);
        queryMap.put("week", CourseConstants.weeks[weekIndex]);
        List<TimetableBo> ll= TimetableService.findAllList(queryMap);
        //根据星期和教室查询出来的日常课程表肯定只有一个
        if(null!=ll&&ll.size()>0){
            TimetableBo timetableBo=ll.get(0);
            list=getCourseListByTimeTable(timetableBo);
        }
        return list;
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

    /**
     * 日常课程表转化培优课list
     * @param bo
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
 /******************************************新增*********************************************************/
    /**
     * 新增页面初始化
     * @return
     */
    @RequestMapping("forAddInitPage.html")
    public ModelAndView forAddInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/Course/add.jsp");
        return mv;
    }
    /**
     * 添加保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forAddAjax.html")
    @ResponseBody
    public Map<String,Object> addSave(CourseBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=CourseService.add(bo);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "saveOrder", e);
            throw new BaseException("saveOrder 异常！");
        }
        result.put("success", flg);
        return result;
    }
    /******************************************修改*********************************************************/
    /**
     * 新增页面初始化
     * @return
     */
    @RequestMapping("forUpdateInitPage.html")
    public ModelAndView forUpdateInitPage(String id) {
        ModelAndView mv = new ModelAndView("/eeh/Course/update.jsp");
        CourseBo bo=CourseService.findById(id);
        mv.addObject("bo", bo);
        return mv;
    }


    /**
     * 修改保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forUpdateAjax.html")
    @ResponseBody
    public Map<String,Object> updateSave(CourseBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=CourseService.update(bo);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "forUpdateAjax", e);
            throw new BaseException("forUpdateAjax 异常！");
        }
        result.put("success", flg);
        return result;
    }
    /******************************************删除操作*********************************************************/

    @RequestMapping("delSave.html")
    @ResponseBody
    public Map<String,Object> delSave(String ids) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=CourseService.delete(ids);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "delSave", e);
            throw new BaseException("delSave 异常！");
        }
        result.put("success", flg);
        return result;
    }
}
