package com.zero2ipo.module.entity.area;


   /**
    * cfjProvCityAreaStreet 实体类
    * Mon Jan 26 11:42:23 GMT+08:00 2015 zhengyunfei
    */ 


public class AreaEntity{
	private String id;
	private String code;
	private String parentId;
	private String name;
	private byte level;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setCode(String code){
	this.code=code;
	}
	public String getCode(){
		return code;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setLevel(byte level){
	this.level=level;
	}
	public byte getLevel(){
		return level;
	}
}

