package com.zero2ipo.pay.web;

import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.core.MobilePageContants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhengyufnei on 2015/8/31.
 * 钱包
 */
@Controller
public class MoneyAction {
	/**
	 * 我的钱包页面
	 * @author zhengyunfei
	 * */
	@RequestMapping(value = "/order/mymoney.html")
	public ModelAndView mymoney(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		ModelAndView mv=new ModelAndView(MobilePageContants.MY_MONEY_PAGE);
		FmUtils.FmData(request, model);
		return mv;
	}
	/**
	 * 我的优惠券
	 * @author zhengyunfei
	 * */
	@RequestMapping(value = "/order/mycoupon.html")
	public ModelAndView mycoupon(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		ModelAndView mv=new ModelAndView(MobilePageContants.MY_COUPON_PAGE);
		FmUtils.FmData(request, model);
		return mv;
	}

	/**
	 * 我的红包页面
	 * @author zhengyunfei
	 * */
	@RequestMapping(value = "/order/myhongbao.html")
	public ModelAndView myhongbao(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		ModelAndView mv=new ModelAndView(MobilePageContants.MY_HONGBAO_PAGE);
		FmUtils.FmData(request, model);
		return mv;
	}
	/**
	 * 充值
	 * @author zhengyunfei
	 * */
	@RequestMapping(value = "/order/chongzhi.html")
	public ModelAndView chongzhi(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		ModelAndView mv=new ModelAndView(MobilePageContants.MY_CHOGNZHI_PAGE);
		FmUtils.FmData(request, model);
		return mv;
	}

}

