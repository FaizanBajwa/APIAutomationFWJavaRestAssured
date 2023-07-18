package com.restassured.models.limits;

import java.util.List;

public class PayLoad {

	private String userPassword;
	private String userName;
	private String tableName;
	private String formName;
	private String requestType;
	private String isActive;
	private String mcConfigId;	
	private String lkpAccountClassificationId;
	private String accountLevelName;
	private String kycSetHeadId;
	//private String dailyTransLimitDr;
	//private String dailyAmtLimitDr;
	//private String monthlyTransLimitDr;
	//private String monthlyAmtLimitDr;
	//private String yearlyTransLimitDr;
	//private String yearlyAmtLimitDr;
	//private String dailyTransLimitCr;
	//private String dailyAmtLimitCr;
	//private String monthlyTransLimitCr;
	//private String monthlyAmtLimitCr;
	//private String yearlyTransLimitCr;
	private String accountLevelId;
	private String accountClassificationId;
	
	//LIMITS
	private String lkpAccountLevelId;
	private String dailyAmtLimitCr;
	private String dailyAmtLimitDr;
	private String dailyTransLimitCr;
	private String dailyTransLimitDr;
	private String excludeLimit;
	private String limitProfileName;
	private String monthlyAmtLimitCr;
	private String monthlyAmtLimitDr;
	private String monthlyTransLimitCr;
	private String monthlyTransLimitDr;
	private String yearlyAmtLimitCr;
	private String yearlyAmtLimitDr;
	private String yearlyTransLimitCr;
	private String yearlyTransLimitDr;
	private String transLimitId;
	private List<tblTransLimitDetailRequestList> tblTransLimitDetailRequestList;
	
	private String dateFrom;
	private String dateTo;
	private String createdBy;
	private String updatedBy;
	private String statusId;
	
	
	public String getTranslimitid() {
		return transLimitId;
	}
	public void setTranslimitid(String translimitid) {
		transLimitId = translimitid;
	}
	public String getAccountClassificationId() {
		return accountClassificationId;
	}
	public void setAccountClassificationId(String accountClassificationId) {
		this.accountClassificationId = accountClassificationId;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	
	
	public String getAccountLevelId() {
		return accountLevelId;
	}
	public void setAccountLevelId(String accountLevelId) {
		this.accountLevelId = accountLevelId;
	}
	public String getLkpAccountClassificationId() {
		return lkpAccountClassificationId;
	}
	public void setLkpAccountClassificationId(String lkpAccountClassificationId) {
		this.lkpAccountClassificationId = lkpAccountClassificationId;
	}
	public String getAccountLevelName() {
		return accountLevelName;
	}
	public void setAccountLevelName(String accountLevelName) {
		this.accountLevelName = accountLevelName;
	}
	public String getKycSetHeadId() {
		return kycSetHeadId;
	}
	public void setKycSetHeadId(String kycSetHeadId) {
		this.kycSetHeadId = kycSetHeadId;
	}
	public String getDailyTransLimitDr() {
		return dailyTransLimitDr;
	}
	public void setDailyTransLimitDr(String dailyTransLimitDr) {
		this.dailyTransLimitDr = dailyTransLimitDr;
	}
	public String getDailyAmtLimitDr() {
		return dailyAmtLimitDr;
	}
	public void setDailyAmtLimitDr(String dailyAmtLimitDr) {
		this.dailyAmtLimitDr = dailyAmtLimitDr;
	}
	public String getMonthlyTransLimitDr() {
		return monthlyTransLimitDr;
	}
	public void setMonthlyTransLimitDr(String monthlyTransLimitDr) {
		this.monthlyTransLimitDr = monthlyTransLimitDr;
	}
	public String getMonthlyAmtLimitDr() {
		return monthlyAmtLimitDr;
	}
	public void setMonthlyAmtLimitDr(String monthlyAmtLimitDr) {
		this.monthlyAmtLimitDr = monthlyAmtLimitDr;
	}
	public String getYearlyTransLimitDr() {
		return yearlyTransLimitDr;
	}
	public void setYearlyTransLimitDr(String yearlyTransLimitDr) {
		this.yearlyTransLimitDr = yearlyTransLimitDr;
	}
	public String getYearlyAmtLimitDr() {
		return yearlyAmtLimitDr;
	}
	public void setYearlyAmtLimitDr(String yearlyAmtLimitDr) {
		this.yearlyAmtLimitDr = yearlyAmtLimitDr;
	}
	public String getDailyTransLimitCr() {
		return dailyTransLimitCr;
	}
	public void setDailyTransLimitCr(String dailyTransLimitCr) {
		this.dailyTransLimitCr = dailyTransLimitCr;
	}
	public String getDailyAmtLimitCr() {
		return dailyAmtLimitCr;
	}
	public void setDailyAmtLimitCr(String dailyAmtLimitCr) {
		this.dailyAmtLimitCr = dailyAmtLimitCr;
	}
	public String getMonthlyTransLimitCr() {
		return monthlyTransLimitCr;
	}
	public void setMonthlyTransLimitCr(String monthlyTransLimitCr) {
		this.monthlyTransLimitCr = monthlyTransLimitCr;
	}
	public String getMonthlyAmtLimitCr() {
		return monthlyAmtLimitCr;
	}
	public void setMonthlyAmtLimitCr(String monthlyAmtLimitCr) {
		this.monthlyAmtLimitCr = monthlyAmtLimitCr;
	}
	public String getYearlyTransLimitCr() {
		return yearlyTransLimitCr;
	}
	public void setYearlyTransLimitCr(String yearlyTransLimitCr) {
		this.yearlyTransLimitCr = yearlyTransLimitCr;
	}
	public String getYearlyAmtLimitCr() {
		return yearlyAmtLimitCr;
	}
	public void setYearlyAmtLimitCr(String yearlyAmtLimitCr) {
		this.yearlyAmtLimitCr = yearlyAmtLimitCr;
	}
	//private String yearlyAmtLimitCr;
	
		
	
	public String getMcConfigId() {
		return mcConfigId;
	}
	public String getLkpAccountLevelId() {
		return lkpAccountLevelId;
	}
	public void setLkpAccountLevelId(String lkpAccountLevelId) {
		this.lkpAccountLevelId = lkpAccountLevelId;
	}
	public String getExcludeLimit() {
		return excludeLimit;
	}
	public void setExcludeLimit(String excludeLimit) {
		this.excludeLimit = excludeLimit;
	}
	public String getLimitProfileName() {
		return limitProfileName;
	}
	public void setLimitProfileName(String limitProfileName) {
		this.limitProfileName = limitProfileName;
	}
	public List<tblTransLimitDetailRequestList> getTblTransLimitDetailRequestList() {
		return tblTransLimitDetailRequestList;
	}
	public void setTblTransLimitDetailRequestList(List<tblTransLimitDetailRequestList> tblTransLimitDetailRequestList) {
		this.tblTransLimitDetailRequestList = tblTransLimitDetailRequestList;
	}
	public void setMcConfigId(String mcConfigId) {
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
