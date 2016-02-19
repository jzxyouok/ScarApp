package com.zero2ipo.bsb.entity;
import java.io.Serializable;
import java.util.Date;

   /**
    * userCoupon 实体类
    * Sat Oct 24 22:43:37 CST 2015 wangli
    */ 


public class UserCoupon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private String couponId;
	private String buyDate;
	private String createTime;
	private String id;
	private String name;
	private String carNo;
	private String mobile;
	private GgwashCoupon coupon;
	
	
	private Car car;
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public GgwashCoupon getCoupon() {
		return coupon;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setCoupon(GgwashCoupon coupon) {
		this.coupon = coupon;
	}
	public void setUserId(String userId){
	this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	public void setCouponId(String couponId){
	this.couponId=couponId;
	}
	public String getCouponId(){
		return couponId;
	}
	
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}


}

