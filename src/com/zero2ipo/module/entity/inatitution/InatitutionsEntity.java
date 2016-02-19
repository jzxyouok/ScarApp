package com.zero2ipo.module.entity.inatitution;
import java.util.Date;

   /**
    * cfjInatitutions 实体类
    * Fri Sep 12 15:31:44 GMT+08:00 2014 
    */ 


public class InatitutionsEntity{
	private String inatitutionsId;
	private String inatitutionName;
	private String shortName;
	private String startTime;
	private String managementCapital;
	private String inatitutionsType;
	private String introduce;
	private String headquarters;
	private String attachmentUrl;
	private String inatitutionsStatus;
	private String createUserCode;
	private String createTime;
	private String updateUserCode;
	private String updateTime;

	public String getInatitutionsId() {
		return inatitutionsId;
	}
	public void setInatitutionsId(String inatitutionsId) {
		this.inatitutionsId = inatitutionsId;
	}
	public String getInatitutionName() {
		return inatitutionName;
	}
	public void setInatitutionName(String inatitutionName) {
		this.inatitutionName = inatitutionName;
	}
	public void setShortName(String shortName){
	this.shortName=shortName;
	}
	public String getShortName(){
		return shortName;
	}
	public void setManagementCapital(String managementCapital){
	this.managementCapital=managementCapital;
	}
	public String getManagementCapital(){
		return managementCapital;
	}
	public void setInatitutionsType(String inatitutionsType){
	this.inatitutionsType=inatitutionsType;
	}
	public String getInatitutionsType(){
		return inatitutionsType;
	}
	public void setIntroduce(String introduce){
	this.introduce=introduce;
	}
	public String getIntroduce(){
		return introduce;
	}
	public void setHeadquarters(String headquarters){
	this.headquarters=headquarters;
	}
	public String getHeadquarters(){
		return headquarters;
	}
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	public void setCreateUserCode(String createUserCode){
		this.createUserCode=createUserCode;
	}
	public String getCreateUserCode(){
		return createUserCode;
	}
	public void setUpdateUserCode(String updateUserCode){
	this.updateUserCode=updateUserCode;
	}
	public String getUpdateUserCode(){
		return updateUserCode;
	}	
	public String getInatitutionsStatus() {
		return inatitutionsStatus;
	}
	public void setInatitutionsStatus(String inatitutionsStatus) {
		this.inatitutionsStatus = inatitutionsStatus;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}

