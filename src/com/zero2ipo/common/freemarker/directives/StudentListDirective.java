package com.zero2ipo.common.freemarker.directives;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zero2ipo.common.entity.Student;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.services.student.IStudentService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * 学生列表 
 * @author zhengYunfei
 *
 */
public class StudentListDirective implements TemplateDirectiveModel{
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Student> list=null;
		try {
			Map<String,Object> queryMap=new HashMap<String,Object>();
			list=studentService.findAllList(queryMap);
			if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
				env.setVariable("studentsList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	/*
	 * 收藏服务层接口注入
	 */
	@Resource(name = "studentService")
	private IStudentService studentService;
}
