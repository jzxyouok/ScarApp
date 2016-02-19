package com.zero2ipo.bsb.webc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.bsb.bizc.IUserCoupon;
import com.zero2ipo.bsb.entity.UserCoupon;
import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.user.bo.User;

/**
 * @title： 系统优惠券支付信息管理控制类
 * @description: 针对系统优惠券支付信息统一管理的控制类。
 * @author： wangli
 * @date： 2015-10-24
 */
@Controller
@RequestMapping("/userCouponManage")
public class UserCouponManageCtrl extends BaseCtrl {
	@Autowired
	@Qualifier("userCouponService")
	private IUserCoupon userCouponManage;// 优惠券支付分类业务处理类

	/**
	 * @title: 初始优惠券支付页面
	 * @description: 初始优惠券支付页面
	 * @author: wangli
	 * @return：/s9/usercoupon/userCoupon_init.jsp
	 */
	@RequestMapping("forInit.shtml")
	public ModelAndView forInit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/s9/usercoupon/userCoupon_init.jsp");
		return mv;
	}

	/**
	 * @title: 查找优惠券支付分类信息列表
	 * @description:查找优惠券支付信息列表
	 * @param : curNo 分页-页码
	 * @param : curSize 分页-页面加载记录条数
	 * @author: wangli
	 * @return:jsonMap
	 */
	@RequestMapping("userCouponInfoList.shtml")
	@ResponseBody
	public Map<String, Object> userCouponInfoList(String curNo, String curSize,
			String curPgn) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			/************* 分页处理 ************/
			int skip;
			int max;
			if (StringUtil.isNullOrEmpty(curNo))
				curNo = "0";
			if (StringUtil.isNullOrEmpty(curSize))
				curSize = "20";
			skip = Integer.parseInt(curNo);
			max = Integer.parseInt(curSize);
			/************ 分页处理结束 ***********/
			Map<String, Object> map = new HashMap<String, Object>();
			List<UserCoupon> userCouponInfo = userCouponManage
					.findUserCouponInfoList(map, (skip - 1) * max, max);
			int total = userCouponManage.findUserCouponInfoListCount(map);
			jsonMap.put("Rows", userCouponInfo);
			jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "优惠券支付信息初始化有误", e);
		}
		return jsonMap;
	}

	/**
	 * @title: 初始优惠券支付页面
	 * @description: 初始优惠券支付页面
	 * @author: wangli
	 * @return：/s9/usercoupon/userCoupon_add.jsp
	 */
	@RequestMapping("userCouponManageAddInit.shtml")
	public ModelAndView forAddInit() {
		ModelAndView mv = new ModelAndView("/s9/usercoupon/userCoupon_add.jsp");
		@SuppressWarnings("unused")
		User oper = (User) session.getAttribute(GlobalConstant.SESSION_USER);
		// 设置添加该用户信息的操作人员
		UserCoupon userCoupon = new UserCoupon();
		// vip.setAddUser(oper.getAddUser());
		// vip.setAddDate(oper.getAddDate());
		mv.addObject("userCoupon", userCoupon);
		return mv;
	}

	/**
	 * @title： 优惠券支付信息 新增
	 * @description: 存储系统优惠券支付的信息,不同管理人员对优惠券支付的添加操作
	 * @param: userCoupon 系统优惠券支付信息实体类
	 * @author: wangli
	 * @return： ModelAndView 新增优惠券支付成功、失败信息
	 */
	@RequestMapping("addUserCoupon.shtml")
	public ModelAndView addUserCoupo(UserCoupon userCoupon) {
		ModelAndView mv = new ModelAndView("/s9/usercoupon/userCoupon_add.jsp");
		String backInfo = null;
		try {
			userCoupon.setCreateTime(DateUtil.format(new Date()));
			backInfo = userCouponManage.addUserCoupon(userCoupon);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "添加优惠券支付信息失败", e);
		}
		mv.addObject("backInfo", backInfo);
		mv.addObject("userCoupon", userCoupon);
		return mv;
	}

	/**
	 * @title： 优惠券支付信息 修改初始化
	 * @description: 修改系统优惠券支付的信息,对系统优惠券支付信息的修改操作
	 * @param: id 系统优惠券支付标识
	 * @author: wangli
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
	@RequestMapping("updUserCouponInit.shtml")
	public ModelAndView updUserCouponInit(String id) {
		ModelAndView mv = new ModelAndView("/s9/usercoupon/userCoupon_upd.jsp");
		try {
			// 用户信息修改初始化
			UserCoupon userCoupon = userCouponManage.upduserCouponInit(id);
			mv.addObject("UserCoupon", userCoupon);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "修改优惠券支付前查询出错", e);
		}
		return mv;
	}

	/**
	 * @title： 优惠券支付信息 修改
	 * @description: 修改系统优惠券支付的信息,对系统优惠券支付信息的修改操作
	 * @param: userCoupon 系统优惠券支付信息实体类
	 * @author: wangli
	 * @return： ModelAndView 修改优惠券支付信息的成功、失败信息
	 */
	@RequestMapping("updUserCoupon.shtml")
	public ModelAndView updUserCoupon(UserCoupon userCoupon) {
		ModelAndView mv = new ModelAndView("/s9/usercoupon/userCoupon_upd.jsp");
		String backInfo = null;
		try {
			backInfo = userCouponManage.updUserCoupon(userCoupon);

		} catch (Exception e) {
			BaseLog.e(this.getClass(), "updVip修改优惠券支付信息失败", e);
		}
		mv.addObject("backInfo", backInfo);
		mv.addObject("userCoupon", userCoupon);
		return mv;
	}

	/**
	 * @title： 优惠券支付信息 删除
	 * @description: 删除指定优惠券支付标识的系统用户信息,对多个系统优惠券支付信息的删除操作
	 * @param: ids 优惠券支付标识（多个优惠券支付时用,号隔开）
	 * @author: wangli
	 * @return： Map 删除优惠券支付信息的成功、失败信息
	 */
	@RequestMapping("delUserCoupon.shtml")
	@ResponseBody
	public Map<String, Object> delUserCoupon(String ids) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String backInfo = "";
		backInfo = userCouponManage.delUserCouponById(ids);
		jsonMap.put("message", backInfo);
		return jsonMap;
	}

}