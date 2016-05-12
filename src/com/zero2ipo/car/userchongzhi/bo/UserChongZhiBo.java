package com.zero2ipo.car.userchongzhi.bo;
public class UserChongZhiBo implements java.io.Serializable{
	private long id;
	private String userId;
	private float money;
	private float zsmoney;
	private String createTime;
	private String empNo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public float getZsmoney() {
		return zsmoney;
	}

	public void setZsmoney(float zsmoney) {
		this.zsmoney = zsmoney;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
}

