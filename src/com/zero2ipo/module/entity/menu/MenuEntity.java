package com.zero2ipo.module.entity.menu;

import java.util.List;

/**
 * �˵���
 * @author liyang
 *
 */
public class MenuEntity {
	
	private String menuId;				
	private String menuCode;			
	private String menuName;			
	private String menuTitle;			
	private long pmenuCode;			
	private String menuFolderFlag;		 
	private String handleSort;			
	private String handleRepresent;		
	private int sortNo;					
	private int menuLevel;				
	private String pathCode;			
	private String isActive;			
	private String addUser;				
	private String addTime;				
	private String remark;				
	private String menuImg;
	private List<MenuEntity> menuItems;
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public long getPmenuCode() {
		return pmenuCode;
	}

	public void setPmenuCode(long pmenuCode) {
		this.pmenuCode = pmenuCode;
	}

	public String getMenuFolderFlag() {
		return menuFolderFlag;
	}

	public void setMenuFolderFlag(String menuFolderFlag) {
		this.menuFolderFlag = menuFolderFlag;
	}

	public String getHandleSort() {
		return handleSort;
	}

	public void setHandleSort(String handleSort) {
		this.handleSort = handleSort;
	}

	public String getHandleRepresent() {
		return handleRepresent;
	}

	public void setHandleRepresent(String handleRepresent) {
		this.handleRepresent = handleRepresent;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getPathCode() {
		return pathCode;
	}

	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	public List<MenuEntity> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuEntity> menuItems) {
		this.menuItems = menuItems;
	}

}
