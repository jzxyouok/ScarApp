package com.zero2ipo.bsb.bizc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.bsb.bizc.IHistoryDao;
import com.zero2ipo.bsb.entity.Car;
import com.zero2ipo.bsb.entity.ServiceProject;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.util.StringUtil;
@Service("historyDao")
public class HistoryDaoImpl implements IHistoryDao {
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	
	
public List<Car> findAllListPage(Map<String, Object> map, int i, int max){
	List<Car> list = new ArrayList<Car>();
	try {
		//设置数据库类型: 网站全局库(01)
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
		list = (List<Car>)baseDao.findForList("findPdCarList", map,i,max);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}
	
	public List<Car> findAllList(Map<String, Object> map){
		List<Car> list = new ArrayList<Car>();
		try {
			//设置数据库类型: 网站全局库(01)
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			list = (List<Car>)baseDao.findForList("findCarList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean update(Car car){
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updCar", car);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public int findAllListCount(Map<String, Object> map){
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			count = (Integer)baseDao.findForObject("findCarListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public Car findById(String id){
			Car car=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
				Map<String,Object> queryMap=new HashMap<String,Object>();
				queryMap.put("id", id);
				car = (Car) baseDao.findForObject("findCarById", queryMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return car;
	}
	 public ServiceProject getServiceProjectById(String id) {
		 ServiceProject entity=null;
			try{
				Map<String,Object> queryMap=new HashMap<String,Object>();
				queryMap.put("id", id);
				entity=(ServiceProject) baseDao.findForObject("findById", queryMap);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return entity;
	 }
	public String getServiceProjectNames(String ids){
		String names="";
		String [] strs=ids.split(",");
		for(int i=0;i<strs.length;i++){
			String value=strs[i];
			if(!StringUtil.isNullOrEmpty(value)){
				ServiceProject bo=this.getServiceProjectById(value);
				if(!StringUtil.isNullOrEmpty(bo)){
					if(i<strs.length-1){
						names+=bo.getName()+",";
					}else{
						names+=bo.getName();
					}
					
				}
			}
				
		}
		return names;
	}
}
