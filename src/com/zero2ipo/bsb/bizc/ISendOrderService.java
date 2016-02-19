/**
 * @(#)IuserManage.java	10:10 07/08/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-08 
 */
package com.zero2ipo.bsb.bizc;

import java.util.List;
import java.util.Map;

import com.zero2ipo.bsb.entity.SendOrder;

/**
 * @title: 派单业务处理接口定义
 * @description: 针对派单业务处理统一接口的定义类
 * @author： wangli
 * @date：2015-10-18
 */
public interface ISendOrderService {

	
	/**
	 * @title： 派单信息    新增
	 * @description: 存储派单的信息,不同管理人员对订单进行派单操作
	 * @param: sendOrder   派单实体类
	 * @author: wangli
	 */
	public String addSendOrder(SendOrder sendOrder);
	/**
	 * @title： 派单信息列表   查询
	 * @description: 根据页面不同查询条件 获取系统会员列表信息
	 * @param: 	map 	派单信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: wangli
	 * @return： list<sendOrder> 派单信息(sendOrder)列表
	 */
	public List<SendOrder> findsendOrderInfoList(Map<String, Object> map, int curNo, int curSize);
	
	/**
	 * @title：派单信息列表    查询
	 * @description: 根据派单实体对象获取系统会员列表信息
	 * @param: sendOrder   系统派单信息实体类
	 * @author: wangli
	 * @return： list<sendOrder> 派单信息(sendOrder)列表
	 */
	public List<SendOrder> findSendOrderInfoList(SendOrder sendOrder);
	
	/**
	 * @title： 派单信息列表    总条数查询
	 * @description: 根据不同查询条件 获取系统会员列表总记录条数
	 * @param: map 	派单信息查询条件
	 * @author: wangli
	 * @return： int 不同条件查询的总记录条数
	 */
	public int findSendOrderInfoListCount(Map<String, Object> map);
	


	/**
	 * @title： 派单信息修改页面初始化
	 * @description: 对不同系统派单信息的查询修改数据初始化
	 * @param: sendOrderId   派单标识
	 * @author: wangli
	 * @return： sendOrder   系统派单信息实体类
	 */
	public SendOrder updSendOrderInit(String id);

	/**
	 * @title： 派单信息	修改
	 * @description: 修改系统派单的信息,对系统派单信息的修改操作
	 * @param: sendOrder   系统派单信息实体类
	 * @author: wangli
	 * @return： String 修改派单信息的成功、失败信息
	 */
	public String updSendOrder(SendOrder sendOrder);
	public SendOrder findSendOrderByOrderId(Map<String, Object> query);

	
}
