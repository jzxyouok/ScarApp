package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
import freemarker.core.Environment;
import freemarker.template.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 根据当前机器的mac查询所在班级
 * @author zhengYunfei
 *
 */
public class FindCurrentGradeDirective implements TemplateDirectiveModel{
	private static  final String  PARAM_GRADE_NAME="gradeName";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		List<GradeBo> list=null;
		GradeBo bo=null;
		try {
			String gradeName= DirectiveUtils.getString(PARAM_GRADE_NAME, params);
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("name",gradeName);
			list=GradeService.findAllList(queryMap);
			if(null!=list&&list.size()>0){
				bo=list.get(0);
			}
			env.setVariable("gradeBo", ObjectWrapper.DEFAULT_WRAPPER.wrap(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	@Resource(name="GradeService")
	private IGradeService GradeService ;

}
