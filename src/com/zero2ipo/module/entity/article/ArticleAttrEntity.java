package com.zero2ipo.module.entity.article;

   /**
    * cfjArticleAttr 实体类
    * Fri Oct 17 15:54:48 GMT+08:00 2014 zhengyunfei
    */ 


public class ArticleAttrEntity{
	private String id;
	private String articleid;
	private String articleKey;
	private String articleValue;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setArticleid(String articleid){
	this.articleid=articleid;
	}
	public String getArticleid(){
		return articleid;
	}
	public String getArticleKey() {
		return articleKey;
	}
	public void setArticleKey(String articleKey) {
		this.articleKey = articleKey;
	}
	public String getArticleValue() {
		return articleValue;
	}
	public void setArticleValue(String articleValue) {
		this.articleValue = articleValue;
	}
	

}

