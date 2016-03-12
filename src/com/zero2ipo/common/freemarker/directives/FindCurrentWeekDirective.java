package com.zero2ipo.common.freemarker.directives;

import com.zero2ipo.common.entity.eeh.WeekDate;
import com.zero2ipo.common.util.WeekDateUtils;
import com.zero2ipo.framework.util.StringUtil;
import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * 获取本周日期
 * @author zhengYunfei
 */
public class FindCurrentWeekDirective implements TemplateDirectiveModel{
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			//获取本周日期
			List<WeekDate> weekDates= WeekDateUtils.getCurrentWeekDate();
			if(!StringUtil.isNullOrEmpty(weekDates)&&weekDates.size()>0){
				env.setVariable("weekDates", ObjectWrapper.DEFAULT_WRAPPER.wrap(weekDates));
			}
			body.render(env.getOut());
	}

}
