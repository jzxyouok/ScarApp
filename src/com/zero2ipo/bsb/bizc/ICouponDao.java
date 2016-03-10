package com.zero2ipo.bsb.bizc;

import java.util.List;
import java.util.Map;

import com.zero2ipo.bsb.entity.GgwashCoupon;

public interface ICouponDao {
	
	public List<GgwashCoupon> findAllListPage(Map<String, Object> map, int i, int max);
	
	public List<GgwashCoupon> findAllList(Map<String, Object> map);
	
	public boolean update(GgwashCoupon notic);
	
	public int findAllListCount(Map<String, Object> map);

	public GgwashCoupon findById(String id);

	public boolean add(GgwashCoupon bo);

	public boolean delete(String ids);
}
