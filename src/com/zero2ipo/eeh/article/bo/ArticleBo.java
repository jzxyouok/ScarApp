package com.zero2ipo.eeh.article.bo;
/**
    * eehArticle 实体类
    * Fri Feb 26 14:23:18 GMT+08:00 2016
    */
public class ArticleBo{
	private int id;
	private String thumbnail;//图片地址
	private String type;//类型 1 通知2公告3班级风采4班级明星
	private String content;//内容
	private String title;//标题
	private String time;//时间
	private long articleId;//主键
    private String  gradeName;//班级名称
    private String  motto;//座右铭
    private String  remark;//备注

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

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

