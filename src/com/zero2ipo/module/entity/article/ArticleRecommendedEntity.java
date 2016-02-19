package com.zero2ipo.module.entity.article;

   /**
    * cfjArticleRecommended 实体类
    * Mon May 18 10:11:52 GMT+08:00 2015 郑云飞
    */ 


public class ArticleRecommendedEntity{
	private String id;
	private String sortId;
	private String recommendedImage;
	private String url;
	private String title;
	private String createDate;
	private String remark;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setSortId(String sortId){
	this.sortId=sortId;
	}
	public String getSortId(){
		return sortId;
	}
	public void setRecommendedImage(String recommendedImage){
	this.recommendedImage=recommendedImage;
	}
	public String getRecommendedImage(){
		return recommendedImage;
	}
	public void setUrl(String url){
	this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setTitle(String title){
	this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setCreateDate(String createDate){
	this.createDate=createDate;
	}
	public String getCreateDate(){
		return createDate;
	}
	public void setRemark(String remark){
	this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

}

