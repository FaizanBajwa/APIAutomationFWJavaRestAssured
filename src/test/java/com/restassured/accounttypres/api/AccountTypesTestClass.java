package com.restassured.accounttypres.api;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
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
import com.restassured.utils.ConfigManager;
import com.restassured.utils.TestUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class AccountTypesTestClass extends AccountTypesBaseClass {
	
	private Map<String, Object> getHeaderList;
	
	private ConfigManager getKey;

	@BeforeMethod
	public void beforeMethod() {
		try {
			getKey = new ConfigManager();
			getHeaderList = new HashMap<String, Object>();
			getHeaderList.put("Authorization",getKey.getKeyValue("AUTH_TOKEN"));		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Feature("Account Type")
	@Story("Account Type Positive Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type",description="Create account and verify that created account from get accounts types API")
	public void create_new_account_type() {	
		
		AllureUtils.logStep("Get KYC SETs from API "+ApiUrls.GET_KYC_SET);
		Response get_kyc = getApiResponse(getHeaderList, ApiUrls.GET_KYC_SET);
		Assert.assertNotNull(get_kyc.jsonPath().getString("payLoad.lovId"), "payLoad.lovId Should not be null");
		
		AllureUtils.logStep("Get client Role ID from API "+ApiUrls.GET_CLIENT_ROLES);
		Response get_client_roles = getApiResponse(getHeaderList, ApiUrls.GET_CLIENT_ROLES);
		Assert.assertNotNull(get_client_roles.jsonPath().getString("payLoad.lovId"),"payLoad.lovId Should not be null");
				
		AllureUtils.logStep("Create New Account Type using below Body and API"+ApiUrls.GET_CLIENT_ROLES);
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());
		api_body_skle.getData().getPayLoad().setLkpAccountClassificationId(get_client_roles.jsonPath().getString("payLoad.lovId[0]"));
		api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 0");
		//api_body_skle.getData().getPayLoad().setKycSetHeadId(get_kyc.jsonPath().getString("payLoad.lovId[11]"));
		api_body_skle.getData().getPayLoad().setKycSetHeadId("5");
		api_body_skle.getData().getPayLoad().setDailyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitDr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitDr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitDr("200000");
		api_body_skle.getData().getPayLoad().setDailyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitCr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitCr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitCr("200000");
		String request_json_body = TestUtils.gsonString(api_body_skle);
		System.out.println(request_json_body.toString());
		Response response_create_Account = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		api_body_skle.getData().getPayLoad().setAccountLevelId(response_create_Account.jsonPath().getString("payLoad.accountLevelId"));
		api_body_skle.getData().getPayLoad().setCreatedBy(response_create_Account.jsonPath().getString("payLoad.createuser"));
		assertions(response_create_Account, api_body_skle);
		AllureUtils.attachData("request Body", request_json_body.toString());
		AllureUtils.attachData("Response Body", response_create_Account.asPrettyString());
		
		ApiModelZbox api_body_skle_getall_accounts = new ApiModelZbox();
		api_body_skle_getall_accounts.setData(new Data());
		api_body_skle_getall_accounts.getData().setPayLoad(new PayLoad());
		api_body_skle_getall_accounts.getData().getPayLoad().setAccountClassificationId(get_client_roles.jsonPath().getString("payLoad.lovId[0]"));
		api_body_skle_getall_accounts.getData().getPayLoad().setDateFrom("");
		api_body_skle_getall_accounts.getData().getPayLoad().setDateTo("");
		api_body_skle_getall_accounts.getData().getPayLoad().setCreatedBy("");
		api_body_skle_getall_accounts.getData().getPayLoad().setUpdatedBy("");
		String json_string = TestUtils.gsonString(api_body_skle_getall_accounts);
		
		Response response_get_all_accounttypes = postApiResponse(getHeaderList, json_string, ApiUrls.GET_ALL_ACCOUNT_TYPES);
        String response_body_string = response_get_all_accounttypes.getBody().asString();
		JsonPath jsonPath = JsonPath.from(response_body_string);
		List<Map<String, Object>> json_array = jsonPath.getList("payLoad");
		int target_accountLevelId =Integer.parseInt(api_body_skle.getData().getPayLoad().getAccountLevelId());
		Map<String, Object> target_json_object = null;
		Map<String, Object> lkpStatusObject = null;
		Map<String, Object> lkpAccountClassificationObject = null;
		Map<String, Object> tblKycSetHeadObject = null;
		for (Map<String, Object> json_object : json_array) {
		    int mcConfigId =  (Integer) json_object.get("accountLevelId");
		    if (mcConfigId == target_accountLevelId) {
		    	target_json_object = json_object;
	            lkpStatusObject = (Map<String, Object>) target_json_object.get("lkpStatus");
	            lkpAccountClassificationObject = (Map<String, Object>) target_json_object.get("lkpAccountClassification"); 
	            tblKycSetHeadObject = (Map<String, Object>) target_json_object.get("tblKycSetHead"); 
		        break;
		    }
		}
		
		Assert.assertEquals(target_accountLevelId, target_json_object.get("accountLevelId"));
		//Assert.assertNotNull(target_json_object.get("accountLevelCode"),"accountLevelCode should not be null");
		Assert.assertNotNull(target_json_object.get("createdate"), "createdate should not be null");
		//Assert.assertTrue(TestUtils.isDateFormatValid(target_json_object.get("createdate").toString()),"Invalid Date Formate of tblKycSetHead.createdate"+target_json_object.get("createdate").toString());
		Assert.assertEquals(target_json_object.get("createuser").toString(),api_body_skle.getData().getPayLoad().getCreatedBy());
		Assert.assertEquals(target_json_object.get("dailyAmtLimitCr").toString(),api_body_skle.getData().getPayLoad().getDailyAmtLimitCr() );
		Assert.assertEquals(target_json_object.get("dailyAmtLimitDr").toString(), api_body_skle.getData().getPayLoad().getDailyAmtLimitDr());
		Assert.assertEquals(target_json_object.get("dailyTransLimitCr").toString(), api_body_skle.getData().getPayLoad().getDailyTransLimitCr());
		Assert.assertEquals(target_json_object.get("dailyTransLimitDr").toString(), api_body_skle.getData().getPayLoad().getDailyTransLimitDr());
		Assert.assertTrue(target_json_object.get("isActive").toString().equals("Y") || target_json_object.get("isActive").toString().equals("N"));
		Assert.assertEquals(target_json_object.get("monthlyAmtLimitCr").toString(), api_body_skle.getData().getPayLoad().getMonthlyAmtLimitCr());
		Assert.assertEquals(target_json_object.get("monthlyAmtLimitDr").toString(), api_body_skle.getData().getPayLoad().getMonthlyAmtLimitDr());
		Assert.assertEquals(target_json_object.get("monthlyTransLimitCr").toString(), api_body_skle.getData().getPayLoad().getMonthlyTransLimitCr());
		Assert.assertEquals(target_json_object.get("monthlyTransLimitDr").toString(), api_body_skle.getData().getPayLoad().getMonthlyTransLimitDr());
		Assert.assertEquals(target_json_object.get("yearlyAmtLimitCr").toString(), api_body_skle.getData().getPayLoad().getYearlyAmtLimitCr());
		Assert.assertEquals(target_json_object.get("yearlyAmtLimitDr").toString(), api_body_skle.getData().getPayLoad().getYearlyAmtLimitDr());
		Assert.assertEquals(target_json_object.get("yearlyTransLimitCr").toString(), api_body_skle.getData().getPayLoad().getYearlyTransLimitCr());
		Assert.assertEquals(target_json_object.get("yearlyTransLimitDr").toString(), api_body_skle.getData().getPayLoad().getYearlyTransLimitDr());
		
		Assert.assertNotNull(lkpStatusObject.get("statusId").toString(),"lkpStatus.statusId should not be null" );
		Assert.assertNotNull(lkpStatusObject.get("createdate").toString(),"accountClassificationCode should not be null");
		Assert.assertNotNull(lkpStatusObject.get("createuser").toString(),"createuser should not be null");
		Assert.assertTrue(lkpStatusObject.get("isActive").toString().equals("Y") || target_json_object.get("isActive").toString().equals("N"));
	
		Assert.assertEquals(lkpAccountClassificationObject.get("accountClassificationId").toString(), api_body_skle.getData().getPayLoad().getLkpAccountClassificationId());
		Assert.assertNotNull(lkpAccountClassificationObject.get("accountClassificationCode").toString(),"accountClassificationCode should not be null");
		Assert.assertNotNull(lkpAccountClassificationObject.get("createdate").toString(),"accountClassificationCode should not be null");
		Assert.assertNotNull(lkpAccountClassificationObject.get("createuser").toString(),"createuser should not be null");
		Assert.assertTrue(lkpAccountClassificationObject.get("isActive").toString().equals("Y") || target_json_object.get("isActive").toString().equals("N"));
		
		Assert.assertEquals(tblKycSetHeadObject.get("kycSetHeadId").toString(), api_body_skle.getData().getPayLoad().getKycSetHeadId());
		
	}

	@Feature("Account Type")
	@Story("Account Type Positive Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type",description="Create account type and update account type status to In-active")
	public void create_and_update_account_type() {
		
		Response get_kyc = getApiResponse(getHeaderList, ApiUrls.GET_KYC_SET);
		Assert.assertNotNull(get_kyc.jsonPath().getString("payLoad.lovId"), "payLoad.lovId Should not be null");
		
		Response get_client_roles = getApiResponse(getHeaderList, ApiUrls.GET_CLIENT_ROLES);
		Assert.assertNotNull(get_client_roles.jsonPath().getString("payLoad.lovId"),"payLoad.lovId Should not be null");
		
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());		
		api_body_skle.getData().getPayLoad().setLkpAccountClassificationId(get_client_roles.jsonPath().getString("payLoad.lovId[0]"));
		api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 00");
		//api_body_skle.getData().getPayLoad().setKycSetHeadId(get_kyc.jsonPath().getString("payLoad.lovId[11]"));
		api_body_skle.getData().getPayLoad().setKycSetHeadId("5");
		api_body_skle.getData().getPayLoad().setDailyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitDr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitDr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitDr("200000");
		api_body_skle.getData().getPayLoad().setDailyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitCr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitCr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitCr("200000");					
		String request_json_body = TestUtils.gsonString(api_body_skle);
		System.out.println(request_json_body.toString());
		Response response_create_Account = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		assertions(response_create_Account, api_body_skle);
		api_body_skle.getData().getPayLoad().setAccountLevelId(response_create_Account.jsonPath().getString("payLoad.accountLevelId"));
		ApiModelZbox api_body_skle_update = new ApiModelZbox();
		api_body_skle_update.setData(new Data());
		api_body_skle_update.getData().setPayLoad(new PayLoad());		
		api_body_skle_update.getData().getPayLoad().setAccountLevelId(api_body_skle.getData().getPayLoad().getAccountLevelId());
		api_body_skle_update.getData().getPayLoad().setIsActive("N");
		String update_request_json_body = TestUtils.gsonString(api_body_skle_update);		
		Response inactive_api_response = postApiResponse(getHeaderList, update_request_json_body, ApiUrls.INACTIVE_ACCOUNT_TYPE);
		Assert.assertEquals(inactive_api_response.jsonPath().getString("payLoad.isActive"), "N");
		assertions(inactive_api_response, api_body_skle);
	}

	@Feature("Account Type")
	@Story("Account Type Positive Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type",description="Verify Mandatory Filters on Get all account types API")
	public void get_all_account_types() {
		
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());
		api_body_skle.getData().getPayLoad().setAccountClassificationId("1");
		api_body_skle.getData().getPayLoad().setDateFrom("2023-07-01");
		api_body_skle.getData().getPayLoad().setDateTo("2023-07-05");
		api_body_skle.getData().getPayLoad().setCreatedBy("");
		api_body_skle.getData().getPayLoad().setUpdatedBy("");
		String request_json_body = TestUtils.gsonString(api_body_skle);
		
		Response response_get_all_accounttypes = postApiResponse(getHeaderList, request_json_body, ApiUrls.GET_ALL_ACCOUNT_TYPES);
        String jsonString = response_get_all_accounttypes.getBody().asString();
        System.out.println("DATA==> "+jsonString);
        JsonPath jsonPath = new JsonPath(jsonString);
        List<Map<String, Object>> payloadList = jsonPath.getList("payLoad"); 
        System.out.println(payloadList.size());
        
        
//        for(int i=0;i<payloadList.size();i++) {
//            Map<String, Object> payloadObject = payloadList.get(i);
//            Assert.assertNotNull(payloadObject.get("createdate").toString(), "isActive attribute is null in payLoad object at index " + i);
//            System.out.println(payloadObject.get("createdate").toString());
//            Assert.assertEquals(payloadObject.get("").toString(), null);
//            Assert.assertTrue(TestUtils.isWithinRange(payloadObject.get("createdate").toString(),
//            		api_body_skle.getData().getPayLoad().getDateFrom(),
//            		api_body_skle.getData().getPayLoad().getDateTo()),"Date is not in rage at index "+i);
//    		Assert.assertNotNull(payloadObject.get("accountLevelId").toString(),"accountLevelId Should be null");
//        }
	}
		
	@Feature("Account Type")
	@Story("Account Type Negative Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type negative_accountType",description="Verify that create API should not respond when bearer token is not provided")
	public void negative_create_new_account_type() {
		
			Map<String, Object> getHeaderList = new HashMap<String, Object>();
			getHeaderList.put("Authorization","");
			
			ApiModelZbox api_body_skle = new ApiModelZbox();
			api_body_skle.setData(new Data());
			api_body_skle.getData().setPayLoad(new PayLoad());
			api_body_skle.getData().getPayLoad().setLkpAccountClassificationId("1");
			api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 0");
			api_body_skle.getData().getPayLoad().setKycSetHeadId("5");
			api_body_skle.getData().getPayLoad().setDailyTransLimitDr("25000");
			api_body_skle.getData().getPayLoad().setDailyAmtLimitDr("25000");
			api_body_skle.getData().getPayLoad().setMonthlyTransLimitDr("25000");
			api_body_skle.getData().getPayLoad().setMonthlyAmtLimitDr("50000");
			api_body_skle.getData().getPayLoad().setYearlyTransLimitDr("200000");
			api_body_skle.getData().getPayLoad().setYearlyAmtLimitDr("200000");
			api_body_skle.getData().getPayLoad().setDailyTransLimitCr("25000");
			api_body_skle.getData().getPayLoad().setDailyAmtLimitCr("25000");
			api_body_skle.getData().getPayLoad().setMonthlyTransLimitCr("25000");
			api_body_skle.getData().getPayLoad().setMonthlyAmtLimitCr("50000");
			api_body_skle.getData().getPayLoad().setYearlyTransLimitCr("200000");
			api_body_skle.getData().getPayLoad().setYearlyAmtLimitCr("200000");
			String request_json_body = TestUtils.gsonString(api_body_skle);
			System.out.println(request_json_body.toString());
			Response response_create_Account = negativePostApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
			Assert.assertEquals(response_create_Account.getStatusCode(), 400);
			Assert.assertEquals(response_create_Account.jsonPath().getString("responseCode"), "2000");
	}

	@Feature("Account Type")
	@Story("Account Type Negative Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type negative_accountType",description="Verify that get API should not respond when bearer token is not provided")
	public void negative_getall_account_types() {
		Map<String, Object> getHeaderList = new HashMap<String, Object>();
		getHeaderList.put("Authorization","");	
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());
		api_body_skle.getData().getPayLoad().setAccountClassificationId("1");
		api_body_skle.getData().getPayLoad().setDateFrom("2023-07-01");
		api_body_skle.getData().getPayLoad().setDateTo("2023-07-05");
		api_body_skle.getData().getPayLoad().setCreatedBy("");
		api_body_skle.getData().getPayLoad().setUpdatedBy("");
		String request_json_body = TestUtils.gsonString(api_body_skle);	
		Response response_get_all_accounttypes = negativePostApiResponse(getHeaderList, request_json_body, ApiUrls.GET_ALL_ACCOUNT_TYPES);
		Assert.assertEquals(response_get_all_accounttypes.getStatusCode(), 400,"The Status code should be 400");
		Assert.assertEquals(response_get_all_accounttypes.jsonPath().getString("responseCode"), "2000");
	}
	
	
	public void update_existing_account_type() {
		
	}
	
	
	public void assertions(Response apiResponse, ApiModelZbox api_body_skle) {
		
		Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.accountLevelId"),"accountLevelId value should not be null or empty");
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.accountLevelCode"),"accountLevelCode Attribute value should not be null");
		Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.createdate"), "createdate value should not be null or empty");
		//Assert.assertTrue(TestUtils.isDateFormatValid(apiResponse.jsonPath().getString("payLoad.createdate")),"Invalid Date Formate of payLoad.createdate"+apiResponse.jsonPath().getString("payLoad.createdate"));
		Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.createuser"), "Attribute value should not be null");
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.dailyAmtLimitCr"), api_body_skle.getData().getPayLoad().getDailyAmtLimitCr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.dailyAmtLimitDr"), api_body_skle.getData().getPayLoad().getDailyAmtLimitDr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.dailyTransLimitCr"), api_body_skle.getData().getPayLoad().getDailyTransLimitCr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.dailyTransLimitDr"), api_body_skle.getData().getPayLoad().getDailyTransLimitDr());
		Assert.assertTrue(apiResponse.jsonPath().getString("payLoad.isActive").equals("Y") || apiResponse.jsonPath().getString("payLoad.isActive").equals("N"));
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.monthlyAmtLimitCr"), api_body_skle.getData().getPayLoad().getMonthlyAmtLimitCr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.monthlyAmtLimitDr"), api_body_skle.getData().getPayLoad().getMonthlyAmtLimitDr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.monthlyTransLimitCr"), api_body_skle.getData().getPayLoad().getMonthlyTransLimitCr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.monthlyTransLimitDr"), api_body_skle.getData().getPayLoad().getMonthlyTransLimitDr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.yearlyAmtLimitCr"), api_body_skle.getData().getPayLoad().getYearlyAmtLimitCr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.yearlyAmtLimitDr"), api_body_skle.getData().getPayLoad().getYearlyAmtLimitDr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.yearlyTransLimitCr"), api_body_skle.getData().getPayLoad().getYearlyTransLimitCr());
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.yearlyTransLimitDr"), api_body_skle.getData().getPayLoad().getYearlyTransLimitDr());		
		Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.lkpStatus.statusId"),"Attribute value should not be null");	
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.lkpAccountClassification.accountClassificationId"), api_body_skle.getData().getPayLoad().getLkpAccountClassificationId());
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.lkpAccountClassification.accountClassificationCode"), "Attribute value should not be null");
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.lkpAccountClassification.createdate"), "lkpAccountClassification.createdate Should not be null");
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.lkpAccountClassification.createuser"), "lkpAccountClassification.createuser should not be null");
		//Assert.assertTrue(apiResponse.jsonPath().getString("payLoad.lkpAccountClassification.isActive").equals("Y") || apiResponse.jsonPath().getString("payLoad.lkpAccountClassification.isActive").equals("N"));		
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.tblKycSetHead.kycSetHeadId"), api_body_skle.getData().getPayLoad().getKycSetHeadId());
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.tblKycSetHead.createdate"),"tblKycSetHead.createdate value should not be null");
		//Assert.assertTrue(TestUtils.isDateFormatValid(apiResponse.jsonPath().getString("payLoad.tblKycSetHead.createdate")),"Invalid Date Formate of tblKycSetHead.createdate"+apiResponse.jsonPath().getString("payLoad.createdate"));
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.tblKycSetHead.createuser"), "tblKycSetHead.createuser value should not be null");			
		Assert.assertNull(apiResponse.jsonPath().getString("errors"), "Following error is showing on error attribute"+apiResponse.jsonPath().getString("errors"));
	}

}
