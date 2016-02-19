package com.zero2ipo.common.entity.app;

   /**
    * goodses 实体类
    * Sat Dec 19 18:43:43 GMT+08:00 2015 郑云飞
    */ 


public class Goodses{
	private long id;
	private String name;
	private int carPrice;
	private int jeepPrice;
	private String info;
	private String url;
	private int status;
	private String createTime;
	private String extra;
	private String cardList;
	public void setId(long id){
	this.id=id;
	}
	public long getId(){
		return id;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setCarPrice(int carPrice){
	this.carPrice=carPrice;
	}
	public int getCarPrice(){
		return carPrice;
	}
	public void setJeepPrice(int jeepPrice){
	this.jeepPrice=jeepPrice;
	}
	public int getJeepPrice(){
		return jeepPrice;
	}
	public void setInfo(String info){
	this.info=info;
	}
	public String getInfo(){
		return info;
	}
	public void setUrl(String url){
	this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setStatus(int status){
	this.status=status;
	}
	public int getStatus(){
		return status;
	}
	public void setCreateTime(String createTime){
	this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setExtra(String extra){
	this.extra=extra;
	}
	public String getExtra(){
		return extra;
	}
	public void setCardList(String cardList){
	this.cardList=cardList;
	}
	public String getCardList(){
		return cardList;
	}

}

