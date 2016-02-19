package com.zero2ipo.bsb.bizc.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.bsb.bizc.ICouponDao;
import com.zero2ipo.bsb.bizc.IHistoryDao;
import com.zero2ipo.bsb.bizc.IUserCoupon;
import com.zero2ipo.bsb.entity.GgwashCoupon;
import com.zero2ipo.bsb.entity.UserCoupon;
import com.zero2ipo.cfj.user.bizc.IVipManage;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @title:优惠券支付业务处理接口实现类
 * @description: 针对系统优惠券支付业务处理管理接口的实现
 * @author： wangli
 * @date： 2015-10-24
 */
@Service("userCouponService")
public class UserCouponImpl implements IUserCoupon {
	// 自动注入数据库公共操作接口
	@Autowired
	@Qualifier("baseDao")
	private IBaseDao baseDao;
	
	@Autowired @Qualifier("couponDao")
	private ICouponDao couponDao;  
	
	@Resource(name = "historyDao")
	private IHistoryDao historyDao;
	
	@Resource(name = "vipManage")
	private IVipManage vipManage;
	@Override
	public String addUserCoupon(UserCoupon userCoupon) {
		String backInfo = "1";
		try {
				// 序列获取用户标识
			long id = baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT);
			userCoupon.setId(String.valueOf(id));
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			userCoupon.setCreateTime(df.format(new Date()));
			// 添加用户
			baseDao.addObject("addUserCoupon", userCoupon);
		} catch (Exception e) {
			backInfo = "0";
			BaseLog.e(this.getClass(), "addUserCoupon添加优惠券支付信息失败", e);
			throw new BaseException("addVip添加优惠券支付信息失败");
		}
		return backInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCoupon> findUserCouponInfoList(Map<String, Object> map,
			int curNo, int curSize) {
		List<UserCoupon> list = null;
		try {
			//设置数据库类型: 网站全局库(01)
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			list = baseDao.findForList("findUserCouponList", map, curNo, curSize);
			for(int i=0;i<list.size();i++){
				String cid=list.get(i).getCouponId();
			//*	String sids=list.get(i).getSid();
				//String  addrId=list.get(i).getAddrId();//*
				GgwashCoupon coupon=couponDao.findById(cid);
			//*	Car car=historyDao.findById(cid);
//				Address address=addressService.findById(addrId);
//				if(!StringUtil.isNullOrEmpty(address)){
//					car.setAddress(address);
//				}else{
//					address=new Address();
//					address.setMobile(car.getMobile());
//					address.setWashAddr(car.getWashAddr());
//				}//
				//list.get(i).setCar(car);
				//String serviceProjectName=historyDao.getServiceProjectNames(sids);
				//list.get(i).setProjectName(serviceProjectName);
				list.get(i).setCoupon(coupon);
//				String userid=list.get(i).getUserId();
//				UserBo entity=vipManage.updUserInit(userid);
//				String mobile="";
//				if(!StringUtil.isNullOrEmpty(entity)){
//					mobile=entity.getMobile();
//				}
//				Map<String, Object> jsonMap = new HashMap<String, Object>();
//				jsonMap.put("openId", userid);
//				String name="";
//				String carNo="";
//				Car car=null;
//				List<Car> carList = historyDao.findAllList(jsonMap);
//				if(carList!=null&&carList.size()>0){
//					car=carList.get(0);
//					name=car.getName();
//					carNo=car.getCarNo();
//					car.setMobile(mobile);
//				}else{
//					car=new Car();
//					car.setMobile(mobile);
//
//				}
//				list.get(i).setCar(car);
//				list.get(i).setName(name);
//				list.get(i).setCarNo(carNo);
//				list.get(i).setMobile(mobile);
//
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findUserCouponList系统优惠券支付列表信息查询失败", e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCoupon> findUserCouponInfoList(UserCoupon userCoupon) {
		List list = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("findUserCouponList", userCoupon);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findUserCouponList 查询优惠券支付列表", e);
			throw new BaseException("查询优惠券支付列表出错！", e);
		}
		return list;
	}

	@Override
	public int findUserCouponInfoListCount(Map<String, Object> map) {
		int count = 0;
		try {
			count = (Integer) baseDao
					.findForObject("findUserCouponListCount", map);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findUserCouponListCount查询优惠券支付个数", e);
		}
		return count;
	}

	@Override
	public String updUserCoupon(UserCoupon userCoupon) {
		SqlMapClient client = baseDao.getSqlMapClient();
		String backInfo = "0";
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			client.update("updUserCoupon", userCoupon);
			client.executeBatch();// 执行批处理模式
			backInfo = "1";
		} catch (Exception e) {
			 backInfo = "0";
			BaseLog.e(this.getClass(), "updUserCoupon修改优惠券支付信息", e);
			throw new BaseException("修改优惠券支付信息出错！", e);
		}
		return backInfo;
	}

	@Override
	public UserCoupon upduserCouponInit(String id) {
		UserCoupon userCoupon = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			userCoupon = (UserCoupon) baseDao.findForObject("findUserCouponById", id);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findUserCouponById 修改优惠券支付前查询", e);
			throw new BaseException("修改优惠券支付前查询出错！", e);
		}
		return userCoupon;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String delUserCouponById(String ids) {
		String backInfo = "0";
		try {
			Map map = new HashMap();
			map.put("id", ids.split(","));
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.delObject("delUserCoupon", map);
			// 删除成功
			backInfo = "1";
		} catch (Exception e) {
			backInfo = "0"; // 删除失败
			BaseLog.e(this.getClass(), "delUserCoupon 删除出错", e);
			throw new BaseException("删除出错！", e);
		}
		return backInfo;

	}

	
}
