package com.restassured.models.limits;

import java.util.List;

public class Data {
	
	private Security security;
	private Account account;
	private String channel;
	private String terminal;
	private String reterivalReferenceNumber;
	private PayLoad payLoad;
	private List<AdditionalInformation> additionalInformation;
	private String checkSum;
	
	
	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getReterivalReferenceNumber() {
		return reterivalReferenceNumber;
	}
	public void setReterivalReferenceNumber(String reterivalReferenceNumber) {
		this.reterivalReferenceNumber = reterivalReferenceNumber;
	}
	public PayLoad getPayLoad() {
		return payLoad;
	}
	public void setPayLoad(PayLoad payLoad) {
		this.payLoad = payLoad;
	}
	public List<AdditionalInformation> getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(List<AdditionalInformation> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public String getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
}
