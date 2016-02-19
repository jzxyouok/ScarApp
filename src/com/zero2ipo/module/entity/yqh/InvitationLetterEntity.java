package com.zero2ipo.module.entity.yqh;

   /**
    * cfjReserationManagement 实体类
    * Tue Apr 21 10:28:33 GMT+08:00 2015 郑云飞
    */ 


public class InvitationLetterEntity{
	private String id;
	private String name;//邀请名（30）
	private String shortName;//简称(10)
	private String title;//二级标题
	private String content;//内容
	private String area;//地点
	private String sendStartTime;//下发开始时间（以天为单位，保存00:00:00）
	private String sendEndTime;//下发结束时间(以天为单位,保存23:59:59)
	private String startTime;//开始时间
	private String createTime;//创建时间
	private String updateTime;//更新时间
    private String createUser;//创建人
    private String updateUser;//修改人
    private String shortImage;//缩略图
    private String url;
    
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSendStartTime() {
		return sendStartTime;
	}
	public void setSendStartTime(String sendStartTime) {
		this.sendStartTime = sendStartTime;
	}
	public String getSendEndTime() {
		return sendEndTime;
	}
	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getShortImage() {
		return shortImage;
	}
	public void setShortImage(String shortImage) {
		this.shortImage = shortImage;
	}
	
}

