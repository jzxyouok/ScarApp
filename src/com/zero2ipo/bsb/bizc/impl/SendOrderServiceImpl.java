package com.zero2ipo.bsb.bizc.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.bsb.bizc.ISendOrderService;
import com.zero2ipo.bsb.entity.SendOrder;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.plugins.user.biz.IuserManage;
import com.zero2ipo.plugins.user.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @title:派单业务处理接口实现类
 * @description: 针对系统派单业务处理管理接口的实现
 * @author： wangli
 * @date： 2015-10-18
 */
@Service("sendOrderService")
public class SendOrderServiceImpl implements ISendOrderService{

	// 自动注入数据库公共操作接口
	@Autowired
	@Qualifier("baseDao")
	private IBaseDao baseDao;
	
	//自动注入用户信息管理业务处理操作接口
	@Autowired @Qualifier("userManage")
	private IuserManage userManage ;
	
	/**
	 * @title： 派单信息    新增
	 * @description: 存储派单的信息,不同管理人员对订单进行派单操作
	 * @param: sendOrder   派单实体类
	 * @author: wangli
	 */
	@Override
	public String addSendOrder(SendOrder sendOrder) {
		String backInfo = "1";
		try {
			//sendOrder.getUserId();
				// 序列获取标识
			long sendOrderId = baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT);
			sendOrder.setId((String.valueOf(sendOrderId)));
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			sendOrder.setOperatorDate(df.format(new Date()));
			// 派单
			baseDao.addObject("addSendOrder", sendOrder);
		} catch (Exception e) {
			e.printStackTrace();
			backInfo = "0";
			BaseLog.e(this.getClass(), "addSendOrder派单失败", e);
			throw new BaseException("addSendOrder派单失败");
		}
		return backInfo;
	}
	
	/**
	 * @title： 派单信息列表   查询
	 * @description: 根据页面不同查询条件 获取系统会员列表信息
	 * @param: 	map 	派单信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: wangli
	 * @return： list<sendOrder> 派单信息(sendOrder)列表
	 */
	@SuppressWarnings("unchecked")
	public List<SendOrder> findsendOrderInfoList(Map<String, Object> map , int curNo , int curSize){
		List<SendOrder> list = null;
		try {
			list = baseDao.findForList("findSendOrderList", map, curNo, curSize);
			for(int i=0;i<list.size();i++){
				String uid=list.get(i).getUserId();
				User user=userManage.updUserInit(uid);
				list.get(i).setUser(user);
			}
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findSendOrderList派单列表信息查询失败", e);
		}
		return list;
	}
	
	/**
	 * @title：派单信息列表    查询
	 * @description: 根据派单实体对象获取系统会员列表信息
	 * @param: sendOrder   系统派单信息实体类
	 * @author: wangli
	 * @return： list<sendOrder> 派单信息(sendOrder)列表
	 */
	@SuppressWarnings("unchecked")
	public List<SendOrder> findSendOrderInfoList(SendOrder sendOrder){
		List list = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("findSendOrderList", sendOrder);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findSendOrderList查询派单列表", e);
			throw new BaseException("查询派单表出错！", e);
		}
		return list;
	}
	
	/**
	 * @title： 派单信息列表    总条数查询
	 * @description: 根据不同查询条件 获取系统会员列表总记录条数
	 * @param: map 	派单信息查询条件
	 * @author: wangli
	 * @return： int 不同条件查询的总记录条数
	 */
	public int findSendOrderInfoListCount(Map<String, Object> map){
		int count = 0;
		try {
			count = (Integer) baseDao
					.findForObject("findSendOrderListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findSendOrderListCount查询派单个数", e);
		}
		return count;
	}
	


	/**
	 * @title： 派单信息修改页面初始化
	 * @description: 对不同系统派单信息的查询修改数据初始化
	 * @param: sendOrderId   派单标识
	 * @author: wangli
	 * @return： sendOrder   系统派单信息实体类
	 */
	public SendOrder updSendOrderInit(String id){
		SendOrder sendOrder= null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			sendOrder = (SendOrder) baseDao.findForObject("findSendOrderById", id);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findSendOrderById 修改派单前查询", e);
			throw new BaseException("修改派单前查询出错！", e);
		}
		return sendOrder;
	}

	/**
	 * @title： 派单信息	修改
	 * @description: 修改系统派单的信息,对系统派单信息的修改操作
	 * @param: sendOrder   系统派单信息实体类
	 * @author: wangli
	 * @return： String 修改派单信息的成功、失败信息
	 */
	public String updSendOrder(SendOrder sendOrder){
		SqlMapClient client = baseDao.getSqlMapClient();
		String backInfo = "0";
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			String staus=sendOrder.getStatus();
			System.out.print("s"+staus);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			if(sendOrder.getStatus().equals("2")){
				sendOrder.setFinishDate(df.format(new Date()));
			}
			client.update("updSendOrder", sendOrder);
			client.executeBatch();// 执行批处理模式
			backInfo = "1";
		} catch (Exception e) {
			 backInfo = "0";
			e.printStackTrace();
			BaseLog.e(this.getClass(), "updSendOrder 修改派单信息出错", e);
			throw new BaseException("修改派单信息出错！", e);
		}
		return backInfo;
	}

	@Override
	public SendOrder findSendOrderByOrderId(Map<String, Object> query) {
		SendOrder sendOrder= null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			sendOrder = (SendOrder) baseDao.findForObject("findSendOrderByMap", query);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findSendOrderById 修改派单前查询", e);
			throw new BaseException("修改派单前查询出错！", e);
		}
		return sendOrder;
	}

}
