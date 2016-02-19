package com.zero2ipo.module.entity.fund;

import com.zero2ipo.module.entity.relation.RelationEntity;


/**
 * 基金实体类 对应基金数据表（cfj_fund_detaile）
 * 
 * @author liyang
 *
 */
public class FundEntity { 
	private String fundId; 						// 主键
//	private String adminId;						//管理人ID
//	private String inatitutionsId;				//机构ID
//	private String productType;					//产品分类
	private String fundName; 					// 基金名称
	private String fundByname;
	private String organizationalForm; 			// 组织形式
	private double fundSize;					// 基金规模
	private String gp;							//GP出资
	private String duration; 					// 存续期
	private int durationSumyear;				// 存续期总年数  
	private double capitalContribution;			// 出资金额(个人)
	private double capitalContributionInatitutions;	// 出资金额(机构)	capitalContributionInatitutions
	private String fundMode; 					// 出资方式
	private String fundType; 					// 类基金类型
	private double expectedReturn; 				// 预期收益率
	private String investmentField; 			// 投资领域
	private String managementFee;	 			// 管理费
	private String collectFees; 				// 募集费用
	private String priorityYield; 				// 优先收益率
	private String incomeDistribution; 			// 收益分配
	private String forArchitecture; 			// 出资架构
	private String investmentStage; 			// 投资阶段
	private String investmentRestrictions; 		// 投资限制
//	private String project;
	private String url;
	private double risk;
	private String createRaise;
	private String overRaise;
	private double toRaise;
	private String recommended;
	private String location;
	private String pro1; 						// 增加属性
	private String status; 						// 基金状态
	private String description;
	private String keyWorld;
	
	private String createUserCode; 				// 创建人
	private String createTime; 					// 创建日期
	private String updateUserCode; 				// 修改人
	private String updateTime; 					// 修改日期
	private String basicStrategy;
	private int fundViews;
	private String cfjOption;//财富街观点
	private String remark;//备注

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCfjOption() {
		return cfjOption;
	}

	public void setCfjOption(String cfjOption) {
		this.cfjOption = cfjOption;
	}

	private RelationEntity relation;

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public double getFundSize() {
		return fundSize;
	}

	public void setFundSize(double fundSize) {
		this.fundSize = fundSize;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getDurationSumyear() {
		return durationSumyear;
	}

	public void setDurationSumyear(int durationSumyear) {
		this.durationSumyear = durationSumyear;
	}

	public String getFundMode() {
		return fundMode;
	}

	public void setFundMode(String fundMode) {
		this.fundMode = fundMode;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getInvestmentField() {
		return investmentField;
	}

	public void setInvestmentField(String investmentField) {
		this.investmentField = investmentField;
	}

	public double getCapitalContribution() {
		return capitalContribution;
	}

	public void setCapitalContribution(double capitalContribution) {
		this.capitalContribution = capitalContribution;
	}

	public double getExpectedReturn() {
		return expectedReturn;
	}

	public void setExpectedReturn(double expectedReturn) {
		this.expectedReturn = expectedReturn;
	}

	public String getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(String managementFee) {
		this.managementFee = managementFee;
	}

	public String getCollectFees() {
		return collectFees;
	}

	public void setCollectFees(String collectFees) {
		this.collectFees = collectFees;
	}

	public String getPriorityYield() {
		return priorityYield;
	}

	public void setPriorityYield(String priorityYield) {
		this.priorityYield = priorityYield;
	}

	public String getIncomeDistribution() {
		return incomeDistribution;
	}

	public void setIncomeDistribution(String incomeDistribution) {
		this.incomeDistribution = incomeDistribution;
	}

	public String getForArchitecture() {
		return forArchitecture;
	}

	public void setForArchitecture(String forArchitecture) {
		this.forArchitecture = forArchitecture;
	}

	public String getInvestmentStage() {
		return investmentStage;
	}

	public void setInvestmentStage(String investmentStage) {
		this.investmentStage = investmentStage;
	}

	public String getInvestmentRestrictions() {
		return investmentRestrictions;
	}

	public void setInvestmentRestrictions(String investmentRestrictions) {
		this.investmentRestrictions = investmentRestrictions;
	}

	public String getPro1() {
		return pro1;
	}

	public void setPro1(String pro1) {
		this.pro1 = pro1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}


	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

//	public String getProject() {
//		return project;
//	}
//
//	public void setProject(String project) {
//		this.project = project;
//	}

//	public String getAdminId() {
//		return adminId;
//	}
//
//	public void setAdminId(String adminId) {
//		this.adminId = adminId;
//	}
//
//	public String getInatitutionsId() {
//		return inatitutionsId;
//	}
//
//	public void setInatitutionsId(String inatitutionsId) {
//		this.inatitutionsId = inatitutionsId;
//	}

	public String getOrganizationalForm() {
		return organizationalForm;
	}

	public void setOrganizationalForm(String organizationalForm) {
		this.organizationalForm = organizationalForm;
	}

//	public String getProductType() {
//		return productType;
//	}
//
//	public void setProductType(String productType) {
//		this.productType = productType;
//	}

	public double getCapitalContributionInatitutions() {
		return capitalContributionInatitutions;
	}

	public void setCapitalContributionInatitutions(
			double capitalContributionInatitutions) {
		this.capitalContributionInatitutions = capitalContributionInatitutions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public double getRisk() {
		return risk;
	}

	public void setRisk(double risk) {
		this.risk = risk;
	}

	public double getToRaise() {
		return toRaise;
	}

	public void setToRaise(double toRaise) {
		this.toRaise = toRaise;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getGp() {
		return gp;
	}

	public void setGp(String gp) {
		this.gp = gp;
	}

	public int getFundViews() {
		return fundViews;
	}

	public void setFundViews(int fundViews) {
		this.fundViews = fundViews;
	}

	public String getFundByname() {
		return fundByname;
	}

	public void setFundByname(String fundByname) {
		this.fundByname = fundByname;
	}

	public String getBasicStrategy() {
		return basicStrategy;
	}

	public void setBasicStrategy(String basicStrategy) {
		this.basicStrategy = basicStrategy;
	}

	public String getKeyWorld() {
		return keyWorld;
	}

	public void setKeyWorld(String keyWorld) {
		this.keyWorld = keyWorld;
	}
	
}
