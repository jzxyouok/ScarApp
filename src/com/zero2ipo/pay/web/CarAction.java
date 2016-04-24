package com.zero2ipo.pay.web;

import com.zero2ipo.common.entity.*;
import com.zero2ipo.common.entity.app.Users;
import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.core.MobilePageContants;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.services.bsb.*;
import com.zero2ipo.mobile.services.user.IUserServices;
import com.zero2ipo.mobile.utils.DateUtil;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.weixin.services.message.ICoreService;
import com.zero2ipo.weixin.templateMessage.TemplateMessageUtils;
import com.zero2ipo.weixin.templateMessage.WxTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/31.
 */
@Controller
public class CarAction {
	/**
	 * 订单流程页面
	 * */
	@RequestMapping(value = "/f/order{id}.html")
	public ModelAndView selectServicePage(HttpServletRequest request, HttpServletResponse response, ModelMap model,@PathVariable("id") String id,String flag)
	{
		ModelAndView mv=new ModelAndView(MobilePageContants.CAR_DETAIL_PAGE);
		FmUtils.FmData(request, model);
		mv.addObject("orderId", id);
		mv.addObject("flag",flag);
		return mv;
	}
	/**
	 * 洗车工修改任务界面
	 * */
	@RequestMapping(value = "/renwu/order{id}.html")
	public ModelAndView adminOderDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model,@PathVariable("id") String id,RedirectAttributes  attr)
	{
		ModelAndView mv=new ModelAndView(MobilePageContants.ADMIN_ORDER_DETAIL_PAGE);
		FmUtils.FmData(request, model);
		mv.addObject("orderId", id);
		return mv;
	}
	/**
	 * 洗车工修改任务界面
	 * */
	@RequestMapping(value = "/renwu/order{id}/f{flag}.html")
	public ModelAndView updateSendOrderSuccessPage(HttpServletRequest request, HttpServletResponse response, ModelMap model,@PathVariable("id") String id,@PathVariable("flag") String flag,RedirectAttributes  attr)
	{
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView(MobilePageContants.ADMIN_ORDER_DETAIL_PAGE);
		//如果没有登录的话，则跳转到洗车工登录页面
		AdminBo adminBo= (AdminBo) SessionHelper.getAttribute(request,MobileContants.ADMIN_SESSION_KEY);
		if(StringUtil.isNullOrEmpty(adminBo)){
			mv.setViewName(MobilePageContants.ADMIN_LOGIN_PAGE);
		}
		mv.addObject("orderId", id);
		mv.addObject("flag", flag);
		return mv;
	}
	/**
	 * 我的订单
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/my/order{id}.html")
	public ModelAndView myOrderPage(HttpServletRequest request, HttpServletResponse response, ModelMap model,@PathVariable("id") String id)
	{
		FmUtils.FmData(request, model);
		//获取当前登录的用户id
		Users user=(Users) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		ModelAndView mv=new ModelAndView();
		if(!StringUtil.isNullOrEmpty(user)){
			mv.setViewName(MobilePageContants.MY_ORDER_PAGE);
		}else{
			mv.setViewName(MobilePageContants.FM_USER_LOGIN);
		}
		mv.addObject("orderId",id);
		return mv;
	}
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	public ModelAndView carList(HttpServletRequest request,
								HttpServletResponse response, ModelMap model, @PathVariable String templateCode) {
		FmUtils.FmData(request, model);
		List<Car> list=null;
		ModelAndView mv=new ModelAndView();
		mv.setViewName(MobilePageContants.FM_PAGE_MAIN);
		try {
			Map<String,Object> queryMap=new HashMap<String,Object>();
			String openId= SessionHelper.getStringAttribute(request, MobileContants.USER_OPEN_ID_KEY);
			if(!StringUtil.isNullOrEmpty(openId)){
				queryMap.put("openId",openId);
				list=historyCarService.findAllList(queryMap);
			}else{
				list=new ArrayList<Car>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("carList",list);
		return mv;
	}

	@RequestMapping(value = "/car/wait_washer.ht", method = RequestMethod.GET)
	public String add(HttpServletRequest request,
					  HttpServletResponse response, ModelMap model,@PathVariable Car car){

		Car article=null;
		int primkey=-1;
		FmUtils.FmData(request, model);
		if (!StringUtil.isNullOrEmpty(car)){
			String openid=SessionHelper.getStringAttribute(request, MobileContants.USER_OPEN_ID_KEY);
			car.setUserCarId(openid);
			primkey= historyCarService.add(car);
		}
		//获取当前登陆的用户

		return MobilePageContants.CAR_DETAIL_PAGE;

	}
	@RequestMapping(value = "/car/wait_washer.html", method = RequestMethod.POST)
	public String addPost(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute Car car,RedirectAttributes redirectAttributes){

		boolean flag=false;
		FmUtils.FmData(request, model);
		if (!StringUtil.isNullOrEmpty(car)){
			String openid=SessionHelper.getStringAttribute(request, MobileContants.USER_OPEN_ID_KEY);
			car.setUserCarId(openid);
			//首页判断此车辆是否存在
			Map<String,Object> queryMap=new HashMap<String,Object>();
			queryMap.put("carNo", car.getCarNo());

			queryMap.put("carSeats", car.getCarSeats());
			queryMap.put("carColor", car.getCarColor());
			Car isExsit=historyCarService.findById(queryMap);
			int cid;//c车辆id
			String openId=SessionHelper.getStringAttribute(request, MobileContants.USER_OPEN_ID_KEY);
			if(StringUtil.isNullOrEmpty(openId)){
				openId=MobileContants.DEFAULT_OPEN_ID;
			}
			car.setUserCarId(openId);
			if(StringUtil.isNullOrEmpty(isExsit)){
				car.setCarColor(car.getCarColor().replace(",",""));
				car.setCarSeats(car.getCarSeats().replace(",", ""));
				int id= historyCarService.add(car);//新增
				cid=id;
				redirectAttributes.addFlashAttribute("id", id);
				if(id>0){
					flag=true;
				}
			}else{//更新
				cid=isExsit.getId();

        		/*queryMap.put("userCardId", openId);
        		isExsit.setWashAddr(car.getWashAddr());
        		isExsit.setWashInfo(car.getWashInfo());
        		isExsit.setMobile(car.getMobile());
        		isExsit.setCarColor(car.getCarColor().replace(",",""));
        		isExsit.setCarSeats(car.getCarSeats().replace(",", ""));
        		isExsit.setPreTime(car.getPreTime());
        		flag=historyCarService.update(isExsit);
        		redirectAttributes.addFlashAttribute("id", isExsit.getId());*/
			}
			//更新车辆地址
			Address address=new Address();
			address.setCid(cid);
			address.setMobile(car.getMobile());
			address.setWashAddr(car.getWashAddr());
			address.setOpenId(openId);
			int addressId=addressService.add(address);
			if(addressId>0){
				flag=true;
			}
			redirectAttributes.addFlashAttribute("carId", cid);
			redirectAttributes.addFlashAttribute("addressId", addressId);
		}
		return "redirect:/car/selectService.html";

	}

	/**
	 * 首页下单
	 * @param request
	 * @param response
	 * @param model
	 * @param car
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/car/xd.html", method = RequestMethod.POST)
	public String xd(HttpServletRequest request, HttpServletResponse response, ModelMap model, Car car,String couponId,String lat,String lng,RedirectAttributes redirectAttributes){

		boolean flag=false;
		FmUtils.FmData(request, model);
		int orderId=-1;
		int carId=-1;
		Users user=(Users) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user))
		{

			if (!StringUtil.isNullOrEmpty(car)){
				car.setUserCarId(user.getUserId());
				//首页判断此车辆是否存在
				Map<String,Object> queryMap=new HashMap<String,Object>();
				queryMap.put("mobile",user.getPhoneNum());
				Car isExsit=null;
				List<Car> historyCar=historyCarService.findAllList(queryMap);
				if(historyCar.size()>0){
					isExsit=historyCar.get(0);
				}
				if(StringUtil.isNullOrEmpty(isExsit)){
					car.setUserCarId(user.getUserId());
					carId= historyCarService.add(car);//新增
					redirectAttributes.addFlashAttribute("id", carId);
					car.setId(carId);
					redirectAttributes.addFlashAttribute("bo", car);
					isExsit=car;
					if(carId>0){
						flag=true;
					}
				}else{//更新
					queryMap.put("userCardId", user.getUserId());
					isExsit.setWashAddr(car.getWashAddr());
					isExsit.setName(car.getName());
					isExsit.setWashInfo(car.getWashInfo());
					isExsit.setMobile(car.getMobile());
					isExsit.setCarColor(car.getCarColor());
					isExsit.setCarSeats(car.getCarSeats());
					isExsit.setCarType(car.getCarType());
					isExsit.setCarNo(car.getCarNo());
					isExsit.setUserCarId(user.getUserId());
					isExsit.setPreTime(car.getPreTime());
					flag=historyCarService.update(isExsit);
					redirectAttributes.addFlashAttribute("id", isExsit.getId());
					redirectAttributes.addFlashAttribute("bo", isExsit);
				}
				/**移除录入的车辆信息保存的session**/
				SessionHelper.removeAttribute(request, MobileContants.CAR_SESSION_KEY);
             	/*生成订单*/
				//生成订单
				Order order=new Order();
				order.setCarNum(isExsit.getCarNo());
				String orderNo=DateUtil.getDateOrderNo();
				order.setOrderId(orderNo);
				String orderTime=DateUtil.getCurrentDateStr();
				order.setCreateTime(orderTime);
				order.setOrderStatus(MobileContants.ORDER_PAY_STATUS_DEFAULT);
				order.setWashTime(isExsit.getPreTime());
				order.setCarNum(isExsit.getCarNo());
				order.setCarType(isExsit.getCarType());
				order.setCarColor(isExsit.getCarColor());
				order.setLat(lat);
				order.setLon(lng);
				order.setCardsId(couponId);
				order.setAddress(isExsit.getWashAddr());
				order.setMobile(user.getPhoneNum());
				order.setDiscription(isExsit.getWashInfo());
				order.setCarId(carId+"");
				//设置洗车券id
				order.setCardsId(couponId);
				//根据 洗车券id查询 洗车券 价格
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("id", couponId);
				GgwashCoupon coupon=washCouponService.findById(m);
				if(!StringUtil.isNullOrEmpty(coupon)){
					order.setPrice(coupon.getPrice());
					order.setWashType(coupon.getName());
				}
				//order.setStatus("-1");
				order.setSendOrderStatus("0"); //默认派单状态
				order.setUserId(user.getUserId());
				//order.setJsParam("");
				orderId=orderService.add(order);
				//如果订单 保存 成功的话 ，减去 用户已经使用 的洗车券
				System.out.println("支付成功后orderid="+orderId);
				if(orderId>1){
					m.put("userId", user.getUserId());
					System.out.println("开始删除洗车券====================");
					//根据couponId查询UserCoupon
					UserCoupon userCoupon=washCouponService.findUserCouponById(m);
					if(!StringUtil.isNullOrEmpty(userCoupon)&&userCoupon.getNumber()>0){//减少次数
						userCoupon.setNumber(userCoupon.getNumber()-1);
						washCouponService.updateCouponNum(userCoupon);
					}else{//直接删除
						//washCouponService.delUserCouponById(couponId);
					}

				}
				order.setOrderId(orderId+"");
				redirectAttributes.addFlashAttribute("order",order);
				redirectAttributes.addFlashAttribute("orderId",orderId);
				redirectAttributes.addFlashAttribute("mobile",user.getPhoneNum());
             	/*洗车券减少**/
			}
		}



		return "redirect:/my/order"+orderId+".html";

	}
	/**
	 * 首页下单Ajax
	 * @param request
	 * @param response
	 * @param model
	 * @param car
	 * @return
	 */
	@RequestMapping(value = "/xd.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> xdAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, Car car,String couponId,String lat,String lng){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		boolean flag=false;
		FmUtils.FmData(request, model);
		int orderId=-1;
		int carId=-1;
		Users user=(Users) SessionHelper.getAttribute(request, MobileContants.USER_SESSION_KEY);
		if(!StringUtil.isNullOrEmpty(user))
		{

			if (!StringUtil.isNullOrEmpty(car)){
				car.setUserCarId(user.getUserId());
				//首页判断此车辆是否存在
				Map<String,Object> queryMap=new HashMap<String,Object>();
				queryMap.put("mobile",user.getPhoneNum());
				Car isExsit=null;
				List<Car> historyCar=historyCarService.findAllList(queryMap);
				if(historyCar.size()>0){
					isExsit=historyCar.get(0);
				}
				if(StringUtil.isNullOrEmpty(isExsit)){
					carId= historyCarService.add(car);//新增
					car.setId(carId);
					isExsit=car;
					if(carId>0){
						flag=true;
					}
				}else{//更新
					queryMap.put("userCardId", user.getUserId());
					isExsit.setWashAddr(car.getWashAddr());
					isExsit.setName(car.getName());
					isExsit.setWashInfo(car.getWashInfo());
					isExsit.setMobile(car.getMobile());
					isExsit.setCarColor(car.getCarColor());
					isExsit.setCarSeats(car.getCarSeats());
					isExsit.setCarType(car.getCarType());
					isExsit.setCarNo(car.getCarNo());
					isExsit.setPreTime(car.getPreTime());
					flag=historyCarService.update(isExsit);
					carId=isExsit.getId();
				}
				/**移除录入的车辆信息保存的session**/
				SessionHelper.removeAttribute(request, MobileContants.CAR_SESSION_KEY);
             	/*生成订单*/
				//生成订单
				Order order=new Order();
				//order.setCid(isExsit.getId());
				String orderNo=DateUtil.getDateOrderNo();
				order.setOrderId(orderNo);
				String orderTime=DateUtil.getCurrentDateStr();
				order.setCreateTime(orderTime);
				order.setOrderStatus(MobileContants.ORDER_PAY_STATUS_DEFAULT);
				order.setWashTime(car.getPreTime());
				order.setCarNum(car.getCarNo());
				order.setCarColor(car.getCarColor());
				order.setAddress(car.getWashAddr());
				order.setMobile(user.getPhoneNum());
				order.setUserId(user.getUserId());
				order.setAddress(car.getWashAddr());
				order.setCarType(car.getCarType());
				order.setGoodsId(couponId);
				order.setDiscription(car.getWashInfo());
				order.setCarId(carId+"");
				order.setLon(lng);
				order.setLat(lat);
				//设置洗车券id
				order.setCardsId(couponId);
				order.setLat(lat);
				order.setLon(lng);
				//根据 洗车券id查询 洗车券 价格
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("id", couponId);
				GgwashCoupon coupon=washCouponService.findById(m);
				if(!StringUtil.isNullOrEmpty(coupon)){
					order.setPrice(coupon.getPrice());
					order.setWashType(coupon.getName());
				}
				order.setSendOrderStatus("0");
				order.setUserId(user.getUserId());
				//order.setJsParam("");
				orderId=orderService.add(order);
				//下完单后是否开启自动派单功能
				String autoPaiDan=coreService.getValue(CodeCommon.AUTO_PAIDAN);
				if(CodeCommon.AUTO_PAIDAN_FLAG.equals(autoPaiDan)){
					//根据经纬度派单给最近的洗车工师父
					SendOrder sendOrder=new SendOrder();
					order.setOrderId(orderId+"");
					//根据经纬度获取最近的洗车工师父
					AdminBo bo=userServices.findAdminByLatLng(lat,lng);
					sendOrder.setCarNo(order.getCarNum());
					sendOrder.setOrderId(orderId+"");
					sendOrder.setName(isExsit.getName());
					sendOrder.setPreTime(isExsit.getPreTime());
					sendOrder.setMobile(isExsit.getMobile());
					sendOrder.setSendOrderTime(com.zero2ipo.framework.util.DateUtil.getCurrentTime());
					sendOrder.setUserId(bo.getUserId());
					sendOrder.setOperatorId(user.getUserId());
					sendOrder.setStatus(MobileContants.SEND_ORDER_STATUS_1);
					sendOrderService.addSendOrder(sendOrder);
					//派单完成后是否给管理员发送短信或者微信
					String isSendMessage=coreService.getValue(CodeCommon.IS_SENDMESSAGE_TO_ADMIN);
					if(CodeCommon.IS_SENDMESSAGE_TO_ADMIN_FLAG.equals(isSendMessage)){//开启给管理发送派单短信通知
						String sendMessageFlag=coreService.getValue(CodeCommon.SEND_MESSAGE_FLAG);
						if(CodeCommon.SEND_MESSAGE_DUANXIN.equals(sendMessageFlag)){
							//发送短信通知
						}
						if(CodeCommon.SEND_MESSAGE_WEIXIN.equals(sendMessageFlag)){
							//发送微信通知
							String openId=bo.getTel();//获取洗车工绑定的微信openid
							String templateMessageId=coreService.getValue(CodeCommon.PAIDAN_TEMPLATE_MESSAGE);
							String washType=order.getWashType();
							//查询域名
							String  domain=coreService.getValue(CodeCommon.DOMAIN);
							String url=domain+"/renwu/order"+orderId+".html";

							WxTemplate wxTemplate= TemplateMessageUtils.getWxTemplateToAdmin(openId,templateMessageId,url,orderNo, com.zero2ipo.framework.util.DateUtil.getCurrentTime(),isExsit.getName(),isExsit.getCarNo(),isExsit.getWashAddr(),order.getWashTime(),washType);
							//发送模板消息
							String appId=coreService.getValue(CodeCommon.APPID);
							String appsecret=coreService.getValue(CodeCommon.APPSECRET);
							coreService.send_template_message(appId,appsecret,openId,wxTemplate);

						}
					}

				}
				//如果订单 保存 成功的话 ，减去 用户已经使用 的洗车券
				if(orderId>1){
					m.put("userId", user.getUserId());
					//根据couponId查询UserCoupon
					UserCoupon userCoupon=washCouponService.findUserCouponById(m);
					if(!StringUtil.isNullOrEmpty(userCoupon)&&userCoupon.getNumber()>0){//减少次数
						userCoupon.setNumber(userCoupon.getNumber()-1);
						washCouponService.updateCouponNum(userCoupon);
					}
				}
				order.setOrderId(orderId+"");
			}
		}
		resultMap.put("success", flag);
		resultMap.put("url", "/my/order"+orderId+".html");
		return resultMap;

	}

	@Resource(name = "historyCarService")
	private IHistoryCarService historyCarService ;
	@Resource(name = "addressService")
	private IAddressService addressService;
	@Resource(name = "orderService")
	private IOrderService orderService;
	@Resource(name = "washCouponService")
	private IWashCouponService washCouponService;
	@Resource(name = "coreService")
	private ICoreService coreService;
	@Resource(name = "sendOrderService")
	private ISendOrderService sendOrderService;
	@Resource(name = "userServices")
	private IUserServices userServices ;
}

