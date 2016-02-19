package com.zero2ipo.module.entity.relation;

   /**
    * cfjAdminRFund 实体类
    * Tue Dec 02 11:24:01 GMT+08:00 2014 yangxn
    */ 


public class AdminRFundEntity{
	private String realId;
	private String adminId;
	private String fundId;
	private int sort;
	public String getRealId() {
		return realId;
	}
	public void setRealId(String realId) {
		this.realId = realId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getFundId() {
		return fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}

