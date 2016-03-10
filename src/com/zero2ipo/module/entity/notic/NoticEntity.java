package com.zero2ipo.module.entity.notic;
import java.util.Date;

   /**
    * cfjNotic 实体类
    * Tue Oct 14 14:04:10 GMT+08:00 2014 yangxn
    */ 


public class NoticEntity{
	private String noticId;
	private String userId;
	private String status;
	private Date createTime;
	private String updateUserCode;
	private Date updateTime;

	public String getNoticId() {
		return noticId;
	}
	public void setNoticId(String noticId) {
		this.noticId = noticId;
	}
	public void setUserId(String userId){
	this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setUpdateUserCode(String updateUserCode){
	this.updateUserCode=updateUserCode;
	}
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	public void setUpdateTime(Date updateTime){
	this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}

}

