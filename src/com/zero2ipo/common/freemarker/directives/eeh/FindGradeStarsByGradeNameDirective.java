package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.eeh.article.bizc.IArticleService;
import com.zero2ipo.eeh.article.bo.ArticleBo;
import com.zero2ipo.eeh.article.bo.ArticleContants;
import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.classroom.bo.ClassRoomBo;
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
 * 查询某班级的所有班级明星
 * @author zhengYunfei
 *
 */
public class FindGradeStarsByGradeNameDirective implements TemplateDirectiveModel{
	private static  final String  PARAM_TYPE="type";//类型1通知2公告3班级风采4班级明星
	private static  final String  PARAM_GRADE_NAME="gradeName";//所在班级，默认1501

	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<ClassRoomBo> list=null;
		try {
			String type= DirectiveUtils.getString(PARAM_TYPE, params);
			String gradeName= DirectiveUtils.getString(PARAM_GRADE_NAME, params);
			Map<String,Object> queryMap=new HashMap<String, Object>();
			if(!StringUtil.isNullOrEmpty(type)){
				queryMap.put("type",ArticleContants.articleMap.get(type));
			}
			//如果是通知或者公告，则不用根据所在班级查询
			if(!ArticleContants.ARTICLE_TYPE_01.equals(type)&&!ArticleContants.ARTICLE_TYPE_02.equals(type)){
				queryMap.put("gradeName",gradeName);
			}
			List<ArticleBo> articleList=ArticleService.findAllList(queryMap);
			env.setVariable("gradeStars", ObjectWrapper.DEFAULT_WRAPPER.wrap(articleList));
			if(articleList.size()>0){
				env.setVariable("mainGradeStart", ObjectWrapper.DEFAULT_WRAPPER.wrap(articleList.get(0)));
			}
			env.setVariable("recordCount", ObjectWrapper.DEFAULT_WRAPPER.wrap(articleList.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	@Resource(name="ArticleService")
	private IArticleService ArticleService ;
	@Resource(name="classRoomService")
	private IClassRoomService classRoomService ;


}
