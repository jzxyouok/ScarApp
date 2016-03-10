package com.zero2ipo.module.entity.article;


import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.framework.util.StringUtil;

/**
 * cfjArticle 实体类 Tue Jan 01 10:09:52 GMT+08:00 1980 郑云飞
 */

public class ArticleEntity {
	private String articleId;
	private String articleName;
	private String articleType;
	private String author;
	private String classification;
	private String content;
	private int location;
	private String createUserCode;
	private String createTime;
	private String updateUserCode;
	private String updateTime;
	private String articleTitle;
	private String sendTime;
	private String infoParam;
	private String articleSource;
	private String thumbnail;
	private String remark;
	private String keyword;
	private String description;
	private String articleTemplate;
	private int articleViews;
	private String mobileThumbnail ;
	private String expirationDate;
	
	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getMobileThumbnail() {
		return mobileThumbnail;
	}

	public void setMobileThumbnail(String mobileThumbnail) {
		this.mobileThumbnail = mobileThumbnail;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getClassification() {
		return classification;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getLocation() {
		return location;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleTitle() {
		return articleTitle;
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

	public String getSendTime() {
		if(!StringUtil.isNullOrEmpty(sendTime)){
			sendTime=DateUtil.strFormatDate(sendTime,DateUtil._DEFAULT2);
		}else{
			sendTime="";
		}
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getArticleTemplate() {
		return articleTemplate;
	}

	public void setArticleTemplate(String articleTemplate) {
		this.articleTemplate = articleTemplate;
	}

	public void setInfoParam(String infoParam) {
		this.infoParam = infoParam;
	}

	public String getInfoParam() {
		return infoParam;
	}

	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}

	public String getArticleSource() {
		return articleSource;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public int getArticleViews() {
		return articleViews;
	}

	public void setArticleViews(int articleViews) {
		this.articleViews = articleViews;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
