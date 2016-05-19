package com.zero2ipo.common.entity;

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
	private String beforePhoto;
	private String afterPhoto;
	private int returnCode;
	private String returnMsg;

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	/**
	 * 扩展信息
	 * @return
	 */
	private String name;
	private String mobile;
	private String carNo;
	private String preTime;
	private String sendOrderTime;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getPreTime() {
		return preTime;
	}

	public void setPreTime(String preTime) {
		this.preTime = preTime;
	}

	public String getSendOrderTime() {
		return sendOrderTime;
	}

	public void setSendOrderTime(String sendOrderTime) {
		this.sendOrderTime = sendOrderTime;
	}

	/**扩展信息*/


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
		return status;
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
