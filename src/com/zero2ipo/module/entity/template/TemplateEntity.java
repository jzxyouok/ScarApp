package com.zero2ipo.module.entity.template;

/**
 * ģ��ʵ����
 * @author liyang
 *
 */
public class TemplateEntity {

	private int templateId;

	private String templateCode;

	private String templateName;

	private String templateAddress;

	private int templateOrder;

	private String templatePreview;
	
	private String templateType;
	
	private String templateFilter;

	private int isValid;

	private String createUserCode;

	private String createTime;

	private String updateUserCode;

	private String updateTime;

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateAddress() {
		return templateAddress;
	}

	public void setTemplateAddress(String templateAddress) {
		this.templateAddress = templateAddress;
	}

	public int getTemplateOrder() {
		return templateOrder;
	}

	public void setTemplateOrder(int templateOrder) {
		this.templateOrder = templateOrder;
	}

	public String getTemplatePreview() {
		return templatePreview;
	}

	public void setTemplatePreview(String templatePreview) {
		this.templatePreview = templatePreview;
	}
	
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateFilter() {
		return templateFilter;
	}

	public void setTemplateFilter(String templateFilter) {
		this.templateFilter = templateFilter;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
