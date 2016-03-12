package com.zero2ipo.eeh.article.bo;
/**
    * eehArticle 实体类
    * Fri Feb 26 14:23:18 GMT+08:00 2016
    */
public class ArticleBo{
	private int id;
	private String thumbnail;
	private String type;
	private String content;
	private String title;
	private String time;
	private long articleId;
    private String  gradeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setThumbnail(String thumbnail){
	this.thumbnail=thumbnail;
	}
	public String getThumbnail(){
		return thumbnail;
	}
	public void setType(String type){
	this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setContent(String content){
	this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setTitle(String title){
	this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setTime(String time){
	this.time=time;
	}
	public String getTime(){
		return time.replace(".0","");
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
}

