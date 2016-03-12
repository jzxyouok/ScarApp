package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.common.util.LocalMAC;
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
 * 根据所在班级和类型查询信息列表
 * @author zhengYunfei
 *
 */
public class FindArticleListByClassRommAndTypeDirective implements TemplateDirectiveModel{
	private static  final String  PARAM_TYPE="type";
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<ClassRoomBo> list=null;
		try {
			String type= DirectiveUtils.getString(PARAM_TYPE, params);
			Map<String,Object> queryMap=new HashMap<String, Object>();
			if(!StringUtil.isNullOrEmpty(type)){
				queryMap.put("type",ArticleContants.articleMap.get(type));
			}
			//如果是通知或者公告，则不用根据所在班级查询
			if(!ArticleContants.ARTICLE_TYPE_01.equals(type)||!ArticleContants.ARTICLE_TYPE_02.equals(type)){
				//获取本机mac地址
				String mac= LocalMAC.getLocalMac();
				//根据mac地址查询所在班级
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("ip", mac);
				list= classRoomService.findAllList(map);
				ClassRoomBo classRoomBo=new ClassRoomBo();
				if(null!=list&&list.size()>0){
					classRoomBo=list.get(0);
				}
				queryMap.put("gradeName",classRoomBo);
			}
			List<ArticleBo> articleList=ArticleService.findAllList(queryMap);
		    env.setVariable("articleList", ObjectWrapper.DEFAULT_WRAPPER.wrap(articleList));
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
