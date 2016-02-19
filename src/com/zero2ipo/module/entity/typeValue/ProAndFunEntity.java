package com.zero2ipo.module.entity.typeValue;

public class ProAndFunEntity {

	private String id;
	private String name;
	private String typeValue;
	private int deadline;
	private double profit;
	private String investmentField;
	private double pointCompany;
	private double pointPerson;
	private String reason;
	private double raise;
	private String currencyType;
	private String text;
	private String mindata;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.text = name;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public String getInvestmentField() {
		return investmentField;
	}
	public void setInvestmentField(String investmentField) {
		this.investmentField = investmentField;
	}
	public double getPointCompany() {
		return pointCompany;
	}
	public void setPointCompany(double pointCompany) {
		this.pointCompany = pointCompany;
	}
	public double getPointPerson() {
		return pointPerson;
	}
	public void setPointPerson(double pointPerson) {
		this.pointPerson = pointPerson;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public double getRaise() {
		return raise;
	}
	public void setRaise(double raise) {
		this.raise = raise;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getText() {
		if("PRODUCT".equals(this.typeValue)){
			text=text+"(产品)";
		}else{
			text=text+"(基金)";
		}
		return text;
	}
	public String getMindata() {
		if(this.typeValue=="PRODUCT"){
			return String.valueOf(this.pointPerson);
		}else{
			if(this.pointCompany-this.pointPerson>0){
				return String.valueOf(this.pointPerson);
			}else{
				return String.valueOf(this.pointCompany);
			}
		}
	}
	
	
}
