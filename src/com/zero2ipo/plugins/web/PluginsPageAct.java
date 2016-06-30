package com.zero2ipo.plugins.web;

import com.zero2ipo.car.choujiang.bizc.IServiceChouJiangResult;
import com.zero2ipo.car.choujiang.bo.ChouJiangResult;
import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.plugins.contants.PluginsContants;
import com.zero2ipo.weixin.services.message.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 营销活动插件功能开发
 * 大转盘
 * @author zhengyunfei
 *
**/

@Controller
public class PluginsPageAct {

	/**
	 * 跟路径控制
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hd/dazhuanpan.html", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
							  HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		try {
			mv.setViewName(PluginsContants.DA_ZHUAN_PAN_PAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value = "/hd/isChouJiang.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> isChouJiang(HttpServletRequest request,
							  HttpServletResponse response, ModelMap model) {
		String openId= SessionHelper.getStringAttribute(request, MobileContants.USER_OPEN_ID_KEY);
		Map<String,Object> result=new HashMap<String, Object>();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			if(StringUtil.isNullOrEmpty(openId)){
				openId=MobileContants.DEFAULT_OPEN_ID;
			}
			map.put("openId",openId);
			map.put("flag",MobileContants.status_0);
			int count=choujiang.findAllListCount(map);
			if(count>0) {
				result.put("error", true);
			}else{
				//保存抽奖记录
				ChouJiangResult bo=new ChouJiangResult();
				bo.setOpenId(openId);
				choujiang.add(bo);
				//bo.setFlag();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value = "/hd/dazhuanpan/get.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getJiangPin(HttpServletRequest request,
							  HttpServletResponse response, ModelMap model,String name,String mobile,String carNum,String content) {
		Map<String,Object> result=new HashMap<String, Object>();
		//获取openid mobile name carnum
		//首先根据openid查询此用户是否已经抽过一次奖
		Map<String,Object> map=new HashMap<String,Object>();
		String openId= SessionHelper.getStringAttribute(request, MobileContants.USER_OPEN_ID_KEY);
		if(StringUtil.isNullOrEmpty(openId)){
			openId=MobileContants.DEFAULT_OPEN_ID;
		}
		map.put("openId",openId);
		if(!StringUtil.isNullOrEmpty(mobile)){
			map.put("mobile",mobile);
		}
		if(!StringUtil.isNullOrEmpty(carNum)){
			map.put("carNum",carNum);
		}

		try {
			int count=choujiang.findAllListCount(map);
			if(count>0){
				result.put("error",true);//已经抽过奖了
			}else{
				//保存信息到数据库中
				ChouJiangResult bo=new ChouJiangResult();
				bo.setOpenId(openId);
				bo.setName(name);
				bo.setCarNum(carNum);
				bo.setMobile(mobile);
				bo.setContent(content);
				String backInfo=choujiang.add(bo);
				result.put("success",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Autowired
	protected ICoreService coreService;
	@Autowired
	protected IServiceChouJiangResult choujiang;

}
