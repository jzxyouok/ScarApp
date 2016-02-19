package com.zero2ipo.module.entity.invitationCode;


   /**
    * cfjInvitationCode 实体类
    * Tue Dec 30 15:13:29 GMT+08:00 2014 zhengyunfei
    */ 


public class InvitationCodeUserEntity{
	private String id;//主键
	private String value;//邀请码
	private String useId;//使用者id
	private String useTime;//使用时间
	private String owerId;//拥有者ID
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getOwerId() {
		return owerId;
	}
	public void setOwerId(String owerId) {
		this.owerId = owerId;
	}
}

