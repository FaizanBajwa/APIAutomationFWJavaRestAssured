package com.restassured.models.headers;


public class HeadersModel {

	private String authorization;
	private String contentType;
	private String accept;
	private String userAgent;
	private String xRequestID;
	
	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getXRequestID() {
		return xRequestID;
	}

	public void setXRequestID(String xRequestID) {
		this.xRequestID = xRequestID;
	}
}
