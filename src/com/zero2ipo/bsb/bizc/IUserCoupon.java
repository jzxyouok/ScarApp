/**
 * @(#)IuserManage.java	10:10 07/08/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-08 
 */
package com.zero2ipo.bsb.bizc;

import java.util.List;
import java.util.Map;

import com.zero2ipo.bsb.entity.UserCoupon;

/**
 * @title: 优惠券付款信息业务处理接口定义
 * @description: 针对优惠券付款信息业务处理统一接口的定义类
 * @author： wangli
 * @date：2015-10-24
 */
public interface IUserCoupon {

	/**
	 * @title： 优惠券付款信息信息 新增
	 * @description: 存储优惠券付款信息的信息,不同管理人员对订单进行优惠券付款信息操作
	 * @param: userCoupon 优惠券付款信息实体类
	 * @author: wangli
	 */
	public String addUserCoupon(UserCoupon userCoupon);

	/**
	 * @title： 优惠券信息列表 查询
	 * @description: 根据页面不同查询条件 获取系统会员列表信息
	 * @param: map 优惠券信息查询条件
	 * @param: curNo 列表查询当前页码
	 * @param: curSize 最大记录位置
	 * @author: wangli
	 * @return： list<userCoupon> 优惠券付款信息信息(userCoupon)列表
	 */
	public List<UserCoupon> findUserCouponInfoList(Map<String, Object> map,
												   int curNo, int curSize);

	/**
	 * @title：优惠券付款信息信息列表 查询
	 * @description: 根据优惠券付款信息实体对象获取系统会员列表信息
	 * @param: userCoupon 系统优惠券付款信息信息实体类
	 * @author: wangli
	 * @return： list<userCoupon> 优惠券付款信息信息(userCoupon)列表
	 */
	public List<UserCoupon> findUserCouponInfoList(UserCoupon userCoupon);

	/**
	 * @title： 优惠券付款信息信息列表 总条数查询
	 * @description: 根据不同查询条件 获取系统会员列表总记录条数
	 * @param: map 优惠券付款信息信息查询条件
	 * @author: wangli
	 * @return： int 不同条件查询的总记录条数
	 */
	public int findUserCouponInfoListCount(Map<String, Object> map);

	/**
	 * @title： 优惠券付款信息信息修改页面初始化
	 * @description: 对不同系统优惠券付款信息信息的查询修改数据初始化
	 * @param: userCouponId 优惠券付款信息标识
	 * @author: wangli
	 * @return： userCoupon 系统优惠券付款信息信息实体类
	 */
	public UserCoupon upduserCouponInit(String id);

	/**
	 * @title： 优惠券付款信息信息 修改
	 * @description: 修改系统优惠券付款信息的信息,对系统优惠券付款信息信息的修改操作
	 * @param: userCoupon 系统优惠券付款信息信息实体类
	 * @author: wangli
	 * @return： String 修改优惠券付款信息信息的成功、失败信息
	 */
	public String updUserCoupon(UserCoupon userCoupon);

	/**
	 * @title 优惠券信息删除
	 * @param ids
	 * @return
	 */
	public String delUserCouponById(String ids);
}
