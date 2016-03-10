package com.zero2ipo.bsb.bizc;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zero2ipo.bsb.entity.Order;

/**
 * cfjCollection 实体类
 * Thu Apr 30 11:19:31 GMT+08:00 2015 zhengyunfei
 */

@Service
public interface IOrderService {
 /**
 *新增
 *@author zhengYunFei
 *@date Thu Apr 30 11:19:31 GMT+08:00 2015
 */
	public List<Order> findAllListPage(Map<String, Object> map, int i, int max);

	public int findAllListCount(Map<String, Object> map);
	/**
	 * @title： 订单信息修改页面初始化
	 * @description: 对不同订单单信息的查询修改数据初始化
	 * @param: id   订单标识
	 * @author: wangli
	 * @return： sendOrder   系统派单信息实体类
	 */
	public Order updOrderInit(String id);
	
	/**
	 * @title： 订单信息	修改
	 * @description: 修改订单的信息,对订单信息的修改操作
	 * @param: order   系统订单信息实体类
	 * @author: wangli
	 * @return： String 修改订单信息的成功、失败信息
	 */
	public String updOrder(Order order);
}

