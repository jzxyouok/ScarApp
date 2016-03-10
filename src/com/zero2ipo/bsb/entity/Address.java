package com.zero2ipo.bsb.entity;

public class Address {
	private String washAddr;
	private String mobile;
	private String cid;
	private int id;
	private String openId;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWashAddr() {
		return washAddr;
	}
	public void setWashAddr(String washAddr) {
		this.washAddr = washAddr;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
