package com.zero2ipo.module.entity.tzyy;

   /**
    * 投资预约 实体类 cfj_reseration_management
    * Tue Apr 21 10:28:33 GMT+08:00 2015 郑云飞
    */ 


public class ReservationEntity{
	private String id;
	private String userId;
	private String productId;
	private String status;//(0:未联系 1 已联系)
	private String type;
	private String userName;
    private String mobile;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
    private String money;//预约金额
    private String preDate;

	   public String getPreDate() {
		   return preDate;
	   }

	   public void setPreDate(String preDate) {
		   this.preDate = preDate;
	   }

	   public String getMoney() {
		   return money;
	   }

	   public void setMoney(String money) {
		   this.money = money;
	   }

	   public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}

