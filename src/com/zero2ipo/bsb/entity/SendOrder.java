package com.zero2ipo.bsb.entity;

import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.user.bo.User;

import java.io.Serializable;

/**
 * @title 派单实体类
 * @description: 系统派单实体对象类，对应数据库中的bsb_send_order表。
 * @author wangli
 * 
 */
public class SendOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String orderId;//订单ID
	private String userId;//员工ID
	private String content;//工作内容
	private String operatorDate;//派单时间
	private String operatorId;//派单人
	private String status;//完成情况 0表示未完成，1表示已完成
	private String finishDate;// 完成时间
	private String beforePhoto;//洗车前照片
	private String afterPhoto;//洗车后照片

	public String getBeforePhoto() {
		return beforePhoto;
	}
	public void setBeforePhoto(String beforePhoto) {
		this.beforePhoto = beforePhoto;
	}
	public String getAfterPhoto() {
		return afterPhoto;
	}
	public void setAfterPhoto(String afterPhoto) {
		this.afterPhoto = afterPhoto;
	}
	private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(String operatorDate) {
		this.operatorDate = operatorDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		if(StringUtil.isNullOrEmpty(status)){
			return "0";
		}else{
			return status;
		}

	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	

	
	
}
