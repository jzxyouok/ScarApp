package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.common.util.WeekDateUtils;
import com.zero2ipo.eeh.course.bo.CourseConstants;
import com.zero2ipo.eeh.seat.bizc.ISeatService;
import com.zero2ipo.eeh.seat.bo.SeatBo;
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
		List<SeatBo> list=null;
		try {
			String classRoom= DirectiveUtils.getString(PARAM_GRADE_NAME, params);
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("classRoom",classRoom);
			//除了根据教室查询座次表，还要根据星期和上课时间
			//获取今天是周几
			String week= WeekDateUtils.getWeekOfDate(null);
			//获取当前系统时间 时分
			String time= WeekDateUtils.getDateNowHm();
			System.out.println("week========================"+week);
			System.out.println("time========================"+time);
			queryMap.put("week", week);
			queryMap.put("schoolTime",time);
			queryMap.put("seatType", CourseConstants.SEAT_TYPE_1);
			List<SeatBo> seatsList=SeatService.findAllList(queryMap);
			if(null!=seatsList&&seatsList.size()>0){
				env.setVariable("firstSeat", ObjectWrapper.DEFAULT_WRAPPER.wrap(seatsList.get(0)));
				env.setVariable("seatType", ObjectWrapper.DEFAULT_WRAPPER.wrap(CourseConstants.SEAT_TYPE_1_name));//培优座次
			}else{//没有培优座位表，那显示班级座位表
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("classRoom",classRoom);
				map.put("seatType", CourseConstants.SEAT_TYPE_0);//日常座位表
				//保存座位表类型
				env.setVariable("seatType", ObjectWrapper.DEFAULT_WRAPPER.wrap(CourseConstants.SEAT_TYPE_0_NAME));//日常座次
				seatsList=SeatService.findAllList(map);
				if(!StringUtil.isNullOrEmpty(seatsList)&&seatsList.size()>0){
					env.setVariable("firstSeat", ObjectWrapper.DEFAULT_WRAPPER.wrap(seatsList.get(0)));
				}

			}
			env.setVariable("seatsList", ObjectWrapper.DEFAULT_WRAPPER.wrap(seatsList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}

	@Resource(name="SeatService")
	private ISeatService SeatService ;


}
