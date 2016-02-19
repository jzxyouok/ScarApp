package com.zero2ipo.bsb.bizc.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.bsb.bizc.IAddressService;
import com.zero2ipo.bsb.entity.Address;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.util.StringUtil;

@Service("addressService")
public class AddressServiceImpl implements IAddressService{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	public Address findById(String id){
		Address address=null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			Map<String,Object> queryMap=new HashMap<String,Object>();
			queryMap.put("id", id);
			if(!StringUtil.isNullOrEmpty(id)){
				address = (Address) baseDao.findForObject("findAddressById", queryMap);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
}
}

