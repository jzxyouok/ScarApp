package com.zero2ipo.module.entity.telMessage;

public class TelMessageEntity {
	
	private String msgId;

	private String tel;

	private String msgTime;

	private String content;

	private String typy;

	private String returnCode;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTypy() {
		return typy;
	}

	public void setTypy(String typy) {
		this.typy = typy;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
