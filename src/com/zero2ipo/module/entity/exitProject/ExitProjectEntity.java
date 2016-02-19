package com.zero2ipo.module.entity.exitProject;

import com.zero2ipo.module.entity.relation.RelationEntity;

/**
 * cfjProjects 实体类 Fri Oct 10 16:39:29 GMT+08:00 2014 yangxn
 */

public class ExitProjectEntity {
	private String projectId;
	private String inatitutionsId;
	private String enterpriseName;
	private String industryClassification;
	private String exitWay;
	private String returnValue;
	private String outTime;
	private String createUserCode;
	private String createTime;
	private String updateUserCode;
	private String updateTime;

	private RelationEntity relation;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getInatitutionsId() {
		return inatitutionsId;
	}

	public void setInatitutionsId(String inatitutionsId) {
		this.inatitutionsId = inatitutionsId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getIndustryClassification() {
		return industryClassification;
	}

	public void setIndustryClassification(String industryClassification) {
		this.industryClassification = industryClassification;
	}
	public String getExitWay() {
		return exitWay;
	}

	public void setExitWay(String exitWay) {
		this.exitWay = exitWay;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public RelationEntity getRelation() {
		return relation;
	}

	public void setRelation(RelationEntity relation) {
		this.relation = relation;
	}

}
