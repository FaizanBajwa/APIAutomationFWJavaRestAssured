package com.restassured.constants;

public class ApiUrls {

	public static String baseUserManagement = "http://10.0.0.13:8080/mfs_user_management/umngt";
	public static String ZINDAGI_BASE_URL = "http://zindigi-qa.appinsnap.com/1.0.28-android";
	public static String ZBOX_BASE_URL_QA = "http://10.0.1.4:8080";
	public static String ZBOX_BASE_URL_DEV = "http://10.0.0.13:8080";

	/*
	 * ---------------------------- ZBOX API's-------------------------------
	 */

	//usecaseManagment
	public static final String login = baseUserManagement+"/login";
	public static final String GET_ALL_USECASES ="/workflow/v1/management/getallusecases";
	public static final String UPDATE_USECASES ="/workflow/v1/management/updateusecase";
	public static final String CREATE_USECASES ="/workflow/v1/management/createusecase";
	
	//AccountTypes
	public static final String CREATE_NEW_ACCOUNT_TYPE ="/account/v1/accountype/createnewaccounttype";
	public static final String GET_ALL_ACCOUNT_TYPES ="/account/v1/accountype/getallaccounttypes";
	public static final String LKP_GET_ACCOUNT_TYPES ="/configuration/v1/lookups/getaccounttypes/LKP_ACCOUNT_LEVEL:*";
	public static final String INACTIVE_ACCOUNT_TYPE ="/account/v1/accountype/inactiveaccounttype";
	public static final String GET_KYC_SET = "/configuration/v1/lookups/getkycsets/TBL_KYC_SET_HEAD:*";
	public static final String GET_CLIENT_ROLES ="/configuration/v1/lookups/getclientroles/LKP_ACCOUNT_CLASSIFICATION:*";
	
	//LIMITS
	public static final String LK_GET_TRANSACTIONPROFILELIMITDATA = "/configuration/v1/lookups/gettransactionprofilelimitdata/TBL_TRANS_LIMIT:*";
	public static final String LK_GET_ACCOUNTTYPES="/configuration/v1/lookups/getaccounttypes/LKP_ACCOUNT_LEVEL:*";
	public static final String LK_GET_TRANSACTIONTYPES="/configuration/v1/lookups/gettransactiontypes/TBL_PRODUCT:*";
	public static final String CREATE_TRANSACTIONWISELIMITPROFILE="/limits/v1/limitsprofiles/createtrasactionwiselimitprofile";
	public static final String GETALLTRANDACTIONWISELIMITPROFILES="/limits/v1/limitsprofiles/getalltransactionwiselimitprofiles";
	public static final String INACTIVETRANSACTIONWISELIMITPROFILE="/limits/v1/limitsprofiles/inactivertransactionwiselimitprofile";
	public static final String UPDATETRANDACTIONWISELIMITPROFILE="/limits/v1/limitsprofiles/updatetransactionwiselimitprofile";
	/*
	 * ---------------------------- Zindagi API's-------------------------------
	 */
	
	 public static final String USER_AUTHENTICATION = "/api/Account/LoginAuthentication";
	 public static final String MOVE_MONEY_MENU = "/api/Account/CustomerLimit";
	 public static final String Z_2_Z_INQUIRY = "/api/Inquiry/WalletToWalletPaymentInquiry";
	 public static final String Z_2_Z_PAYMENT = "/api/Payment/WalletToWallet";
	 public static final String Z_2_CNIC = "/api/Inquiry/WalletToCNICInquiry";

}
