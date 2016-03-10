package com.zero2ipo.bsb.bizc.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.bsb.bizc.IAddressService;
import com.zero2ipo.bsb.bizc.IHistoryDao;
import com.zero2ipo.bsb.bizc.IOrderService;
import com.zero2ipo.bsb.bizc.ISendOrderService;
import com.zero2ipo.bsb.entity.Car;
import com.zero2ipo.bsb.entity.Order;
import com.zero2ipo.bsb.entity.SendOrder;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.user.biz.IuserManage;
import com.zero2ipo.plugins.user.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements IOrderService{
	// 自动注入数据库公共操作接口
	@Autowired
	@Qualifier("baseDao")
	private IBaseDao baseDao;
	@Resource(name = "historyDao")
	private IHistoryDao historyDao;
	@Resource(name="addressService")
	private IAddressService addressService;
	@Resource(name="sendOrderService")
	private ISendOrderService sendOrderService;
	//自动注入用户信息管理业务处理操作接口
	@Autowired @Qualifier("userManage")
	private IuserManage userManage ;
	public List<Order> findAllListPage(Map<String, Object> map, int skip, int max){
		List<Order> list = new ArrayList<Order>();
		Map<String,Object> query=new HashMap<String,Object>();
		try {
			//设置数据库类型: 网站全局库(01)
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			list = (List<Order>)baseDao.findForList("findBsbOrderList", map,skip,max);
			for(int i=0;i<list.size();i++){
				String orderId=list.get(i).getId();//订单id
				//根据订单id查询派给谁了
				query.put("orderId", orderId);
				SendOrder sendOrder=null;
				sendOrder=sendOrderService.findSendOrderByOrderId(query);
				if(!StringUtil.isNullOrEmpty(sendOrder)){
					String userId=sendOrder.getUserId();
					if(!StringUtil.isNullOrEmpty(userId)){
						User user=userManage.updUserInit(userId);
						if(!StringUtil.isNullOrEmpty(user)){
							list.get(i).setSendOrderToName(user.getUserRealName());
						}
					}
				}else{
					sendOrder=new SendOrder();
				}
				list.get(i).setSendOrder(sendOrder);
				String carId=list.get(i).getCarId();
				Car car=historyDao.findById(carId);
				if(!StringUtil.isNullOrEmpty(car)){
					list.get(i).setCar(car);
				}
			/*	String cid=list.get(i).getCid();
				String sids=list.get(i).getSid();
				String  addrId=list.get(i).getAddrId();
				Car car=historyDao.findById(cid);
				Address address=addressService.findById(addrId);
				if(!StringUtil.isNullOrEmpty(address)){
					car.setAddress(address);
				}else{
					address=new Address();
					address.setMobile(car.getMobile());
					address.setWashAddr(car.getWashAddr());
				}
				list.get(i).setCar(car);*/
				//String serviceProjectName=historyDao.getServiceProjectNames(sids);
				//list.get(i).setProjectName(serviceProjectName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		public int findAllListCount(Map<String, Object> map){
			int count=0;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
				count = (Integer)baseDao.findForObject("findBsbOrderListCount", map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		/**
		 * @title： 订单信息修改页面初始化
		 * @description: 对不同订单单信息的查询修改数据初始化
		 * @param: id   订单标识
		 * @author: wangli
		 * @return： order   系统派单信息实体类
		 */
		public Order updOrderInit(String id){
			Order order= null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id",id);
			    order = (Order) baseDao.findForObject("findBsbOrderById", map);
			} catch (Exception e) {
				e.printStackTrace();
				BaseLog.e(this.getClass(), "findBsbOrderById 修改订单前查询", e);
				throw new BaseException("修改订单前查询出错！", e);
			}
			return order;
		}
		/**
		 * @title： 订单信息	修改
		 * @description: 修改订单的信息,对订单信息的修改操作
		 * @param: order   系统订单信息实体类
		 * @author: wangli
		 * @return： String 修改订单信息的成功、失败信息
		 */
		@Override
		public String updOrder(Order order) {
			SqlMapClient client = baseDao.getSqlMapClient();
			String backInfo = "0";
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				client.update("updBsbOrder", order);
				client.executeBatch();// 执行批处理模式
				backInfo = "1";
			} catch (Exception e) {
				e.printStackTrace();
				 backInfo = "0";
				BaseLog.e(this.getClass(), "updBsbOrder修改订单信息", e);
				throw new BaseException("修改订单信息出错！", e);
			}
			return backInfo;
		}
}

