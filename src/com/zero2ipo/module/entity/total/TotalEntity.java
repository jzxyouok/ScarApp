package com.zero2ipo.module.entity.total;
import java.util.Date;

   /**
    * cfjTotalData 实体类
    * Thu Oct 09 16:21:35 GMT+08:00 2014 yangxn
    */ 


public class TotalEntity{
	private String totalId;
	private double money;
	private double turnover;
	private double cycle;
	private double cfTotal;
	private double secondaryMarketTotal;
	private String createUserCode;
	private Date createTime;
	private String updateUserCode;
	private Date updateTime;
	
	public String getTotalId() {
		return totalId;
	}
	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}
	public void setMoney(double money){
	this.money=money;
	}
	public double getMoney(){
		return money;
	}
	public void setTurnover(double turnover){
	this.turnover=turnover;
	}
	public double getTurnover(){
		return turnover;
	}
	public void setCycle(double cycle){
	this.cycle=cycle;
	}
	public double getCycle(){
		return cycle;
	}
	public void setCreateUserCode(String createUserCode){
	this.createUserCode=createUserCode;
	}
	public String getCreateUserCode(){
		return createUserCode;
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
	public double getCfTotal() {
		return cfTotal;
	}
	public void setCfTotal(double cfTotal) {
		this.cfTotal = cfTotal;
	}
	public double getSecondaryMarketTotal() {
		return secondaryMarketTotal;
	}
	public void setSecondaryMarketTotal(double secondaryMarketTotal) {
		this.secondaryMarketTotal = secondaryMarketTotal;
	}

}

