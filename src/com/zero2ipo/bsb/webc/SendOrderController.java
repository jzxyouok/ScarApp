package com.zero2ipo.bsb.webc;

import com.zero2ipo.bsb.bizc.IOrderService;
import com.zero2ipo.bsb.bizc.ISendOrderService;
import com.zero2ipo.bsb.entity.Order;
import com.zero2ipo.bsb.entity.SendOrder;
import com.zero2ipo.common.token.AvoidDuplicateSubmission;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.CodeCommon;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import com.zero2ipo.plugins.code.bo.CodeInfo;
import com.zero2ipo.plugins.role.biz.ISysRole;
import com.zero2ipo.plugins.role.bo.SysRole;
import com.zero2ipo.plugins.user.biz.IuserManage;
import com.zero2ipo.plugins.user.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title： 派单控制类
 * @description: 针对系统派单的控制类。
 * @author： wangli
 * @date： 2015-10-18
 */
@Controller
@RequestMapping("/sendOrder")
public class SendOrderController extends BaseCtrl {
	@Autowired
	@Qualifier("sendOrderService")
	private ISendOrderService sendOrderService;
	@Resource(name = "orderService")
	private IOrderService orderService;
	// 自动注入用户信息管理业务处理操作接口
	@Autowired
	@Qualifier("userManage")
	private IuserManage userManage;
	@Autowired
	@Qualifier("sysRole")
	public ISysRole sysRole;
	@Autowired
	@Qualifier("codeManage")
	private ICodeManage codeManage;
	/**
	 * @title: 初始派单页面
	 * @description: 初始派单页面
	 * @author: wangli
	 * @return：/s9/order/sendOrder_add.jsp
	 */
	@RequestMapping("sendOrderAddInit.shtml")
	public ModelAndView forAddInit(String orderId) {
		ModelAndView mv = new ModelAndView("/s9/order/sendOrder_add.jsp");
		@SuppressWarnings("unused")
		SendOrder sendOrder = new SendOrder();
		mv.addObject("orderId", orderId);
		// 1.获得用户
		User user = (User) session.getAttribute("user");
		List<User> userList = userManage.findUserList();
		sendOrder.setOperatorId(user.getUserId());
		mv.addObject("sendOrder", sendOrder);
		mv.addObject("userList", userList);
		return mv;
	}

	/**
	 * @title： 派单信息 新增
	 * @description: 存储系统派单的信息,不同管理人员对派单的添加操作
	 * @param: sendOrder 系统派单信息实体类
	 * @author: wangli
	 * @return： ModelAndView 新增派单成功、失败信息
	 */
	@RequestMapping("sendOrderAdd.shtml")
	@AvoidDuplicateSubmission(needRemoveToken = true)
	public ModelAndView sendOrderAdd(SendOrder sendOrder) {
		ModelAndView mv = new ModelAndView("/s9/order/sendOrder_add.jsp");
		String backInfo = null;
		try {
			String id = sendOrder.getOrderId();
			Order order = orderService.updOrderInit(id);
			order.setSendOrderStatus("1");
			//更新订单的表的同时，也需要更改派单表的状态
			orderService.updOrder(order);
			sendOrder.setStatus("1");//已派單
			backInfo = sendOrderService.addSendOrder(sendOrder);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "sendOrder 派单失败", e);
		}
		mv.addObject("backInfo", backInfo);
		mv.addObject("sendOrder", sendOrder);
		return mv;
	}

	/**
	 * @title: 初始派单页面
	 * @description: 初始派单页面
	 * @author: wangli
	 * @return：/s9/order/sendOrder_init.jsp
	 */
	@RequestMapping("forInit.shtml")
	public ModelAndView forInit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/s9/order/sendOrder_init.jsp");
		return mv;
	}

	/**
	 * @title: 查找派单分类信息列表
	 * @description:查找派单信息列表
	 * @param : curNo 分页-页码
	 * @param : curSize 分页-页面加载记录条数
	 * @author: wangli
	 * @return:jsonMap
	 */
	@RequestMapping("sendOrderInfoList.shtml")
	@ResponseBody
	public Map<String, Object> sendOrderInfoList(String curNo, String curSize,
			String curPgn, String status) {
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
			map.put("status", status); // 状态
			User user = (User) session.getAttribute("user");
			// 返回的是roleId
			List<String> roleList = userManage.findUserRole(user.getUserId());
			Map<String, Object> params = new HashMap<String, Object>();
			if(!user.getPosition().contains("管理员")){
				params.put("roleName", "洗车工");
			}
			
			List<SysRole> washcarList = sysRole.findRoleList(params);
			if (washcarList.size() > 0) {
				SysRole sysRole = washcarList.get(0);
				if (roleList.size() > 0) {
					if (!sysRole.getRoleName().contains("管理员")){
							map.put("userId", user.getUserId());
					}
				}
			}
			List<SendOrder> sendOrderInfo = sendOrderService
					.findsendOrderInfoList(map, (skip - 1) * max, max);
			int total = sendOrderService.findSendOrderInfoListCount(map);
			jsonMap.put("Rows", sendOrderInfo);
			jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "派单信息初始化有误", e);
		}
		return jsonMap;
	}

	/**
	 * @title： 派单信息 修改初始化
	 * @description: 修改系统派单的信息,对系统派单信息的修改操作
	 * @param: vipId 系统派单标识
	 * @author: wangli
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
	@RequestMapping("updSendOrderInit.shtml")
	public ModelAndView updSendOrderInit(String id) {
		ModelAndView mv = new ModelAndView("/s9/order/sendOrder_upd.jsp");
		try {
			SendOrder sendOrder = sendOrderService.updSendOrderInit(id);
			String uid = sendOrder.getUserId();
			User user = userManage.updUserInit(uid);
			mv.addObject("userRealName", user.getUserRealName());
			mv.addObject("sendOrder", sendOrder);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "sendOrder 修改配单前查询", e);
		}
		return mv;
	}

	/**
	 * @title： 派单信息 修改
	 * @description: 修改系统派单的信息,对系统派单信息的修改操作
	 * @param: sendOrder 系统派单信息实体类
	 * @author: wangli
	 * @return： ModelAndView 修改派单信息的成功、失败信息
	 */
	@RequestMapping("updSendOrder.shtml")
	public ModelAndView updSendOrder(SendOrder sendOrder) {
		ModelAndView mv = new ModelAndView("/s9/order/sendOrder_upd.jsp");
		String backInfo = null;
		try {
			backInfo = sendOrderService.updSendOrder(sendOrder);

		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "updSendOrder修改派单信息失败", e);
		}
		mv.addObject("backInfo", backInfo);
		mv.addObject("sendOrder", sendOrder);
		return mv;
	}

	/**
	 * 订单轮训刷新
	 * @return
	 */
	@RequestMapping("findOrderByMap.shtml")
	@ResponseBody
	public int findByMap(){
		Map<String,Object> queryMap=new HashMap<String,Object>();
		//查询字典表里轮询时间的间隔
		queryMap.put("codeValue", CodeCommon.MESSAGE_PUSH);
		CodeInfo code=codeManage.findCodeInfo(queryMap);
		int time2=-30;
		String time=code.getContent2();
		try {
			time2=Integer.parseInt(time);
		} catch (Exception e) {

		}
		queryMap.put("time", -time2);
		int  count= orderService.findAllListCount(queryMap);
		return count;
	}
}
