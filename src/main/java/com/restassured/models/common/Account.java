package com.restassured.models.common;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private String iban;
	private String bban;
	private String pan;
	private String currency;
	private String msidn;
	
	public String getMsidn() {
		return msidn;
	}
	public void setMsidn(String msidn) {
		this.msidn = msidn;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getBban() {
		return bban;
	}
	public void setBban(String bban) {
		this.bban = bban;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
