package com.restassured.limits.api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.restassured.constants.ApiUrls;
import com.restassured.listeners.AllureUtils;
import com.restassured.models.limits.ApiModelZbox;
import com.restassured.models.limits.Data;
import com.restassured.models.limits.PayLoad;
import com.restassured.models.limits.tblTransLimitDetailRequestList;
import com.restassured.utils.ConfigManager;
import com.restassured.utils.TestUtils;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class CreateTransactionWiseLimitProfile extends LimitsBaseClass {
    //FaizanBajwa
	private Map<String, Object> getHeaderList;
	private ApiModelZbox TC1,TC2,TC3,TC4,TC5,TC6,TC7,TC8,TC9,TC10,TC11;
	
	private ConfigManager getKey;
	
	@BeforeMethod(groups="Usecase_Managmnet")
	public void beforeMethod() {
		try {
			getKey = new ConfigManager();
			getHeaderList = new HashMap<String, Object>();
			getHeaderList.put("Authorization",getKey.getKeyValue("AUTH_TOKEN"));		
			//getHeaderList.put("USER","HELLO");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Feature("LIMITS")
	@Story("LOOK UP FLOW")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="LOOKUPS",description="LOOK UP APIS")
	public void LookUpsAPIs() {
		
		AllureUtils.logStep("Lookup Get Transaction Profile limit data from API "+ApiUrls.LK_GET_TRANSACTIONPROFILELIMITDATA);
		Response response_getall_transactionProfileData = getApiResponse(getHeaderList,ApiUrls.LK_GET_TRANSACTIONPROFILELIMITDATA );
		Assert.assertEquals(response_getall_transactionProfileData.getStatusCode(), 200);
		Assert.assertEquals(response_getall_transactionProfileData.jsonPath().getString("message"), "Success");
		Assert.assertEquals(response_getall_transactionProfileData.jsonPath().getString("responseCode"), "0000");	
		String response_body_string = response_getall_transactionProfileData.getBody().asString();
		JsonPath jsonPath = JsonPath.from(response_body_string);
		List<String> LovIdArraytransactionProfileData = new ArrayList<String>();
		List<Map<String, Object>> json_array = jsonPath.getList("payLoad");
		for(Map<String, Object> json_object : json_array) {
			String id = json_object.get("lovId").toString();
			//String Name = json_object.get("name").toString();
			Assert.assertNotNull(id ,"lovid value should not be null");
			//Assert.assertNotNull(Name,"name should not be null");
			// System.out.println(json_object); 
			// System.out.println(id);
			LovIdArraytransactionProfileData.add(id);
			// System.out.println(name);
			
		}
		System.out.println(LovIdArraytransactionProfileData);
		boolean hasDuplicates = hasDuplicates(LovIdArraytransactionProfileData);
		Assert.assertFalse(hasDuplicates, "Duplicates found in the string array");
		AllureUtils.attachData("Response Body", response_getall_transactionProfileData.asPrettyString());

		/*
		 * ---------------------------- LK-getAccountTypesApis-------------------------------
		 */
		
		AllureUtils.logStep("Lookup Get Account Types data from API "+ApiUrls.LK_GET_ACCOUNTTYPES);
		Response response_getall_AccountTypes = getApiResponse(getHeaderList,ApiUrls.LK_GET_ACCOUNTTYPES );
		Assert.assertEquals(response_getall_AccountTypes.getStatusCode(), 200);
		Assert.assertEquals(response_getall_AccountTypes.jsonPath().getString("message"), "Success");
		Assert.assertEquals(response_getall_AccountTypes.jsonPath().getString("responseCode"), "0000");
		String responseStringAccountTypes = response_getall_AccountTypes.getBody().asString();
		JsonPath jsonPathAccountTypes = JsonPath.from(responseStringAccountTypes);
		List<String> LovIdArrayAccountTypes = new ArrayList<String>();
		List<Map<String, Object>> json_array_AccountTypes = jsonPathAccountTypes.getList("payLoad");
		for(Map<String, Object> json_object_AccountTypes : json_array_AccountTypes) {
			String Accountid = json_object_AccountTypes.get("lovId").toString();
			String Accountname = json_object_AccountTypes.get("name").toString();
			String Account_isActive = json_object_AccountTypes.get("isActive").toString();
			//String code = json_object_AccountTypes.get("code").toString();
			Assert.assertNotNull(Accountid ,"lovid value should not be null");
			Assert.assertNotNull(Accountname,"name should not be null");
			Assert.assertNotNull(Account_isActive ,"lovid value should not be null");
			//Assert.assertNotNull(code,"name should not be null");
			
			
			System.out.println(Accountid);
			LovIdArrayAccountTypes.add(Accountid);
			
		}
		System.out.println(LovIdArrayAccountTypes);
		boolean hasDuplicatesAccountTypes = hasDuplicates(LovIdArrayAccountTypes);
		Assert.assertFalse(hasDuplicatesAccountTypes, "Duplicates found in the string array");
		AllureUtils.attachData("Response Body", response_getall_AccountTypes.asPrettyString());
	
		/*
		 * ---------------------------- LK-GetTransactionTypes-------------------------------
		 */
	
		AllureUtils.logStep("Lookup Get Transaction Types data from API "+ApiUrls.LK_GET_TRANSACTIONTYPES);
		Response response_getall_TransactionTypes = getApiResponse(getHeaderList,ApiUrls.LK_GET_TRANSACTIONTYPES );
		Assert.assertEquals(response_getall_TransactionTypes.getStatusCode(), 200);
		Assert.assertEquals(response_getall_TransactionTypes.jsonPath().getString("message"), "Success");
		Assert.assertEquals(response_getall_TransactionTypes.jsonPath().getString("responseCode"), "0000");
		String responseStringTransactionTypes = response_getall_TransactionTypes.getBody().asString();
		JsonPath jsonPathTransactionTypes = JsonPath.from(responseStringTransactionTypes);
		List<String> LovIdArrayTransactionTypes = new ArrayList<String>();
		List<Map<String, Object>> json_array_TransactionTypes = jsonPathTransactionTypes.getList("payLoad");
		for(Map<String, Object> json_object_TransactionTypes : json_array_TransactionTypes) {
			String Transactionid = json_object_TransactionTypes.get("lovId").toString();
			// String Transactionname = json_object_TransactionTypes.get("name").toString();
			String Transaction_isActive = json_object_TransactionTypes.get("isActive").toString();
			//String Transaction_code = json_object_TransactionTypes.get("code").toString();
			Assert.assertNotNull(Transactionid ,"lovid value should not be null");
			//Assert.assertNotNull(Transactionname,"name should not be null");
			Assert.assertNotNull(Transaction_isActive ,"lovid value should not be null");
			//Assert.assertNotNull(Transaction_code,"name should not be null");
			
			// System.out.println(json_object); 
			System.out.println(Transactionid);
			LovIdArrayTransactionTypes.add(Transactionid);
			// System.out.println(code);
			
		}
		System.out.println(LovIdArrayTransactionTypes);
		boolean hasDuplicatesTransactionTypes = hasDuplicates(LovIdArrayTransactionTypes);
		Assert.assertFalse(hasDuplicatesTransactionTypes, "Duplicates found in the string array");
		AllureUtils.attachData("Response Body", response_getall_TransactionTypes.asPrettyString());
		
	}
	
	
	
	@Feature("LIMITS")
	@Story("Create Transaction FLOW")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="CreateTransactionFlow",description="Create Transaction Profile Limit Flow")
	public void CreateTransactionflow() {
		
		 int randomNumber = generateRandomNumber(1, 10000);
		 String Pname = ("MICROBankTestAutomation"+randomNumber);
		
//--------------------------------------------------CREATE TRANSACTION PROFILE API----------------------------------------
		AllureUtils.logStep("Create Transaction Profile from API "+ApiUrls.CREATE_TRANSACTIONWISELIMITPROFILE);
		TC1 = new ApiModelZbox();
		TC1.setData(new Data());
		TC1.getData().setPayLoad(new PayLoad());
		TC1.getData().getPayLoad().setLkpAccountLevelId("1");
		TC1.getData().getPayLoad().setDailyAmtLimitCr("1");
		TC1.getData().getPayLoad().setDailyAmtLimitDr("1");
		TC1.getData().getPayLoad().setDailyTransLimitCr("1");
		TC1.getData().getPayLoad().setDailyTransLimitDr("1");
		TC1.getData().getPayLoad().setExcludeLimit("N");
		TC1.getData().getPayLoad().setLimitProfileName(Pname);
		TC1.getData().getPayLoad().setMonthlyAmtLimitCr("1");
		TC1.getData().getPayLoad().setMonthlyAmtLimitDr("1");
		TC1.getData().getPayLoad().setMonthlyTransLimitCr("1");
		TC1.getData().getPayLoad().setMonthlyTransLimitDr("1");
		TC1.getData().getPayLoad().setYearlyAmtLimitCr("1");
		TC1.getData().getPayLoad().setYearlyAmtLimitDr("1");
		TC1.getData().getPayLoad().setYearlyTransLimitCr("1");
		TC1.getData().getPayLoad().setYearlyTransLimitDr("1");
		List<tblTransLimitDetailRequestList> requestList = new ArrayList<tblTransLimitDetailRequestList>();
		tblTransLimitDetailRequestList request = new tblTransLimitDetailRequestList();
		request.settblProductId("10245397");
		requestList.add(request);
		TC1.getData().getPayLoad().setTblTransLimitDetailRequestList(requestList);
		String request_json_body = TestUtils.gsonString(TC1);
		Response response_create_TransactionProfile = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_TRANSACTIONWISELIMITPROFILE);
		System.out.println(response_create_TransactionProfile.asPrettyString());
		AllureUtils.attachData("Request Body", request_json_body.toString());
		AllureUtils.attachData("Response Body", response_create_TransactionProfile.asPrettyString());
		
		//*********************Asserting ResponseCode and values***************************
		Assert.assertEquals(response_create_TransactionProfile.getStatusCode(), 200);
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("responseCode"), "020000");
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.dailyAmtLimitCr"), TC1.getData().getPayLoad().getDailyAmtLimitCr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.dailyAmtLimitDr"), TC1.getData().getPayLoad().getDailyAmtLimitDr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.dailyTransLimitCr"), TC1.getData().getPayLoad().getDailyTransLimitCr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.dailyTransLimitDr"), TC1.getData().getPayLoad().getDailyTransLimitDr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.excludeLimit"), TC1.getData().getPayLoad().getExcludeLimit());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.limitProfileName"), TC1.getData().getPayLoad().getLimitProfileName());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.monthlyAmtLimitCr"), TC1.getData().getPayLoad().getMonthlyAmtLimitCr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.monthlyAmtLimitDr"), TC1.getData().getPayLoad().getMonthlyAmtLimitDr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.monthlyTransLimitCr"), TC1.getData().getPayLoad().getMonthlyTransLimitCr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.monthlyTransLimitDr"), TC1.getData().getPayLoad().getMonthlyTransLimitDr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.yearlyAmtLimitCr"), TC1.getData().getPayLoad().getYearlyAmtLimitCr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.yearlyAmtLimitDr"), TC1.getData().getPayLoad().getYearlyAmtLimitDr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.yearlyTransLimitCr"), TC1.getData().getPayLoad().getYearlyTransLimitCr());
		Assert.assertEquals(response_create_TransactionProfile.jsonPath().getString("payLoad.yearlyTransLimitDr"), TC1.getData().getPayLoad().getYearlyTransLimitDr());
		
		//********Asserting Mandatory Params which should not be null**************
		Assert.assertNotNull(response_create_TransactionProfile.jsonPath().getString("payLoad.transLimitId"),"Trans Limit id Value should not be null");
		// Assert.assertNotNull(response_create_TransactionProfile.jsonPath().getString("payLoad.createdate"),"Create Date Value should not be null");
		Assert.assertNotNull(response_create_TransactionProfile.jsonPath().getString("payLoad.createuser"),"Create User Value should not be null");
		Assert.assertNotNull(response_create_TransactionProfile.jsonPath().getString("payLoad.isActive"),"IsActive Value should not be null");
		
		//************For chaining CREATE api with GET api and passing Profile name to filter records(chaining w.r.t transLimitID)
		String ProfileName = response_create_TransactionProfile.jsonPath().getString("payLoad.limitProfileName");
		String TranslimitWhileCreating = response_create_TransactionProfile.jsonPath().getString("payLoad.transLimitId");
		
//----------------------------------------------------------GET ALL TRANSACTION PROFILES API----------------------------------------------------------------------------
		
		AllureUtils.logStep("Get all Transaction Profile from API "+ApiUrls.GETALLTRANDACTIONWISELIMITPROFILES);
		TC2 = new ApiModelZbox();
		TC2.setData(new Data());
		TC2.getData().setPayLoad(new PayLoad());
		TC2.getData().getPayLoad().setLimitProfileName(ProfileName);
		TC2.getData().getPayLoad().setDateFrom(GetCurrentDate());
		TC2.getData().getPayLoad().setDateTo(GetCurrentDate());
		TC2.getData().getPayLoad().setCreatedBy("");
		TC2.getData().getPayLoad().setUpdatedBy("");
		TC2.getData().getPayLoad().setStatusId("");
		String requestjsonbody = TestUtils.gsonString(TC2);
		Response response_getalltransactionwiselimitprofiles = postApiResponse(getHeaderList, requestjsonbody, ApiUrls.GETALLTRANDACTIONWISELIMITPROFILES);
		AllureUtils.attachData("Request Body", requestjsonbody.toString());
		AllureUtils.attachData("Response Body", response_getalltransactionwiselimitprofiles.asPrettyString());
		Assert.assertEquals(response_getalltransactionwiselimitprofiles.getStatusCode(), 200);
		Assert.assertEquals(response_getalltransactionwiselimitprofiles.jsonPath().getString("responseCode"), "020000");
		
		
		String responseStringGetAllTrandaction = response_getalltransactionwiselimitprofiles.getBody().asString();
		JsonPath jsonPathTransLimitid = JsonPath.from(responseStringGetAllTrandaction);
		List<String> ArrayTransLimitid = new ArrayList<String>();
		List<Map<String, Object>> json_array_TransLmit = jsonPathTransLimitid.getList("payLoad");
		
		//***************creating list of all translimitIDs******************
		for(Map<String, Object> json_object_Translimit : json_array_TransLmit) {
			String TransLimit = json_object_Translimit.get("transLimitId").toString();
			//System.out.println(TransLimit);
			ArrayTransLimitid.add(TransLimit);
			}
		System.out.println(ArrayTransLimitid);
		boolean valuePresent = false; //verifying, if translimitid while creating is present in the list that we created above
	       for (String element : ArrayTransLimitid) {
	            if (element.equals(TranslimitWhileCreating)) {
	                valuePresent = true;
	                break;
	            }
	        }
	    Assert.assertTrue(valuePresent,"Trans limit Value not found in the string array");
	    
	    
	    //***********************getting the particular object from payload against the created translimitid ************************  
		int target_TranslimitID = Integer.parseInt(response_create_TransactionProfile.jsonPath().getString("payLoad.transLimitId"));
		Map<String, Object> target_json_object = null;		
			for (Map<String, Object> json_object : json_array_TransLmit) {
			    int tlid =  (Integer) json_object.get("transLimitId");
			    if (tlid == target_TranslimitID) {
			    	target_json_object = json_object;
			        break;
			    }
			}
		System.out.println(target_json_object);
		
		//************Assertions on Mandatory Params in GetAll transaction Api**************************
		Assert.assertNotNull(target_json_object.get("transLimitId"),"TransLimitId Value should not be null");
		Assert.assertNotNull(target_json_object.get("createdate"),"Create Date Value should not be null");
		Assert.assertNotNull(target_json_object.get("createuser"),"Create User Value should not be null");
		Assert.assertNotNull(target_json_object.get("isActive"),"Create User Value should not be null");
		
		//**************Asserting reponse of json object against particular translimit id with the data provided while creating transaction profile*******************
		Assert.assertEquals(target_json_object.get("dailyAmtLimitCr").toString(), TC1.getData().getPayLoad().getDailyAmtLimitCr());
		Assert.assertEquals(target_json_object.get("dailyAmtLimitDr").toString(), TC1.getData().getPayLoad().getDailyAmtLimitDr());
		Assert.assertEquals(target_json_object.get("dailyTransLimitCr").toString(), TC1.getData().getPayLoad().getDailyTransLimitCr());
		Assert.assertEquals(target_json_object.get("dailyTransLimitDr").toString(), TC1.getData().getPayLoad().getDailyTransLimitDr());
		Assert.assertEquals(target_json_object.get("excludeLimit").toString(), TC1.getData().getPayLoad().getExcludeLimit());
		Assert.assertEquals(target_json_object.get("limitProfileName").toString(), TC1.getData().getPayLoad().getLimitProfileName());
		Assert.assertEquals(target_json_object.get("monthlyAmtLimitCr").toString(), TC1.getData().getPayLoad().getMonthlyAmtLimitCr());
		Assert.assertEquals(target_json_object.get("monthlyAmtLimitDr").toString(), TC1.getData().getPayLoad().getMonthlyAmtLimitDr());
		Assert.assertEquals(target_json_object.get("monthlyTransLimitCr").toString(), TC1.getData().getPayLoad().getMonthlyTransLimitCr());
		Assert.assertEquals(target_json_object.get("monthlyTransLimitDr").toString(), TC1.getData().getPayLoad().getMonthlyTransLimitDr());
		Assert.assertEquals(target_json_object.get("yearlyAmtLimitCr").toString(), TC1.getData().getPayLoad().getYearlyAmtLimitCr());
		Assert.assertEquals(target_json_object.get("yearlyAmtLimitDr").toString(), TC1.getData().getPayLoad().getYearlyAmtLimitDr());
		Assert.assertEquals(target_json_object.get("yearlyTransLimitCr").toString(), TC1.getData().getPayLoad().getYearlyTransLimitCr());
		Assert.assertEquals(target_json_object.get("yearlyTransLimitDr").toString(), TC1.getData().getPayLoad().getYearlyTransLimitDr());
		
		Assert.assertEquals(target_json_object.get("createuser").toString(), response_create_TransactionProfile.jsonPath().getString("payLoad.createuser"));
		//Assert.assertEquals(target_json_object.get("createdate").toString(), response_create_TransactionProfile.jsonPath().getString("payLoad.createdate"));
		Assert.assertEquals(target_json_object.get("isActive").toString(), response_create_TransactionProfile.jsonPath().getString("payLoad.isActive"));
		
//---------------------------------------------------------UPDATE TRANSACTION PROFILES API----------------------------------------------------------------------------
		
		AllureUtils.logStep("Update Transaction Profile from API "+ApiUrls.UPDATETRANDACTIONWISELIMITPROFILE);
		TC9 = new ApiModelZbox();
		TC9.setData(new Data());
		TC9.getData().setPayLoad(new PayLoad());
		TC9.getData().getPayLoad().setLkpAccountLevelId("1");
		TC9.getData().getPayLoad().setDailyAmtLimitCr("100");
		TC9.getData().getPayLoad().setDailyAmtLimitDr("100");
		TC9.getData().getPayLoad().setDailyTransLimitCr("100");
		TC9.getData().getPayLoad().setDailyTransLimitDr("100");
		TC9.getData().getPayLoad().setExcludeLimit("Y");
		TC9.getData().getPayLoad().setLimitProfileName(ProfileName);
		TC9.getData().getPayLoad().setMonthlyAmtLimitCr("100");
		TC9.getData().getPayLoad().setMonthlyAmtLimitDr("100");
		TC9.getData().getPayLoad().setMonthlyTransLimitCr("100");
		TC9.getData().getPayLoad().setMonthlyTransLimitDr("100");
		TC9.getData().getPayLoad().setYearlyAmtLimitCr("100");
		TC9.getData().getPayLoad().setYearlyAmtLimitDr("100");
		TC9.getData().getPayLoad().setYearlyTransLimitCr("100");
		TC9.getData().getPayLoad().setYearlyTransLimitDr("100");
		TC9.getData().getPayLoad().setTranslimitid(TranslimitWhileCreating);
		List<tblTransLimitDetailRequestList> requestListUpdate = new ArrayList<tblTransLimitDetailRequestList>();
		tblTransLimitDetailRequestList requestUpdate = new tblTransLimitDetailRequestList();
		requestUpdate.settblProductId("10245397");
		requestListUpdate.add(requestUpdate);
		TC9.getData().getPayLoad().setTblTransLimitDetailRequestList(requestListUpdate);
		String request_json_body_Update = TestUtils.gsonString(TC9);
		//System.out.println(request_json_body_Update);
		Response response_update_TransactionProfile = postApiResponse(getHeaderList, request_json_body_Update, ApiUrls.UPDATETRANDACTIONWISELIMITPROFILE);
		System.out.println(response_update_TransactionProfile.asPrettyString());
		AllureUtils.attachData("Request Body", request_json_body_Update.toString());
		AllureUtils.attachData("Response Body", response_update_TransactionProfile.asPrettyString());
		
		
		Assert.assertEquals(response_update_TransactionProfile.getStatusCode(), 200);
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("responseCode"), "020000");
		
		//********Asserting Mandatory Params which should not be null**************
		Assert.assertNotNull(response_update_TransactionProfile.jsonPath().getString("payLoad.transLimitId"),"Trans Limit id Value should not be null");
		// Assert.assertNotNull(response_update_TransactionProfile.jsonPath().getString("payLoad.createdate"),"Create Date Value should not be null");
		Assert.assertNotNull(response_update_TransactionProfile.jsonPath().getString("payLoad.createuser"),"Create User Value should not be null");
		Assert.assertNotNull(response_update_TransactionProfile.jsonPath().getString("payLoad.isActive"),"IsActive Value should not be null");
		Assert.assertNotNull(response_update_TransactionProfile.jsonPath().getString("payLoad.lastupdatedate"),"Last Update Date Value should not be null");
		Assert.assertNotNull(response_update_TransactionProfile.jsonPath().getString("payLoad.lastupdateuser"),"Last update user Value should not be null");
		Assert.assertNotNull(response_update_TransactionProfile.jsonPath().getString("payLoad.updateindex"),"Update index Value should not be null");
		
		//********Asserting on Values**************
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.dailyAmtLimitCr"), TC9.getData().getPayLoad().getDailyAmtLimitCr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.dailyAmtLimitDr"), TC9.getData().getPayLoad().getDailyAmtLimitDr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.dailyTransLimitCr"), TC9.getData().getPayLoad().getDailyTransLimitCr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.dailyTransLimitDr"), TC9.getData().getPayLoad().getDailyTransLimitDr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.excludeLimit"), TC9.getData().getPayLoad().getExcludeLimit());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.limitProfileName"), TC9.getData().getPayLoad().getLimitProfileName());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.monthlyAmtLimitCr"), TC9.getData().getPayLoad().getMonthlyAmtLimitCr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.monthlyAmtLimitDr"), TC9.getData().getPayLoad().getMonthlyAmtLimitDr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.monthlyTransLimitCr"), TC9.getData().getPayLoad().getMonthlyTransLimitCr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.monthlyTransLimitDr"), TC9.getData().getPayLoad().getMonthlyTransLimitDr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.yearlyAmtLimitCr"), TC9.getData().getPayLoad().getYearlyAmtLimitCr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.yearlyAmtLimitDr"), TC9.getData().getPayLoad().getYearlyAmtLimitDr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.yearlyTransLimitCr"), TC9.getData().getPayLoad().getYearlyTransLimitCr());
		Assert.assertEquals(response_update_TransactionProfile.jsonPath().getString("payLoad.yearlyTransLimitDr"), TC9.getData().getPayLoad().getYearlyTransLimitDr());
		
//---------------------------------------------------------Inactive TRANSACTION PROFILES API----------------------------------------------------------------------------
		
		AllureUtils.logStep("Inactive Transaction Profile from API "+ApiUrls.INACTIVETRANSACTIONWISELIMITPROFILE);
		TC6= new ApiModelZbox();
		TC6.setData(new Data());
		TC6.getData().setPayLoad(new PayLoad());
		TC6.getData().getPayLoad().setTranslimitid(TranslimitWhileCreating);
		TC6.getData().getPayLoad().setIsActive("N");
		
		String requestjsonbodyinactive = TestUtils.gsonString(TC6);
		System.out.println(requestjsonbodyinactive);
		Response response_inactivetransactionwiselimitprofiles = postApiResponse(getHeaderList, requestjsonbodyinactive, ApiUrls.INACTIVETRANSACTIONWISELIMITPROFILE);
		AllureUtils.attachData("Request Body",requestjsonbodyinactive.toString());
		AllureUtils.attachData("Response Body", response_inactivetransactionwiselimitprofiles.asPrettyString());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.getStatusCode(), 200);
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("responseCode"), "020000");
		
		//Verifying mandatory params
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.transLimitId"),"Trans Limit id Value should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.createdate"),"Create date Value should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.createuser"),"Create user Value should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.isActive"),"Is Active Value should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.lkpStatus"),"lkp Status Value should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.lkpStatus.statusId"),"Status Id should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.lkpStatus.createdate"),"Create date value should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.lkpStatus.createuser"),"Create user should not be null");
		Assert.assertNotNull(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.lkpStatus.isActive"),"Is Active should not be null");
		
		//Verfiying values of Transaction wise profile with values provided during creation
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.dailyAmtLimitCr"), TC9.getData().getPayLoad().getDailyAmtLimitCr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.dailyAmtLimitDr"), TC9.getData().getPayLoad().getDailyAmtLimitDr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.dailyTransLimitCr"), TC9.getData().getPayLoad().getDailyTransLimitCr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.dailyTransLimitDr"), TC9.getData().getPayLoad().getDailyTransLimitDr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.excludeLimit"), TC9.getData().getPayLoad().getExcludeLimit());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.limitProfileName"), TC9.getData().getPayLoad().getLimitProfileName());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.monthlyAmtLimitCr"), TC9.getData().getPayLoad().getMonthlyAmtLimitCr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.monthlyAmtLimitDr"), TC9.getData().getPayLoad().getMonthlyAmtLimitDr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.monthlyTransLimitCr"), TC9.getData().getPayLoad().getMonthlyTransLimitCr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.monthlyTransLimitDr"), TC9.getData().getPayLoad().getMonthlyTransLimitDr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.yearlyAmtLimitCr"), TC9.getData().getPayLoad().getYearlyAmtLimitCr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.yearlyAmtLimitDr"), TC9.getData().getPayLoad().getYearlyAmtLimitDr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.yearlyTransLimitCr"), TC9.getData().getPayLoad().getYearlyTransLimitCr());
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.yearlyTransLimitDr"), TC9.getData().getPayLoad().getYearlyTransLimitDr());
		
		//Verfiying values of Transaction wise profile with values generated in response during creation
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.transLimitId"), response_create_TransactionProfile.jsonPath().getString("payLoad.transLimitId"));
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.createuser"), response_create_TransactionProfile.jsonPath().getString("payLoad.createuser"));
		//Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.createdate"), response_create_TransactionProfile.jsonPath().getString("payLoad.createdate"));
		Assert.assertEquals(response_inactivetransactionwiselimitprofiles.jsonPath().getString("payLoad.isActive"), response_create_TransactionProfile.jsonPath().getString("payLoad.isActive"));
			    
	}
	
	
	
	
	
	
	@Feature("LIMITS")
	@Story("Create Transaction FLOW")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="CreateTransactionFlow",description="Negetive(without auth token) Create Transaction Profile Limit Flow")
	public void Negetive_Create_Transaction_Wise_Limit_Profile() {
		
		int randomNumber = generateRandomNumber(1, 10000);
		Map<String, Object> NegetivegetHeaderList = new HashMap<String, Object>();
		getHeaderList.put("Authorization","");
		
		//----------------------------------------CREATE TRANSACTION PROFILE API----------------------------------------
		AllureUtils.logStep("Create Transaction Profile for negetive test from API "+ApiUrls.CREATE_TRANSACTIONWISELIMITPROFILE);
		TC3 = new ApiModelZbox();
		TC3.setData(new Data());
		TC3.getData().setPayLoad(new PayLoad());
		TC3.getData().getPayLoad().setLkpAccountLevelId("1");
		TC3.getData().getPayLoad().setDailyAmtLimitCr("1");
		TC3.getData().getPayLoad().setDailyAmtLimitDr("1");
		TC3.getData().getPayLoad().setDailyTransLimitCr("1");
		TC3.getData().getPayLoad().setDailyTransLimitDr("1");
		TC3.getData().getPayLoad().setExcludeLimit("N");
		TC3.getData().getPayLoad().setLimitProfileName("MICROBankTestAutomation"+randomNumber);
		TC3.getData().getPayLoad().setMonthlyAmtLimitCr("1");
		TC3.getData().getPayLoad().setMonthlyAmtLimitDr("1");
		TC3.getData().getPayLoad().setMonthlyTransLimitCr("1");
		TC3.getData().getPayLoad().setMonthlyTransLimitDr("1");
		TC3.getData().getPayLoad().setYearlyAmtLimitCr("1");
		TC3.getData().getPayLoad().setYearlyAmtLimitDr("1");
		TC3.getData().getPayLoad().setYearlyTransLimitCr("1");
		TC3.getData().getPayLoad().setYearlyTransLimitDr("1");
		List<tblTransLimitDetailRequestList> requestList = new ArrayList<tblTransLimitDetailRequestList>();
		tblTransLimitDetailRequestList request = new tblTransLimitDetailRequestList();
		request.settblProductId("10245397");
		//request.settblTransLimitId("6");
		requestList.add(request);
		TC3.getData().getPayLoad().setTblTransLimitDetailRequestList(requestList);
		String request_json_body = TestUtils.gsonString(TC3);
		Response response_Neg_create_TransactionProfile = postApiResponse(NegetivegetHeaderList, request_json_body, ApiUrls.CREATE_TRANSACTIONWISELIMITPROFILE);
		System.out.println(response_Neg_create_TransactionProfile.asPrettyString());
		AllureUtils.attachData("Request Body",request_json_body.toString());
		AllureUtils.attachData("Response Body", response_Neg_create_TransactionProfile.asPrettyString());
		
		
		Assert.assertEquals(response_Neg_create_TransactionProfile.getStatusCode(), 400);
		Assert.assertEquals(response_Neg_create_TransactionProfile.jsonPath().getString("responseCode"), "022000");
	}
	
	@Feature("LIMITS")
	@Story("Update Transaction API")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="UpdateTransaction",description="Negetive(without auth token) Update Transaction WiseFlow")
	public void Negetive_Update_Transaction_Api() {
		int randomNumber = generateRandomNumber(1, 100000);
		String Pname = ("MICROBankTestAutomation"+randomNumber);
		AllureUtils.logStep("Inactive Transaction Profile for negetive test from API "+ApiUrls.UPDATETRANDACTIONWISELIMITPROFILE);
		TC10 = new ApiModelZbox();
		TC10.setData(new Data());
		TC10.getData().setPayLoad(new PayLoad());
		TC10.getData().getPayLoad().setLkpAccountLevelId("1");
		TC10.getData().getPayLoad().setDailyAmtLimitCr("1");
		TC10.getData().getPayLoad().setDailyAmtLimitDr("1");
		TC10.getData().getPayLoad().setDailyTransLimitCr("1");
		TC10.getData().getPayLoad().setDailyTransLimitDr("1");
		TC10.getData().getPayLoad().setExcludeLimit("N");
		TC10.getData().getPayLoad().setLimitProfileName(Pname);
		TC10.getData().getPayLoad().setMonthlyAmtLimitCr("1");
		TC10.getData().getPayLoad().setMonthlyAmtLimitDr("1");
		TC10.getData().getPayLoad().setMonthlyTransLimitCr("1");
		TC10.getData().getPayLoad().setMonthlyTransLimitDr("1");
		TC10.getData().getPayLoad().setYearlyAmtLimitCr("1");
		TC10.getData().getPayLoad().setYearlyAmtLimitDr("1");
		TC10.getData().getPayLoad().setYearlyTransLimitCr("1");
		TC10.getData().getPayLoad().setYearlyTransLimitDr("1");
		List<tblTransLimitDetailRequestList> requestList = new ArrayList<tblTransLimitDetailRequestList>();
		tblTransLimitDetailRequestList request = new tblTransLimitDetailRequestList();
		request.settblProductId("10245397");
		//request.settblTransLimitId("6");
		requestList.add(request);
		TC10.getData().getPayLoad().setTblTransLimitDetailRequestList(requestList);
		String request_json_body = TestUtils.gsonString(TC10);
		Response response_create_TransactionProfile = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_TRANSACTIONWISELIMITPROFILE);
		System.out.println(response_create_TransactionProfile.asPrettyString());
		String TranslimitWhileCreating = response_create_TransactionProfile.jsonPath().getString("payLoad.transLimitId");
		
		
		Map<String, Object> getHeaderList = new HashMap<String, Object>();
		getHeaderList.put("Authorization","");
		
		TC11 = new ApiModelZbox();
		TC11.setData(new Data());
		TC11.getData().setPayLoad(new PayLoad());
		TC11.getData().getPayLoad().setLkpAccountLevelId("1");
		TC11.getData().getPayLoad().setDailyAmtLimitCr("100");
		TC11.getData().getPayLoad().setDailyAmtLimitDr("100");
		TC11.getData().getPayLoad().setDailyTransLimitCr("100");
		TC11.getData().getPayLoad().setDailyTransLimitDr("100");
		TC11.getData().getPayLoad().setExcludeLimit("Y");
		TC11.getData().getPayLoad().setLimitProfileName(Pname);
		TC11.getData().getPayLoad().setMonthlyAmtLimitCr("100");
		TC11.getData().getPayLoad().setMonthlyAmtLimitDr("100");
		TC11.getData().getPayLoad().setMonthlyTransLimitCr("100");
		TC11.getData().getPayLoad().setMonthlyTransLimitDr("100");
		TC11.getData().getPayLoad().setYearlyAmtLimitCr("100");
		TC11.getData().getPayLoad().setYearlyAmtLimitDr("100");
		TC11.getData().getPayLoad().setYearlyTransLimitCr("100");
		TC11.getData().getPayLoad().setYearlyTransLimitDr("100");
		TC11.getData().getPayLoad().setTranslimitid(TranslimitWhileCreating);
		List<tblTransLimitDetailRequestList> requestListUpdate = new ArrayList<tblTransLimitDetailRequestList>();
		tblTransLimitDetailRequestList requestUpdate = new tblTransLimitDetailRequestList();
		requestUpdate.settblProductId("10245397");
		requestListUpdate.add(requestUpdate);
		TC11.getData().getPayLoad().setTblTransLimitDetailRequestList(requestListUpdate);
		String request_json_body_Update = TestUtils.gsonString(TC11);
		Response response_update_TransactionProfile = postApiResponse(getHeaderList, request_json_body_Update, ApiUrls.UPDATETRANDACTIONWISELIMITPROFILE);
		AllureUtils.attachData("Request Body",request_json_body_Update.toString());
		AllureUtils.attachData("Response Body", response_update_TransactionProfile.asPrettyString());
		
		Assert.assertEquals(response_update_TransactionProfile.getStatusCode(), 400);
	}
	
	@Feature("LIMITS")
	@Story("Inactive Transaction FLOW")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="InactiveTransactionWiseFlow",description="Negetive(without auth token) Inactive Transaction WiseFlow")
	public void Negetive_InactiveTransactionProfile() {
		int randomNumber = generateRandomNumber(1, 100000);
		AllureUtils.logStep("Inactive Transaction Profile for negetive test from API "+ApiUrls.INACTIVETRANSACTIONWISELIMITPROFILE);
		TC7 = new ApiModelZbox();
		TC7.setData(new Data());
		TC7.getData().setPayLoad(new PayLoad());
		TC7.getData().getPayLoad().setLkpAccountLevelId("1");
		TC7.getData().getPayLoad().setDailyAmtLimitCr("1");
		TC7.getData().getPayLoad().setDailyAmtLimitDr("1");
		TC7.getData().getPayLoad().setDailyTransLimitCr("1");
		TC7.getData().getPayLoad().setDailyTransLimitDr("1");
		TC7.getData().getPayLoad().setExcludeLimit("N");
		TC7.getData().getPayLoad().setLimitProfileName("Test"+randomNumber);
		TC7.getData().getPayLoad().setMonthlyAmtLimitCr("1");
		TC7.getData().getPayLoad().setMonthlyAmtLimitDr("1");
		TC7.getData().getPayLoad().setMonthlyTransLimitCr("1");
		TC7.getData().getPayLoad().setMonthlyTransLimitDr("1");
		TC7.getData().getPayLoad().setYearlyAmtLimitCr("1");
		TC7.getData().getPayLoad().setYearlyAmtLimitDr("1");
		TC7.getData().getPayLoad().setYearlyTransLimitCr("1");
		TC7.getData().getPayLoad().setYearlyTransLimitDr("1");
		List<tblTransLimitDetailRequestList> requestList = new ArrayList<tblTransLimitDetailRequestList>();
		tblTransLimitDetailRequestList request = new tblTransLimitDetailRequestList();
		request.settblProductId("10245397");
		//request.settblTransLimitId("6");
		requestList.add(request);
		TC7.getData().getPayLoad().setTblTransLimitDetailRequestList(requestList);
		String request_json_body = TestUtils.gsonString(TC7);
		Response response_create_TransactionProfile = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_TRANSACTIONWISELIMITPROFILE);
		System.out.println(response_create_TransactionProfile.asPrettyString());
		
		Map<String, Object> getHeaderList = new HashMap<String, Object>();
		getHeaderList.put("Authorization","");
		//System.out.println(getHeaderList);
		
		TC8= new ApiModelZbox();
		TC8.setData(new Data());
		TC8.getData().setPayLoad(new PayLoad());
		TC8.getData().getPayLoad().setTranslimitid(response_create_TransactionProfile.jsonPath().getString("payLoad.transLimitId"));
		TC8.getData().getPayLoad().setIsActive("N");

		String requestjsonbody = TestUtils.gsonString(TC8);
		Response response_InActivetransactionwiselimitprofiles = postApiResponse(getHeaderList, requestjsonbody, ApiUrls.GETALLTRANDACTIONWISELIMITPROFILES);
		AllureUtils.attachData("Request Body",requestjsonbody.toString());
		AllureUtils.attachData("Response Body", response_InActivetransactionwiselimitprofiles.asPrettyString());
		
		Assert.assertEquals(response_InActivetransactionwiselimitprofiles.getStatusCode(), 400);
	}
	
	
	
	@Feature("LIMITS")
	@Story("GetAllTransactionWise FLOW")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="GetAllTransactionWiseFlow",description="Negetive(without auth token) Get All Transaction WiseFlow")
	public void Negetive_Get_All_TransactionWiseLimitProfile() {
		int randomNumber = generateRandomNumber(1, 100000);
		String pname = ("Test"+randomNumber);
		AllureUtils.logStep("Inactive Transaction Profile for negetive test from API "+ApiUrls.GETALLTRANDACTIONWISELIMITPROFILES);
		TC4 = new ApiModelZbox();
		TC4.setData(new Data());
		TC4.getData().setPayLoad(new PayLoad());
		TC4.getData().getPayLoad().setLkpAccountLevelId("1");
		TC4.getData().getPayLoad().setDailyAmtLimitCr("1");
		TC4.getData().getPayLoad().setDailyAmtLimitDr("1");
		TC4.getData().getPayLoad().setDailyTransLimitCr("1");
		TC4.getData().getPayLoad().setDailyTransLimitDr("1");
		TC4.getData().getPayLoad().setExcludeLimit("N");
		TC4.getData().getPayLoad().setLimitProfileName(pname);
		TC4.getData().getPayLoad().setMonthlyAmtLimitCr("1");
		TC4.getData().getPayLoad().setMonthlyAmtLimitDr("1");
		TC4.getData().getPayLoad().setMonthlyTransLimitCr("1");
		TC4.getData().getPayLoad().setMonthlyTransLimitDr("1");
		TC4.getData().getPayLoad().setYearlyAmtLimitCr("1");
		TC4.getData().getPayLoad().setYearlyAmtLimitDr("1");
		TC4.getData().getPayLoad().setYearlyTransLimitCr("1");
		TC4.getData().getPayLoad().setYearlyTransLimitDr("1");
		List<tblTransLimitDetailRequestList> requestList = new ArrayList<tblTransLimitDetailRequestList>();
		tblTransLimitDetailRequestList request = new tblTransLimitDetailRequestList();
		request.settblProductId("10245397");
		//request.settblTransLimitId("6");
		requestList.add(request);
		TC4.getData().getPayLoad().setTblTransLimitDetailRequestList(requestList);
		String request_json_body = TestUtils.gsonString(TC4);
		Response response_create_TransactionProfile = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_TRANSACTIONWISELIMITPROFILE);
		System.out.println(response_create_TransactionProfile.asPrettyString());
		
		Map<String, Object> getHeaderList = new HashMap<String, Object>();
		getHeaderList.put("Authorization","");
		System.out.println(getHeaderList);
		
		TC5 = new ApiModelZbox();
		TC5.setData(new Data());
		TC5.getData().setPayLoad(new PayLoad());
		TC5.getData().getPayLoad().setLimitProfileName(pname);
		TC5.getData().getPayLoad().setDateFrom(GetCurrentDate());
		TC5.getData().getPayLoad().setDateTo(GetCurrentDate());
		TC5.getData().getPayLoad().setCreatedBy("");
		TC5.getData().getPayLoad().setUpdatedBy("");
		TC5.getData().getPayLoad().setStatusId("");
		String requestjsonbody = TestUtils.gsonString(TC5);
		Response response_getalltransactionwiselimitprofiles = postApiResponse(getHeaderList, requestjsonbody, ApiUrls.GETALLTRANDACTIONWISELIMITPROFILES);
		AllureUtils.attachData("Request Body",requestjsonbody.toString());
		AllureUtils.attachData("Response Body", response_getalltransactionwiselimitprofiles.asPrettyString());
		
		Assert.assertEquals(response_getalltransactionwiselimitprofiles.getStatusCode(), 400);
		//Assert.assertEquals(response_getalltransactionwiselimitprofiles.jsonPath().getString("responseCode"), "2000");
		
	}
	
}
