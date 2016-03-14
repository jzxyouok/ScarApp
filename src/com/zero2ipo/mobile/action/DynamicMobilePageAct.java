package com.zero2ipo.mobile.action;

import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.common.util.LocalMAC;
import com.zero2ipo.core.WaterPageContants;
import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.classroom.bo.ClassRoomBo;
import com.zero2ipo.eeh.common.CommonConstant;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.io.FileHelper;
import com.zero2ipo.mobile.services.bsb.IHistoryCarService;
import com.zero2ipo.mobile.services.bsb.IWashCouponService;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.mobile.web.URLHelper;
import com.zero2ipo.weixin.services.message.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 手机端主页调整控制
 * @author zhengyunfei
 * @date 2015-04-22
 *
 */

@Controller
public class DynamicMobilePageAct {
	@Autowired
	@Qualifier("classRoomService")
	private IClassRoomService classRoomService ;
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
							  HttpServletResponse response, ModelMap model) throws SocketException, UnknownHostException {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(WaterPageContants.INDEX_PAGE);
		mv.addObject("type", CommonConstant.NAV_TYPE_KEBIAO);
		String gradeName=getCurrentGradeByMacAddress();
		SessionHelper.setAttribute(request,CommonConstant.DEFAULT_GRADE_NAME_kEY,gradeName);
		return mv;
	}

	/**
	 * 根据当前机器mac地址查询所在班级
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public String getCurrentGradeByMacAddress() throws SocketException, UnknownHostException {
		//获取本机mac地址
		String mac= LocalMAC.getLocalMac();
		//根据mac地址查询所在班级
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("ip", mac);
		List<ClassRoomBo> list=new ArrayList<ClassRoomBo>();
		list= classRoomService.findAllList(map);
		ClassRoomBo classRoomBo=new ClassRoomBo();
		String gradeName=CommonConstant.DEFAULT_GRADE_NAME;//默认班级名称
		if(null!=list&&list.size()>0){
			classRoomBo=list.get(0);
			gradeName=classRoomBo.getName();
		}
		return gradeName;
	}


	/**
	 * 班级风采
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bjfc.html", method = RequestMethod.GET)
	public ModelAndView wycj(HttpServletRequest request,
							 HttpServletResponse response, ModelMap model,String pageNo) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(WaterPageContants.BanJiFengCaiPage);
		mv.addObject("type", CommonConstant.NAV_TYPE_BanJiFengCai);
		if(StringUtil.isNullOrEmpty(pageNo)){
			pageNo="1";
		}
		model.put("pageNo", Integer.valueOf(pageNo));
		model.put("pageSize", CommonConstant.PAGESIZE);
		model.put("recordCount",20);
		return mv;
	}



	/**
	 *通知
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tongzhi.html", method = RequestMethod.GET)
	public ModelAndView tongzhi(HttpServletRequest request,
							 HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(WaterPageContants.TongZhiPage);
		mv.addObject("type", CommonConstant.NAV_TYPE_TongZhi);

		return mv;
	}



	/**
	 * 公告
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gonggao.html", method = RequestMethod.GET)
	public ModelAndView gonggao(HttpServletRequest request,
							 HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(WaterPageContants.GongGaoPage);
		mv.addObject("type", CommonConstant.NAV_TYPE_GongGao);
		return mv;
	}
	/**
	 * 考试查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/kaoshi.html", method = RequestMethod.GET)
	public ModelAndView kaoshiPage(HttpServletRequest request,
								HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(WaterPageContants.KaoShiPage);
		mv.addObject("type", CommonConstant.NAV_TYPE_KaoShiChaXun);
		return mv;
	}
	/**
	 * 单页资源请求处理
	 */
	@RequestMapping(value = "/url/**/*.*", method = RequestMethod.GET)
	public String urlReq(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String filePath = URLHelper.getFilePath(request);
		String sPath = "";

		if(FileHelper.isHaveFile(filePath))
		{
			sPath =  FmUtils.fmHtmlPage(request, model, URLHelper.getStatePath(request));
		}
		else
		{
			sPath = FmUtils.fmNotFountPage(request, response, model);
		}
		return sPath;
	}
	@Autowired
	protected IHistoryCarService historyCarService ;
	@Autowired
	protected IWashCouponService washCouponService;
	@Autowired
	protected ICoreService coreService;

}
