package com.zero2ipo.module.entity.article;

   /**
    * cfjArticleAttach 实体类
    * Fri Oct 17 15:55:25 GMT+08:00 2014 zhengyunfei
    */ 


public class ArticleAttachEntity{
	private String id;
	private String articleId;
	private String title;
	private String filePath;
	private String uploadAuthor;
	private String uploadTime;
	private String viewName;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}

	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public void setTitle(String title){
	this.title=title;
	}
	public String getTitle(){
		return title;
	}

	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUploadAuthor() {
		return uploadAuthor;
	}
	public void setUploadAuthor(String uploadAuthor) {
		this.uploadAuthor = uploadAuthor;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

}

