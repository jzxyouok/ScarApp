package com.zero2ipo.module.entity.invitationCode;

   /**
    * 邀请码
    * cfjInvitationCode 实体类
    * Tue Dec 30 15:13:29 GMT+08:00 2014 zhengyunfei
    */ 


public class InvitationCodeEntity{
	private String id;
	private String userId;//邀请码关联的会员ID
	private String value;//邀请码
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

