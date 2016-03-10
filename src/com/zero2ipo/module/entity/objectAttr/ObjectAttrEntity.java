package com.zero2ipo.module.entity.objectAttr;


   /**
    * cfjObjectAttr 实体类
    * Fri Oct 24 14:19:29 GMT+08:00 2014 yangxn
    */ 


public class ObjectAttrEntity{
	private String id;
	private String objectId;
	private String objectType;
	private String objectPositionId;
	private String value;
	private String objectKey;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setObjectId(String objectId){
	this.objectId=objectId;
	}
	public String getObjectId(){
		return objectId;
	}
	public void setObjectType(String objectType){
	this.objectType=objectType;
	}
	public String getObjectType(){
		return objectType;
	}
	public String getObjectPositionId() {
		return objectPositionId;
	}
	public void setObjectPositionId(String objectPositionId) {
		this.objectPositionId = objectPositionId;
	}
	public void setValue(String value){
	this.value=value;
	}
	public String getValue(){
		return value;
	}
	public String getObjectKey() {
		return objectKey;
	}
	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	
}

