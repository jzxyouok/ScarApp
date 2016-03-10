package com.zero2ipo.bsb.bizc;

import org.springframework.stereotype.Service;

import com.zero2ipo.bsb.entity.Address;

/**
 * cfjCollection 实体类
 * Thu Apr 30 11:19:31 GMT+08:00 2015 zhengyunfei
 */

@Service
public interface IAddressService {
 /**
 *新增
 *@author zhengYunFei
 *@date Thu Apr 30 11:19:31 GMT+08:00 2015
 */
	public Address findById(String id);
 
}

