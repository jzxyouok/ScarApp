package com.zero2ipo.mobile.services.bsb;

import java.util.List;
import java.util.Map;

import com.zero2ipo.common.entity.GgwashCoupon;
import com.zero2ipo.common.entity.UserCoupon;

public interface IWashCouponService {

	List<GgwashCoupon> findAllList(Map<String, Object> queryMap);

	public GgwashCoupon findById(Map<String, Object> queryMap) ;
	
	public void delUserCouponById(String couponId);
	
	public UserCoupon findUserCouponById(Map<String, Object> queryMap);

	public void updateCouponNum(UserCoupon userCoupon);

	public List<UserCoupon> findUserCouponList(Map<String, Object> queryMap);

	public List<UserCoupon> IsSd(Map<String, Object> queryMap);
}
