package com.zero2ipo.module.entity.product;

import java.util.Date;

import com.zero2ipo.module.entity.relation.RelationEntity;

/**
 * 管理人表（cfj_fund_detaile）
 * 
 * @author liyang
 *
 */

public class ProductEntity {
	private String productId;
	private String inatitutionsId;
	private String adminId;
	private String productName;
	private String productByname;
	private String fundCompany;
	private String productClassfy;
	private String gradingScale;
	private String custodianBanks;
	private String investmentAdviser;
	private String investmentTarget;
	private int productDeadline;
	private double productSize;
	private double startingPointCompany;
	private double startingPointPerson;
	private double subscriptionFee;
	private double managementFee;
	private String beneficiary;
	private String controlMeasures;
	private String purchase;
	private String portfolioInvestment;
	private String safetyCushion;
	private String importantTime;
	private String pro1;
	private String url;
	private String location;
	private String status;
	private String managersPaid;
	private String incomeDistribution;
	private String earningsEstimates;
	private String currencyType;
	
	private double profit;
	private double raise;
	private String createRaise;
	private String overRaise;
	private String investmentField;
	private String reason;
	private String description;
	private String revenueType; 

	private String createUserCode;
	private Date createTime;
	private String updateUserCode;
	private Date updateTime;
	
	private int productViews;

	private RelationEntity relation;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getInatitutionsId() {
		return inatitutionsId;
	}

	public void setInatitutionsId(String inatitutionsId) {
		this.inatitutionsId = inatitutionsId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getGradingScale() {
		return gradingScale;
	}

	public void setGradingScale(String gradingScale) {
		this.gradingScale = gradingScale;
	}

	public String getCustodianBanks() {
		return custodianBanks;
	}

	public void setCustodianBanks(String custodianBanks) {
		this.custodianBanks = custodianBanks;
	}

	public String getInvestmentAdviser() {
		return investmentAdviser;
	}

	public void setInvestmentAdviser(String investmentAdviser) {
		this.investmentAdviser = investmentAdviser;
	}

	public String getInvestmentTarget() {
		return investmentTarget;
	}

	public void setInvestmentTarget(String investmentTarget) {
		this.investmentTarget = investmentTarget;
	}

	public int getProductDeadline() {
		return productDeadline;
	}

	public void setProductDeadline(int productDeadline) {
		this.productDeadline = productDeadline;
	}

	public double getProductSize() {
		return productSize;
	}

	public void setProductSize(double productSize) {
		this.productSize = productSize;
	}

	public double getStartingPointCompany() {
		return startingPointCompany;
	}

	public void setStartingPointCompany(double startingPointCompany) {
		this.startingPointCompany = startingPointCompany;
	}

	public double getStartingPointPerson() {
		return startingPointPerson;
	}

	public void setStartingPointPerson(double startingPointPerson) {
		this.startingPointPerson = startingPointPerson;
	}

	public double getSubscriptionFee() {
		return subscriptionFee;
	}

	public void setSubscriptionFee(double subscriptionFee) {
		this.subscriptionFee = subscriptionFee;
	}

	public double getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(double managementFee) {
		this.managementFee = managementFee;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getControlMeasures() {
		return controlMeasures;
	}

	public void setControlMeasures(String controlMeasures) {
		this.controlMeasures = controlMeasures;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

	public String getPortfolioInvestment() {
		return portfolioInvestment;
	}

	public void setPortfolioInvestment(String portfolioInvestment) {
		this.portfolioInvestment = portfolioInvestment;
	}

	public String getSafetyCushion() {
		return safetyCushion;
	}

	public void setSafetyCushion(String safetyCushion) {
		this.safetyCushion = safetyCushion;
	}

	public String getImportantTime() {
		return importantTime;
	}

	public void setImportantTime(String importantTime) {
		this.importantTime = importantTime;
	}

	public String getPro1() {
		return pro1;
	}

	public void setPro1(String pro1) {
		this.pro1 = pro1;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getRaise() {
		return raise;
	}

	public void setRaise(double raise) {
		this.raise = raise;
	}

	public String getInvestmentField() {
		return investmentField;
	}

	public void setInvestmentField(String investmentField) {
		this.investmentField = investmentField;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RelationEntity getRelation() {
		return relation;
	}

	public void setRelation(RelationEntity relation) {
		this.relation = relation;
	}

	public String getProductClassfy() {
		return productClassfy;
	}

	public void setProductClassfy(String productClassfy) {
		this.productClassfy = productClassfy;
	}

	public String getRevenueType() {
		return revenueType;
	}

	public void setRevenueType(String revenueType) {
		this.revenueType = revenueType;
	}

	public String getCreateRaise() {
		return createRaise;
	}

	public void setCreateRaise(String createRaise) {
		this.createRaise = createRaise;
	}

	public String getOverRaise() {
		return overRaise;
	}

	public void setOverRaise(String overRaise) {
		this.overRaise = overRaise;
	}

	public String getManagersPaid() {
		return managersPaid;
	}

	public void setManagersPaid(String managersPaid) {
		this.managersPaid = managersPaid;
	}

	public String getIncomeDistribution() {
		return incomeDistribution;
	}

	public void setIncomeDistribution(String incomeDistribution) {
		this.incomeDistribution = incomeDistribution;
	}

	public String getEarningsEstimates() {
		return earningsEstimates;
	}

	public void setEarningsEstimates(String earningsEstimates) {
		this.earningsEstimates = earningsEstimates;
	}

	public int getProductViews() {
		return productViews;
	}

	public void setProductViews(int productViews) {
		this.productViews = productViews;
	}

	public String getProductByname() {
		return productByname;
	}

	public void setProductByname(String productByname) {
		this.productByname = productByname;
	}

	public String getFundCompany() {
		return fundCompany;
	}

	public void setFundCompany(String fundCompany) {
		this.fundCompany = fundCompany;
	}
	
}
