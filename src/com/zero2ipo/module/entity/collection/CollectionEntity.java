package com.zero2ipo.module.entity.collection;
import java.util.Date;

   /**
    *  表: cfj_collection  实体类
    *  收藏功能
    * Thu Apr 30 11:03:24 GMT+08:00 2015 郑云飞
    */ 
public class CollectionEntity{
	private String id;
	private String userId;
	private String objectId;
	private String type;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setUserId(String userId){
	this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	public void setObjectId(String objectId){
	this.objectId=objectId;
	}
	public String getObjectId(){
		return objectId;
	}
	public void setType(String type){
	this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setCreateUser(String createUser){
	this.createUser=createUser;
	}
	public String getCreateUser(){
		return createUser;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setUpdateUser(String updateUser){
	this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return updateUser;
	}
	public void setUpdateTime(Date updateTime){
	this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	
}

