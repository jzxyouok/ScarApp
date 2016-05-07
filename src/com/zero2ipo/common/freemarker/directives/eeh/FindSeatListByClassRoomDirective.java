package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.common.util.WeekDateUtils;
import com.zero2ipo.eeh.Attendance.bizc.IAttendanceService;
import com.zero2ipo.eeh.Attendance.bo.AttendanceBo;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.CourseConstants;
import com.zero2ipo.eeh.seat.bizc.ISeatService;
import com.zero2ipo.eeh.seat.bo.SeatBo;
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
 * 根据所在班级和当前时间查询座位表
 * @author zhengYunfei
 *
 */
public class FindSeatListByClassRoomDirective implements TemplateDirectiveModel{
	private static  final String  PARAM_GRADE_NAME="gradeName";

	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<SeatBo> seatsList=null;
		try {
			String classRoom= DirectiveUtils.getString(PARAM_GRADE_NAME, params);
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("classRoom",classRoom);
			//除了根据教室查询座次表，还要根据星期和上课时间
			//获取今天是周几
			String week= WeekDateUtils.getWeekOfDate(null);
			//获取当前系统时间 时分
			String time= WeekDateUtils.getDateNowHm();
			String getAfterMinutes=WeekDateUtils.getBeforeMinutes(30);
			queryMap.put("week", week);
			queryMap.put("schoolTimeStart",getAfterMinutes);
			queryMap.put("schoolTimeEnd",time);
			queryMap.put("seatType", CourseConstants.SEAT_TYPE_1);
			seatsList=SeatService.findAllList(queryMap);
			String seatTypeName="";
			int seatType=1;
			if(null!=seatsList&&seatsList.size()>0){
				seatTypeName=CourseConstants.SEAT_TYPE_1_name;
			}else{//没有培优座位表，那显示班级座位表
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("classRoom",classRoom);
				map.put("seatType", CourseConstants.SEAT_TYPE_0);//日常座位表
				//保存座位表类型
				seatTypeName=CourseConstants.SEAT_TYPE_0_NAME;
				seatsList=SeatService.findAllList(map);
			}
			env.setVariable("seatType", ObjectWrapper.DEFAULT_WRAPPER.wrap(seatTypeName));//培优座次

			//查询考勤状态
			seatsList=getKaoQinStatus(classRoom, seatsList,seatType);
			env.setVariable("seatsList", ObjectWrapper.DEFAULT_WRAPPER.wrap(seatsList));
			if(!StringUtil.isNullOrEmpty(seatsList)&&seatsList.size()>0){
				SeatBo firstSeat=seatsList.get(0);
				if(firstSeat.getCell()<=8){
					firstSeat.setFlg(1);
				}
				env.setVariable("firstSeat", ObjectWrapper.DEFAULT_WRAPPER.wrap(firstSeat));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}

	private List<SeatBo> getKaoQinStatus(String classRoom, List<SeatBo> seatsList,int type) {
		//查询当前课程
		String courseName="";//当前课程名
		//if(type==CourseConstants.SEAT_TYPE_0){
			//CourseBo courseBo=courseService.getCurrentCourse(classRoom);
			//if(!StringUtil.isNullOrEmpty(courseBo))
				//courseName=courseBo.getCourseName();
		//}
		//if(type==CourseConstants.SEAT_TYPE_1){
		//	CourseBo courseBo=courseService.getCurrentRiChangCourse(classRoom);
		//	if(!StringUtil.isNullOrEmpty(courseBo))
		//	courseName=courseBo.getCourseName();
		//}
		String day= DateUtil.getCurrentDate("yyyy-MM-dd");//今天日期
		int total=seatsList.size();
		Map<String,Object> m=null;
		AttendanceBo attendanceBo=null;
		//if(!StringUtil.isNullOrEmpty(courseName)) {
			for (int i = 0; i < total; i++) {
				m = new HashMap<String, Object>();
				SeatBo seatBo = seatsList.get(i);
				String name = seatBo.getName();//学生姓名
				String gradeName = seatBo.getClassRoom();
				m.put("studentName", name);
				m.put("gradeName", gradeName);
				/*m.put("courseName", courseName);*/
				m.put("dayTime", day);
				//if(!StringUtil.isNullOrEmpty(courseName)){
				attendanceBo = attendanceService.findByMap(m);
				if(!StringUtil.isNullOrEmpty(attendanceBo)){
					seatsList.get(i).setKaoqin_flg(attendanceBo.getType());
					System.out.println("考勤标志==================="+attendanceBo.getType());

				}
				//}

			}
		//}
		return seatsList;
	}

	@Resource(name="SeatService")
	private ISeatService SeatService ;
	@Resource(name="CourseService")
	private ICourseService courseService ;
	@Resource(name="AttendanceService")
	private IAttendanceService attendanceService ;

}
