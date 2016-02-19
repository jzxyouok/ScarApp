package com.zero2ipo.bsb.bizc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.bsb.bizc.ICouponDao;
import com.zero2ipo.bsb.entity.GgwashCoupon;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
@Service("couponDao")
public class CouponDaoImpl implements ICouponDao {
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	
	
public List<GgwashCoupon> findAllListPage(Map<String, Object> map, int i, int max){
	List<GgwashCoupon> list = new ArrayList<GgwashCoupon>();
	try {
		//设置数据库类型: 网站全局库(01)
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
		list = (List<GgwashCoupon>)baseDao.findForList("findGgwash_couponList", map,i,max);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}
	
	public List<GgwashCoupon> findAllList(Map<String, Object> map){
		List<GgwashCoupon> list = new ArrayList<GgwashCoupon>();
		try {
			//设置数据库类型: 网站全局库(01)
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			list = (List<GgwashCoupon>)baseDao.findForList("findGgwash_couponList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean update(GgwashCoupon car){
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updCoupon", car);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	public boolean add(GgwashCoupon car){
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("addCoupon", car);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	/*删除
	*@author zhengYunFei
	*@date Mon Jan 26 11:54:24 GMT+08:00 2015
	*/
	public boolean delete(String id){
		boolean flag=false;
		try{
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id",id.split(","));
				baseDao.delObject("delCoupon", map);
				flag=true;
			}catch(Exception e){
			flag=false;
				e.printStackTrace();
				throw new BaseException("删除！",e);
		}
	return flag;
}
	public int findAllListCount(Map<String, Object> map){
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			count = (Integer)baseDao.findForObject("findGgwash_couponListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public GgwashCoupon findById(String id){
			GgwashCoupon car=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
				Map<String,Object> queryMap=new HashMap<String,Object>();
				queryMap.put("id", id);
				car = (GgwashCoupon) baseDao.findForObject("findCouponById", queryMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return car;
	}
	 
	
}
