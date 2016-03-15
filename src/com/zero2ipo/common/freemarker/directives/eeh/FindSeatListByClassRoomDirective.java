package com.zero2ipo.common.freemarker.directives.eeh;

import com.zero2ipo.common.freemarker.DirectiveUtils;
import com.zero2ipo.eeh.seat.bizc.ISeatService;
import com.zero2ipo.eeh.seat.bo.SeatBo;
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
			List<SeatBo> seatsList=SeatService.findAllList(queryMap);
			if(null!=seatsList&&seatsList.size()>0){
				env.setVariable("firstSeat", ObjectWrapper.DEFAULT_WRAPPER.wrap(seatsList.get(0)));
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
