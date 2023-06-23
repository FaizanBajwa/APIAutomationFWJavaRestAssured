package com.restassured.models.common;

public class PayLoad {

	private String userPassword;
	private String userName;
	private String tableName;
	private String formName;
	private String requestType;
	private String isActive;
	private long mcConfigId;
		
	
	public long getMcConfigId() {
		return mcConfigId;
	}
	public void setMcConfigId(long mcConfigId) {
		this.mcConfigId = mcConfigId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
