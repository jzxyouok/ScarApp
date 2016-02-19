package com.zero2ipo.module.entity.typeValue;

   /**
    * cfjTypeValue 实体类
    * Mon Oct 20 15:10:00 GMT+08:00 2014 yangxn
    */ 


public class TypeValueEntity{
	private String id;
	private String sysCode;
	private String value;
	private String codeType;

	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setSysCode(String sysCode){
	this.sysCode=sysCode;
	}
	public String getSysCode(){
		return sysCode;
	}
	public void setValue(String value){
	this.value=value;
	}
	public String getValue(){
		return value;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
}

