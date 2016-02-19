package com.zero2ipo.module.entity.myMessage;
/**
 * 我的消息
 * table:cfj_user_message
 * @author ZhengYunfei
 *
 */
public class MyMessageEntity {
	private String id;
	private String userId;
	private String title;
	private String content;
	private String inputTime;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
