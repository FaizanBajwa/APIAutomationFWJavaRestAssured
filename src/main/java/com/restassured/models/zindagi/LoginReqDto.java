package com.restassured.models.zindagi;


public class LoginReqDto {
	
	private Integer buildVersion;
    private String androidVersion;
    private String appVersion;
    private String deviceName;
    private Boolean enableFingerPrint;
    private String firebaseToken;
    private String imei;
    private String mobileNo;
    private String otpPin;

    public Integer getBuildVersion() {
		return buildVersion;
	}
	public void setBuildVersion(Integer buildVersion) {
		this.buildVersion = buildVersion;
	}
	public String getAndroidVersion() {
		return androidVersion;
	}
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Boolean getEnableFingerPrint() {
		return enableFingerPrint;
	}
	public void setEnableFingerPrint(Boolean enableFingerPrint) {
		this.enableFingerPrint = enableFingerPrint;
	}
	public String getFirebaseToken() {
		return firebaseToken;
	}
	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getOtpPin() {
		return otpPin;
	}
	public void setOtpPin(String otpPin) {
		this.otpPin = otpPin;
	}
		
}
