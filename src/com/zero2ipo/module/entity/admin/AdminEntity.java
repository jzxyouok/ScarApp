package com.zero2ipo.module.entity.admin;

/**
 * 管理人表（cfj_fund_detaile）
 * 
 * @author liyang
 *
 */

public class AdminEntity {
	private String adminId; 			// 主键
	private String adminName;		 	// 姓名
	private String position; 			// 职务
	private String introduce;			// 个人简介
	private String adminStatus;			//状态
	private String inatitutionsId;		//机构ID
	private String createUserCode;		// 创建人
	private String createTime; 			// 创建日期
	private String updateUserCode;	 	// 修改人
	private String updateTime; 			// 修改日期
	private String photo;//用户头像

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getInatitutionsId() {
		return inatitutionsId;
	}

	public void setInatitutionsId(String inatitutionsId) {
		this.inatitutionsId = inatitutionsId;
	}
}
