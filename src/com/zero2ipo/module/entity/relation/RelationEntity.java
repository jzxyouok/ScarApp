package com.zero2ipo.module.entity.relation;

/**
 * 基金关系表实体类
 * 
 * @author liyang
 *
 */
public class RelationEntity {

	private String relaId; 					// relaUUID
	private String productFortuneId; 		// 产品或基金UUID
	private String productType;			 	// 基金或产品(PRODUCT 产品 FORTUNE 基金)
	private String projectId; 				// 项目UUID
	private String inatitutionsId; 			// 机构UUID
//	private String adminId; 				// 管理人UUID
	private String investorId;				// 天使投资人UUID
	private String codeId; 					// 类别UUID

	public String getRelaId() {
		  return relaId;   
	}

	public void setRelaId(String relaId) {
		this.relaId = relaId;
	}

	public String getProductFortuneId() {
		return productFortuneId;
	}

	public void setProductFortuneId(String productFortuneId) {
		this.productFortuneId = productFortuneId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

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

//	public String getAdminId() {
//		return adminId;
//	}
//
//	public void setAdminId(String adminId) {
//		this.adminId = adminId;
//	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

}