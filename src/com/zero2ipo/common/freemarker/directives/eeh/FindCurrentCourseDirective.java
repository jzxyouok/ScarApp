package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.CourseBo;
import com.zero2ipo.framework.util.StringUtil;
import freemarker.core.Environment;
import freemarker.template.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 *
 * 查询当前课程
 * @author zhengYunfei
 *
 */
public class FindCurrentCourseDirective implements TemplateDirectiveModel{
	private static  final String  PARAM_CLASS_ROOM="classRoom";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String currentCourseName="";
		try {
			String classRoom= DirectiveUtils.getString(PARAM_CLASS_ROOM, params);
			//首先根据当前教室，去查询当前培优课程
			CourseBo current=courseService.getCurrentCourse(classRoom);
			if(StringUtil.isNullOrEmpty(current)){//如果不存在培优课程，再查询日常课程
				current=courseService.getCurrentRiChangCourse(classRoom);
			}
			if(StringUtil.isNullOrEmpty(current)){
				currentCourseName="-999999";//假设一个不存在的课程
			}else{
				currentCourseName=current.getCourseName();//当前课程

			}
			env.setVariable("currentCourseName", ObjectWrapper.DEFAULT_WRAPPER.wrap(currentCourseName));
			env.setVariable("currentCourse", ObjectWrapper.DEFAULT_WRAPPER.wrap(current));
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}

	@Resource(name="CourseService")
	private ICourseService courseService ;
}
