package com.zero2ipo.module.entity.vcode;

/**
 * 
 * 发送邮件的随机码
 * @author liyang
 *
 */
public class VcodeEntity {

	private int codeId;

	private String userId;

	private String emailMobileOther;

	private String randomMd5;

	private String expireTime;

	private String channelType;

	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailMobileOther() {
		return emailMobileOther;
	}

	public void setEmailMobileOther(String emailMobileOther) {
		this.emailMobileOther = emailMobileOther;
	}

	public String getRandomMd5() {
		return randomMd5;
	}

	public void setRandomMd5(String randomMd5) {
		this.randomMd5 = randomMd5;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

}
