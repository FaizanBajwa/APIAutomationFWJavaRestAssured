package com.restassured.constants;

public class ApiUrls {

	public static String baseUrl = "https://reqres.in";
	public static String baseAuth = "http://coop.apps.symfonycasts.com";
	public static String baseUserManagement = "http://10.0.0.13:8080/mfs_user_management/umngt";
	public static String ZINDAGI_BASE_URL = "http://zindigi-qa.appinsnap.com/1.0.28-android";
	public static String ZBOX_BASE_URL_QA = "http://10.0.1.4:8080";
	public static String ZBOX_BASE_URL_DEV = "http://10.0.0.13:8080";

	/*
	 * ---------------------------------- Demo API's--------------------------------------------
	 */
	public static final String baseAuthSec = "http://coop.apps.symfonycasts.com/api/";
	public static final String gettoken = baseAuth + "/token";
	public static final String myProfile = baseAuth + "/api/me";
	public static final String unlockTheBarn = "/barn-unlock";
	public static final String putToiletSeatDown = "/toiletseat-down";
	public static final String feedYourChickes = "/chickens-feed";
	public static final String createNewUser = baseUrl + "/api/users";

	/*
	 * ---------------------------- ZBOX API's-------------------------------
	 */

	//usecaseManagment
	public static final String login = baseUserManagement+"/login";
	public static final String GET_ALL_USECASES ="/workflow/v1/management/getallusecases";
	public static final String UPDATE_USECASES ="/workflow/v1/management/updateusecase";
	public static final String CREATE_USECASES ="/workflow/v1/management/createusecase";
	
	
	/*
	 * ---------------------------- Zindagi API's-------------------------------
	 */
	
	 public static final String USER_AUTHENTICATION = "/api/Account/LoginAuthentication";
	 public static final String MOVE_MONEY_MENU = "/api/Account/CustomerLimit";
	 public static final String Z_2_Z_INQUIRY = "/api/Inquiry/WalletToWalletPaymentInquiry";
	 public static final String Z_2_Z_PAYMENT = "/api/Payment/WalletToWallet";
	 public static final String Z_2_CNIC = "/api/Inquiry/WalletToCNICInquiry";

}
