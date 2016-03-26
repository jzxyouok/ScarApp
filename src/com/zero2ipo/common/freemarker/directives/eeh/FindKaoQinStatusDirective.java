package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.eeh.Attendance.bizc.IAttendanceService;
import com.zero2ipo.eeh.Attendance.bo.AttendanceBo;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.CourseBo;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.framework.util.StringUtil;
import freemarker.core.Environment;
import freemarker.template.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 根据当前教室，当前课程，学生卡号
 * @author zhengYunfei
 *
 */
public class FindKaoQinStatusDirective implements TemplateDirectiveModel{
	private static  final String  PARAM_GRADE_NAME="gradeName";
	private static  final String  PARAM_CARD_NO="cardNo";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<AttendanceBo> list=null;
		AttendanceBo bo=null;
		try {
			String classRoom= DirectiveUtils.getString(PARAM_GRADE_NAME, params);
			String cardNo= DirectiveUtils.getString(PARAM_CARD_NO, params);
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("classRoom",classRoom);//上课教室
			queryMap.put("cardNo",cardNo);//卡号
			//首先根据当前教室，去查询当前培优课程
			CourseBo current=courseService.getCurrentCourse(classRoom);
			if(StringUtil.isNullOrEmpty(current)){//如果不存在培优课程，再查询日常课程
				//current=courseService.getCurrentRiChangCourse(classRoom);
			}
			if(StringUtil.isNullOrEmpty(current)){
				queryMap.put("courseName","-999999");//假设一个不存在的课程
			}else{
				queryMap.put("courseName",current.getCourseName());//当前课程

			}
			String day= DateUtil.getCurrentDate("yyyy-MM-dd");
			queryMap.put("dayTime",day);
			list=attendanceService.findAllList(queryMap);
			if(null!=list&&list.size()>0){
				bo=list.get(0);
			}
			String status="";
			if (null!=bo){
				 status=bo.getType();
			}
			env.setVariable("status", ObjectWrapper.DEFAULT_WRAPPER.wrap(status));
			env.setVariable("kaoqin", ObjectWrapper.DEFAULT_WRAPPER.wrap(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	@Resource(name="AttendanceService")
	private IAttendanceService attendanceService ;
	@Resource(name="CourseService")
	private ICourseService courseService ;

}
