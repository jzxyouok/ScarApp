package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.util.LocalMAC;
import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.classroom.bo.ClassRoomBo;
import freemarker.core.Environment;
import freemarker.template.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 根据当前机器的mac查询所在教室标签
 * @author zhengYunfei
 *
 */
public class FindCurrentClassRoomDirective implements TemplateDirectiveModel{
	public void execute(Environment env, Map params, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		List<ClassRoomBo> list=null;
		try {
			//获取本机mac地址
			String mac= LocalMAC.getLocalMac();
			//根据mac地址查询所在班级
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("ip",mac);
			list= classRoomService.findAllList(queryMap);
			ClassRoomBo classRoomBo=new ClassRoomBo();
			if(null!=list&&list.size()>0){
				classRoomBo=list.get(0);
			}
			env.setVariable("classRoom", ObjectWrapper.DEFAULT_WRAPPER.wrap(classRoomBo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
	@Resource(name="classRoomService")
	private IClassRoomService classRoomService ;

}
