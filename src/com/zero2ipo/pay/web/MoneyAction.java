package com.zero2ipo.pay.web;

import com.zero2ipo.car.chongzhi.bizc.IChongZhi;
import com.zero2ipo.car.userchongzhi.bizc.IUserChongZhi;
import com.zero2ipo.car.userchongzhi.bo.UserChongZhiBo;
import com.zero2ipo.common.entity.app.Users;
import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.core.MobilePageContants;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.pay.model.MdlPay;
import com.zero2ipo.pay.service.WXPay;
import com.zero2ipo.pay.service.WXPrepay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhengyufnei on 2015/8/31.
 * 钱包
 */
@Controller
public class MoneyAction {
	public final static String notifyUrl="/order/yuepayupd.html";
	@Resource(name = "userchongzhi")
	private IUserChongZhi userchongzhi ;
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
	/**
	 * 充值
	 * @author zhengyunfei
	 * */
	@RequestMapping(value = "/order/savechongzhi.html")
	public ModelAndView saveChongZhi(HttpServletRequest request, HttpServletResponse response, ModelMap model,UserChongZhiBo bo)
	{
		ModelAndView mv=new ModelAndView(MobilePageContants.MY_HONGBAO_PAGE);
		FmUtils.FmData(request, model);
		//首先获取当前登陆的会员信息
		Users u= (Users) SessionHelper.getAttribute(request,MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(u)){
			String userId=u.getUserId();
			bo.setUserId(userId);
		}
		userchongzhi.add(bo);
		return mv;
	}
	/**
	 * 获取微信支付jsParam
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/order/getPayParam.html", method = RequestMethod.POST)
	@ResponseBody
	public String getJsParam(HttpServletRequest request, HttpServletResponse response,float toatl) {
			//根据orderId查询订单
			String jsParam="";
			jsParam=getWXJsParamForNative(request,toatl);
			return jsParam;
	}
	//获取微信支付参数信息
	public String getWXJsParamForNative(HttpServletRequest request, float total_free) {
		Map<String, Object> result = new HashMap<String, Object>();
		MdlPay pay = new MdlPay();
		WXPrepay prePay = getWxPrepay(request);
		float b = (float) (Math.round(total_free * 100)) / 100;
		prePay.setTotal_fee((int) (b * 100) + "");
		prePay.setTrade_type("JSAPI");
		String jsParam = "";
		//此处添加获取openid的方法，获取预支付订单需要此参数！！！！！！！！！！！
		// 获取预支付订单号
		String prepayid = prePay.submitXmlGetPrepayId();
		if (!"".equals(prepayid) && prepayid != null && prepayid.length() > 10) {
			// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
			jsParam = WXPay.createPackageValue(pay.getAppId(), pay.getPartnerKey(), prepayid);
		}
		return jsParam;
	}
	/**
	 * 动态获取wxPrepay
	 */
	private WXPrepay getWxPrepay(HttpServletRequest request) {
		ServletContext application =request.getSession().getServletContext();
		String partnerId =application.getAttribute(MobileContants.PARTNERID_KEY)+"";
		String appid =application.getAttribute(MobileContants.APPID_KEY)+"";
		String partnerValue = application.getAttribute(MobileContants.PARTNERVALUE_KEY)+"";
		String spbill_create_ip = request.getRemoteAddr();
		WXPrepay prePay = new WXPrepay();
		String prePayBody = application.getAttribute(MobileContants.PREPAYBODY_KEY)+"";
		String domain=application.getAttribute(MobileContants.DOMAIN_KEY)+"";
		prePay.setAppid(appid);
		prePay.setBody(prePayBody);
		prePay.setPartnerKey(partnerValue);
		prePay.setMch_id(partnerId);
		prePay.setNotify_url(domain + notifyUrl);
		String outTradeNo= UUID.randomUUID().toString().replace("-","");
		prePay.setOut_trade_no(outTradeNo);//每次重新生成交易单号，防止订单重复，但是需要把订单里面的outTradeNo也修改了
		prePay.setSpbill_create_ip(spbill_create_ip);
		String openid = "";
		//首先从当前登录的账号信息中获取openid
		Object o=  SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(o instanceof Users){
			Users u= (Users) o;
			if(!StringUtil.isNullOrEmpty(u)){
				openid=u.getOpenId();//如果数据库中不存在openid，再从缓存中读取
				if(StringUtil.isNullOrEmpty(openid)){
					openid=SessionHelper.getStringAttribute(request, MobileContants.USER_OPEN_ID_KEY);//从缓存中获取，这里有时会获取不到，所有要从数据库中读取
				}
			}
		}
		prePay.setOpenid(openid);
		return prePay;
	}
	/**
	 * 获取微信支付参数
	 * appId 微信appid
	 * partnerId 微信支付商户号
	 * partnerkey 微信支付商户秘钥
	 *
	 * @return
	 */
	public MdlPay getMdlPay(HttpServletRequest request) {
		MdlPay pay = new MdlPay();
		ServletContext application =request.getSession().getServletContext();
		String partnerId =application.getAttribute(MobileContants.PARTNERID_KEY)+"";
		String appid =application.getAttribute(MobileContants.APPID_KEY)+"";
		String partnerValue = application.getAttribute(MobileContants.PARTNERVALUE_KEY)+"";
		pay.setAppId(appid);
		pay.setPartnerId(partnerId);
		pay.setPartnerKey(partnerValue);
		return pay;
	}
}

