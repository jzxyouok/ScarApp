package com.zero2ipo.bsb.entity;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String userId;

	private String goodsId;
	private String carNum;
	private String carType;
	private String washType;
	private String address;
	private String lat;
	private String lon;
	private float price;
	private String discription;
	private String startPhoto;
	private String endPhoto;
	private String createTime;
	private String payTime;
	private String confirmTime;
	private String washTime;
	private String finishTime;
	private String comment;
	private String payType;
	private String orderStatus;
	private String confirmInfo;
	private String sendOrderToName;
	private String sendOrderStatus;
	private String mobile;
	private String carId;
	private Car car;
	private SendOrder sendOrder;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SendOrder getSendOrder() {
		return sendOrder;
	}

	public void setSendOrder(SendOrder sendOrder) {
		this.sendOrder = sendOrder;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSendOrderStatus() {
		return sendOrderStatus;
	}

	public void setSendOrderStatus(String sendOrderStatus) {
		this.sendOrderStatus = sendOrderStatus;
	}

	public String getSendOrderToName() {
		return sendOrderToName;
	}

	public void setSendOrderToName(String sendOrderToName) {
		this.sendOrderToName = sendOrderToName;
	}

	public void setOrderId(String orderId){
		this.orderId=orderId;
	}
	public String getOrderId(){
		return orderId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	public void setGoodsId(String goodsId){
		this.goodsId=goodsId;
	}
	public String getGoodsId(){
		return goodsId;
	}
	public void setCarNum(String carNum){
		this.carNum=carNum;
	}
	public String getCarNum(){
		return carNum;
	}
	public void setCarType(String carType){
		this.carType=carType;
	}
	public String getCarType(){
		return carType;
	}
	public void setWashType(String washType){
		this.washType=washType;
	}
	public String getWashType(){
		return washType;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setLat(String lat){
		this.lat=lat;
	}
	public String getLat(){
		return lat;
	}
	public void setLon(String lon){
		this.lon=lon;
	}
	public String getLon(){
		return lon;
	}
	public void setPrice(float price){
		this.price=price;
	}
	public float getPrice(){
		return price;
	}
	public void setDiscription(String discription){
		this.discription=discription;
	}
	public String getDiscription(){
		return discription;
	}
	public void setStartPhoto(String startPhoto){
		this.startPhoto=startPhoto;
	}
	public String getStartPhoto(){
		return startPhoto;
	}
	public void setEndPhoto(String endPhoto){
		this.endPhoto=endPhoto;
	}
	public String getEndPhoto(){
		return endPhoto;
	}
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setPayTime(String payTime){
		this.payTime=payTime;
	}
	public String getPayTime(){
		return payTime;
	}
	public void setConfirmTime(String confirmTime){
		this.confirmTime=confirmTime;
	}
	public String getConfirmTime(){
		return confirmTime;
	}
	public void setWashTime(String washTime){
		this.washTime=washTime;
	}
	public String getWashTime(){
		return washTime;
	}
	public void setFinishTime(String finishTime){
		this.finishTime=finishTime;
	}
	public String getFinishTime(){
		return finishTime;
	}
	public void setComment(String comment){
		this.comment=comment;
	}
	public String getComment(){
		return comment;
	}
	public void setPayType(String payType){
		this.payType=payType;
	}
	public String getPayType(){
		return payType;
	}
	public void setOrderStatus(String orderStatus){
		this.orderStatus=orderStatus;
	}
	public String getOrderStatus(){
		return orderStatus;
	}
	public void setConfirmInfo(String confirmInfo){
		this.confirmInfo=confirmInfo;
	}
	public String getConfirmInfo(){
		return confirmInfo;
	}

	
	
}
