package com.zero2ipo.mobile.services.bsb.impl;

import com.zero2ipo.common.entity.Order;
import com.zero2ipo.common.entity.SendOrder;
import com.zero2ipo.common.entity.UserCoupon;
import com.zero2ipo.mobile.dao.bsb.IOrderDao;
import com.zero2ipo.mobile.services.bsb.IOrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("orderService")
public class OrderServiceImpl implements IOrderService{
	@Resource(name = "orderDao")
	private IOrderDao orderDao;
	public int add(Order bo) {
		return orderDao.add(bo);
	}

	public boolean update(Order Order) {
		return orderDao.update(Order);
	}

	@Override
	public List<Order> findAllList(Map<String, Object> queryMap) {
		return orderDao.findAllList(queryMap);
	}

	@Override
	public Order findById(Map<String, Object> queryMap) {
		return orderDao.findById(queryMap);
	}

	@Override
	public boolean updateCoupon(UserCoupon coupon) {
		return orderDao.updateCoupon(coupon);
	}

	@Override
	public SendOrder findSendOrderByOrderId(Map<String, Object> map) {
		return orderDao.findSendOrderByOrderId(map);
	}

	@Override
	public int findIsOrNotFirstOrder(Map<String, Object> queryMap) {
		return orderDao.findIsOrNotFirstOrder(queryMap);
	}

}

