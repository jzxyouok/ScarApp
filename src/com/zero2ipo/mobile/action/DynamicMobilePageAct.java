package com.zero2ipo.mobile.action;

import com.zero2ipo.common.entity.Car;
import com.zero2ipo.common.entity.app.Users;
import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.core.MobilePageContants;
import com.zero2ipo.core.WaterPageContants;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.io.FileHelper;
import com.zero2ipo.mobile.services.bsb.IHistoryCarService;
import com.zero2ipo.mobile.services.bsb.IWashCouponService;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.mobile.web.URLHelper;
import com.zero2ipo.weixin.services.message.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 手机端主页调整控制
 * @author zhengyunfei
 * @date 2015-04-22
 *
 */

@Controller
public class DynamicMobilePageAct {

	/**
	 * 首页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/water/index.html", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
							  HttpServletResponse response, ModelMap model,String couponId) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(WaterPageContants.INDEX_PAGE);
		return mv;
	}


	/**
	 * 商品列表页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/water/goodlist.html", method = RequestMethod.GET)
	public ModelAndView wycj(HttpServletRequest request,
							 HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(WaterPageContants.GOODS_LIST_PAGE);
		return mv;
	}
	/**
	 * 我的洗车券
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mycoupon.html", method = RequestMethod.POST)
	public ModelAndView wycjForPost(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,Car car) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//获取当前登录的用户id
		Users user=(Users) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){
			mv.setViewName(MobilePageContants.FM_PAGE_WDXCQ);
			mv.addObject("user", user);
			mv.addObject("mobile",user.getPhoneNum());
			if(!StringUtil.isNullOrEmpty(car)){
				SessionHelper.setAttribute(request, MobileContants.CAR_SESSION_KEY, car);
			}
		}else{
			mv.setViewName(MobilePageContants.FM_USER_LOGIN);
		}

		return mv;
	}
	/**
	 * 购买洗车券
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pay/buycouponPage.html", method = RequestMethod.GET)
	public ModelAndView buycoupon(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//获取当前登录的用户id
		Users user=(Users) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){
			mv.addObject("mobile",user.getPhoneNum());
			String carNo="";
			Car car=(Car) SessionHelper.getAttribute(request, MobileContants.CAR_SESSION_KEY);
			if(!StringUtil.isNullOrEmpty(car)){
				carNo=car.getCarNo();
				if(StringUtil.isNullOrEmpty(carNo)){
					carNo=user.getAccount();
				}
			}else{
				carNo=user.getAccount();
			}
			mv.addObject("carNo",carNo);//车牌号

			mv.setViewName(MobilePageContants.FM_PAGE_GMXCQ);
		}else{
			mv.setViewName(MobilePageContants.FM_USER_LOGIN);
		}

		return mv;
	}
	/**
	 * 我的洗车订单
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myorderlist.html", method = RequestMethod.GET)
	public ModelAndView bmsuccess(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		//获取当前登录的用户id
		Users user=(Users) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user)){
			mv.addObject("mobile",user.getPhoneNum());
			mv.addObject("user",user);
			mv.setViewName(MobilePageContants.FM_LZH);
		}else{
			mv.setViewName(MobilePageContants.FM_USER_LOGIN);
		}
		return mv;
	}
	/**
	 * 洗车工登陆
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminLogin.html", method = RequestMethod.GET)
	public ModelAndView adminLogin(HttpServletRequest request,
								   HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MobilePageContants.ADMIN_LOGIN_PAGE);
		return mv;
	}
	/**
	 * 积分签到
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jfqd.html", method = RequestMethod.GET)
	public ModelAndView qiandao(HttpServletRequest request,
								   HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MobilePageContants.QIANDAO_PAGE);
		return mv;
	}
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resetPassword.html", method = RequestMethod.GET)
	public ModelAndView resetPassword(HttpServletRequest request,
									  HttpServletResponse response, ModelMap model) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MobilePageContants.RESET_PASSWORD_PAGE);
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
