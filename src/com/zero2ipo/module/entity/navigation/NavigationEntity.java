package com.zero2ipo.module.entity.navigation;

/**
 * 导航实体类
 * @author liyang
 * @date 2015-04-22
 *
 */
public class NavigationEntity {

	private int navigationId;

	private String navigationName;

	private int navigationOrder;

	private String navigationUse;

	private String navigationState;
	
	private String navigationIp;
	
	private String navigationPort;
	
	private String navigationUrl;

	private int navigationParent;

	public int getNavigationId() {
		return navigationId;
	}

	public void setNavigationId(int navigationId) {
		this.navigationId = navigationId;
	}

	public String getNavigationName() {
		return navigationName;
	}

	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}

	public int getNavigationOrder() {
		return navigationOrder;
	}

	public void setNavigationOrder(int navigationOrder) {
		this.navigationOrder = navigationOrder;
	}

	public String getNavigationUse() {
		return navigationUse;
	}

	public void setNavigationUse(String navigationUse) {
		this.navigationUse = navigationUse;
	}

	public String getNavigationState() {
		return navigationState;
	}

	public void setNavigationState(String navigationState) {
		this.navigationState = navigationState;
	}

	public int getNavigationParent() {
		return navigationParent;
	}

	public void setNavigationParent(int navigationParent) {
		this.navigationParent = navigationParent;
	}

	public String getNavigationIp() {
		return navigationIp;
	}

	public void setNavigationIp(String navigationIp) {
		this.navigationIp = navigationIp;
	}

	public String getNavigationPort() {
		return navigationPort;
	}

	public void setNavigationPort(String navigationPort) {
		this.navigationPort = navigationPort;
	}

	public String getNavigationUrl() {
		return navigationUrl;
	}

	public void setNavigationUrl(String navigationUrl) {
		this.navigationUrl = navigationUrl;
	}
	
}
