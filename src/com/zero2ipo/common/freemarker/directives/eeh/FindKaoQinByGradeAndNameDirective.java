package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.eeh.Attendance.bizc.IAttendanceService;
import com.zero2ipo.eeh.Attendance.bo.AttendanceBo;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.student.bizc.IStudentService;
import com.zero2ipo.eeh.student.bo.StudentBo;
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
 * 根据学生姓名和班级查询考勤
 * @author zhengYunfei
 *
 */
public class FindKaoQinByGradeAndNameDirective implements TemplateDirectiveModel{
	private static  final String  PARAM_GRADE_NAME="gradeName";
	private static  final String  PARAM_CLASS_ROOM="classRoom";
	private static  final String  PARAM_NAME="name";
	private static  final String  PARAM_COURSE_NAME="courseName";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<AttendanceBo> list=null;
		AttendanceBo bo=null;
		try {
			String gradeName= DirectiveUtils.getString(PARAM_GRADE_NAME, params);
			String name= DirectiveUtils.getString(PARAM_NAME, params);
			String classRoom= DirectiveUtils.getString(PARAM_CLASS_ROOM, params);
			String courseName= DirectiveUtils.getString(PARAM_COURSE_NAME, params);
			//根据教室
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("classId",gradeName);//学生班级
			queryMap.put("name",name);//学生姓名
			List<StudentBo> ll=studentService.findAllList(queryMap);
			StudentBo studentBo=new StudentBo();
			if(!StringUtil.isNullOrEmpty(ll)&&ll.size()>0){
				studentBo=ll.get(0);
			}
			//再根据今天的日期+学生卡号+课程查询考勤
			String day= DateUtil.getCurrentDate("yyyy-MM-dd");
			queryMap.put("dayTime",day);
			queryMap.put("cardNo",studentBo.getCardNo());
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
	@Resource(name="StudentService")
	private IStudentService studentService ;


}
