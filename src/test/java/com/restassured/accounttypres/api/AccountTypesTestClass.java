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
import com.restassured.models.common.ApiModelZbox;
import com.restassured.models.common.Data;
import com.restassured.models.common.PayLoad;
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
		
		AllureUtils.logStep("Step 1: Get KYC SETs from API "+ApiUrls.GET_KYC_SET);
		Response get_kyc = getApiResponse(getHeaderList, ApiUrls.GET_KYC_SET);
		Assert.assertNotNull(get_kyc.jsonPath().getString("payLoad.lovId[1]"), "payLoad.lovId Should not be null");
		
		AllureUtils.logStep("Step 2: Get client Role ID from API "+ApiUrls.GET_CLIENT_ROLES);
		Response get_client_roles = getApiResponse(getHeaderList, ApiUrls.GET_CLIENT_ROLES);
		Assert.assertNotNull(get_client_roles.jsonPath().getString("payLoad.lovId[0]"),"payLoad.lovId Should not be null");
				
		AllureUtils.logStep("Step 3: Create New Account Type using API "+ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());
		api_body_skle.getData().getPayLoad().setLkpAccountClassificationId(get_client_roles.jsonPath().getString("payLoad.lovId[0]"));
		api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 0"+TestUtils.getRandomNum());
		api_body_skle.getData().getPayLoad().setKycSetHeadId(get_kyc.jsonPath().getString("payLoad.lovId[1]"));
		//api_body_skle.getData().getPayLoad().setKycSetHeadId("5");
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
		api_body_skle.getData().getPayLoad().setMaxAmtLimit("200000");
		String request_json_body = TestUtils.gsonString(api_body_skle);
		System.out.println(request_json_body.toString());
		Response response_create_Account = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		api_body_skle.getData().getPayLoad().setAccountLevelId(response_create_Account.jsonPath().getString("payLoad.accountLevelId"));
		api_body_skle.getData().getPayLoad().setCreatedBy(response_create_Account.jsonPath().getString("payLoad.createuser"));	
		
		AllureUtils.attachData("request Body", request_json_body.toString());
		AllureUtils.logStep("Step 4: Verifying the Response of Create new account type API "+ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		AllureUtils.attachData("Response Body", response_create_Account.asPrettyString());
		assertions(response_create_Account, api_body_skle);
		
		AllureUtils.logStep("Step 5: Verify the newly created account type using get Account Type API "+ApiUrls.GET_ALL_ACCOUNT_TYPES);
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
		Assert.assertEquals(response_get_all_accounttypes.jsonPath().getString("responseCode"), "010000");
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
		AllureUtils.attachData("Get API Response Body", target_json_object.toString());
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
		Assert.assertEquals(target_json_object.get("maxAmtLimit").toString(), api_body_skle.getData().getPayLoad().getMaxAmtLimit());
		
		Assert.assertNotNull(lkpStatusObject.get("statusId").toString(),"lkpStatus.statusId should not be null");
		Assert.assertNotNull(lkpStatusObject.get("createdate").toString(),"accountClassificationCode should not be null");
		Assert.assertNotNull(lkpStatusObject.get("createuser").toString(),"createuser should not be null");
		Assert.assertTrue(lkpStatusObject.get("isActive").toString().equals("Y") || target_json_object.get("isActive").toString().equals("N"));
	
		Assert.assertEquals(lkpAccountClassificationObject.get("accountClassificationId").toString(), api_body_skle.getData().getPayLoad().getLkpAccountClassificationId());
		Assert.assertNotNull(lkpAccountClassificationObject.get("accountClassificationCode").toString(),"accountClassificationCode should not be null");
		Assert.assertNotNull(lkpAccountClassificationObject.get("createdate").toString(),"accountClassificationCode should not be null");
		Assert.assertNotNull(lkpAccountClassificationObject.get("createuser").toString(),"createuser should not be null");
		Assert.assertTrue(lkpAccountClassificationObject.get("isActive").toString().equals("Y") || target_json_object.get("isActive").toString().equals("N"));
		
		Assert.assertEquals(tblKycSetHeadObject.get("kycSetHeadId").toString(), api_body_skle.getData().getPayLoad().getKycSetHeadId());
		Assert.assertNotNull(tblKycSetHeadObject.get("createdate").toString(), "kycSetHeadId.createdate Should not be null");
		Assert.assertNotNull(tblKycSetHeadObject.get("createuser").toString(), "kycSetHeadId.createuser Should not be null");
		
	}

	@Feature("Account Type")
	@Story("Account Type Positive Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type",description="Create account type and update account type status to In-active")
	public void create_and_update_account_type() {
		
		AllureUtils.logStep("Step 1: Get KYC SETs from API "+ApiUrls.GET_KYC_SET);
		Response get_kyc = getApiResponse(getHeaderList, ApiUrls.GET_KYC_SET);
		Assert.assertNotNull(get_kyc.jsonPath().getString("payLoad.lovId[1]"), "payLoad.lovId Should not be null");
		
		AllureUtils.logStep("Step 2: Get client Role ID from API "+ApiUrls.GET_CLIENT_ROLES);
		Response get_client_roles = getApiResponse(getHeaderList, ApiUrls.GET_CLIENT_ROLES);
		Assert.assertNotNull(get_client_roles.jsonPath().getString("payLoad.lovId[0]"),"payLoad.lovId Should not be null");
		
		AllureUtils.logStep("Step 3: Create New Account Type using API"+ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());		
		api_body_skle.getData().getPayLoad().setLkpAccountClassificationId(get_client_roles.jsonPath().getString("payLoad.lovId[0]"));
		api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 1"+TestUtils.getRandomNum());
		api_body_skle.getData().getPayLoad().setKycSetHeadId(get_kyc.jsonPath().getString("payLoad.lovId[1]"));
		//api_body_skle.getData().getPayLoad().setKycSetHeadId("5");
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
		api_body_skle.getData().getPayLoad().setMaxAmtLimit("200000");
		String request_json_body = TestUtils.gsonString(api_body_skle);
		System.out.println(request_json_body.toString());
		Response response_create_Account = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		AllureUtils.attachData("request Body", request_json_body.toString());
		
		AllureUtils.logStep("Step 4: Verifying the Response of new account type");
		AllureUtils.attachData("Response Body", response_create_Account.asPrettyString());	
		assertions(response_create_Account, api_body_skle);
		api_body_skle.getData().getPayLoad().setAccountLevelId(response_create_Account.jsonPath().getString("payLoad.accountLevelId"));
		
		AllureUtils.logStep("Step 5: Updating the account type Status to In-active using API "+ApiUrls.INACTIVE_ACCOUNT_TYPE);
		ApiModelZbox api_body_skle_update = new ApiModelZbox();
		api_body_skle_update.setData(new Data());
		api_body_skle_update.getData().setPayLoad(new PayLoad());		
		api_body_skle_update.getData().getPayLoad().setAccountLevelId(api_body_skle.getData().getPayLoad().getAccountLevelId());
		api_body_skle_update.getData().getPayLoad().setIsActive("N");
		String update_request_json_body = TestUtils.gsonString(api_body_skle_update);		
		AllureUtils.attachData("Request Body", update_request_json_body.toString());
		Response inactive_api_response = negativePostApiResponse(getHeaderList, update_request_json_body, ApiUrls.INACTIVE_ACCOUNT_TYPE);
		AllureUtils.attachData("Response Body", inactive_api_response.toString());
		
		AllureUtils.logStep("Step 6: Verify the response");
		Assert.assertEquals(inactive_api_response.getStatusCode(), 400);
		Assert.assertEquals(inactive_api_response.jsonPath().getString("responseCode"), "011301");
		//Assert.assertEquals(inactive_api_response.jsonPath().getString("payLoad.isActive"), "N");
		//assertions(inactive_api_response, api_body_skle); //Assertion function when maker checker is not active
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
        Assert.assertEquals(payloadList.size(), getSearchCountFromDb().size());      
        for(int i=0;i<payloadList.size();i++) {
            Map<String, Object> payloadObject = payloadList.get(i);
            Assert.assertNotNull(payloadObject.get("createdate").toString(), "isActive attribute is null in payLoad object at index " + i);
            Assert.assertTrue(TestUtils.isWithinRange(payloadObject.get("createdate").toString(),
            		api_body_skle.getData().getPayLoad().getDateFrom(),
            		api_body_skle.getData().getPayLoad().getDateTo()),"Date is not in rage at index "+i);
    		Assert.assertNotNull(payloadObject.get("accountLevelId").toString(),"accountLevelId Should be null");
        }
	}
	
	@Feature("Account Type")
	@Story("Account Type Positive Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type",description="Verify that already created account type is updating")
	public void update_existing_account_type() {
		
		AllureUtils.logStep("Step 1: Get KYC SETs from API "+ApiUrls.GET_KYC_SET);
		Response get_kyc = getApiResponse(getHeaderList, ApiUrls.GET_KYC_SET);
		Assert.assertNotNull(get_kyc.jsonPath().getString("payLoad.lovId"), "payLoad.lovId Should not be null");
	
		AllureUtils.logStep("Step 2: Get client Role ID from API "+ApiUrls.GET_CLIENT_ROLES);
		Response get_client_roles = getApiResponse(getHeaderList, ApiUrls.GET_CLIENT_ROLES);
		Assert.assertNotNull(get_client_roles.jsonPath().getString("payLoad.lovId"),"payLoad.lovId Should not be null");
		
		AllureUtils.logStep("Step 3: Create New Account Type using below Body of API"+ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());
		api_body_skle.getData().getPayLoad().setLkpAccountClassificationId(get_client_roles.jsonPath().getString("payLoad.lovId[0]"));
		api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 0"+TestUtils.getRandomNum());
		//api_body_skle.getData().getPayLoad().setKycSetHeadId(get_kyc.jsonPath().getString("payLoad.lovId[1]"));
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
		api_body_skle.getData().getPayLoad().setMaxAmtLimit("200000");
		String request_json_body = TestUtils.gsonString(api_body_skle);
		System.out.println(request_json_body.toString());
		Response response_create_Account = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		api_body_skle.getData().getPayLoad().setAccountLevelId(response_create_Account.jsonPath().getString("payLoad.accountLevelId"));
		api_body_skle.getData().getPayLoad().setCreatedBy(response_create_Account.jsonPath().getString("payLoad.createuser"));
		
		AllureUtils.attachData("request Body for API "+ApiUrls.CREATE_NEW_ACCOUNT_TYPE, request_json_body.toString());
		AllureUtils.logStep("Step 4: Verifying the Response of Create new account type API "+ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		AllureUtils.attachData("Response Body of API"+ApiUrls.CREATE_NEW_ACCOUNT_TYPE, response_create_Account.asPrettyString());
		assertions(response_create_Account, api_body_skle);
		
		AllureUtils.logStep("Step 5: Get account type By id by API "+ApiUrls.GET_ACCOUNT_TYPE_BY_ID);
		Response response_get_acctype_by_id = getApiResponse(getHeaderList, ApiUrls.GET_ACCOUNT_TYPE_BY_ID+api_body_skle.getData().getPayLoad().getAccountLevelId());
		AllureUtils.attachData("Response Body of API"+ApiUrls.GET_ACCOUNT_TYPE_BY_ID, response_get_acctype_by_id.asPrettyString());
		Assert.assertEquals(response_get_acctype_by_id.jsonPath().getString("payLoad.accountLevelId"), api_body_skle.getData().getPayLoad().getAccountLevelId());
		
		AllureUtils.logStep("Step 6: Verify Response of API "+ApiUrls.GET_ACCOUNT_TYPE_BY_ID);
		assertions(response_get_acctype_by_id, api_body_skle);
		api_body_skle.getData().getPayLoad().setCreatedBy(null);
		String request__body = TestUtils.gsonString(api_body_skle);
		System.out.println(request__body.toString());
		
		AllureUtils.logStep("Step 7: Updating Account type using API "+ApiUrls.Update_EXISTING_ACCOUNT_TYPE);
		Response update_acctype_by_id = negativePostApiResponse(getHeaderList, request__body, ApiUrls.Update_EXISTING_ACCOUNT_TYPE);
		AllureUtils.attachData("Request Body of API"+ApiUrls.Update_EXISTING_ACCOUNT_TYPE, request__body.toString());
		AllureUtils.attachData("Response Body of API"+ApiUrls.Update_EXISTING_ACCOUNT_TYPE, update_acctype_by_id.asPrettyString());

		AllureUtils.logStep("Step 8: Verifying Response of API "+ApiUrls.Update_EXISTING_ACCOUNT_TYPE);
		Assert.assertEquals(update_acctype_by_id.getStatusCode(), 400);
		Assert.assertEquals(update_acctype_by_id.jsonPath().getString("responseCode"), "011301");
		//Assert.assertEquals(update_acctype_by_id.jsonPath().getString("This record is already pending for approval"), "This record is already pending for approval");
		//Assert.assertEquals(false, null);
	}
	
	@Feature("Account Type")
	@Story("Account Type Negative Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type negative_accountType",description="Verify that create API should not respond when bearer token is not provided")
	public void negative_create_new_account_type() {
		
			
			Map<String, Object> getHeaderList = new HashMap<String, Object>();
			getHeaderList.put("Authorization","");
			
			AllureUtils.logStep("Step 1: Hit Create Account Type API with empty token  "+ApiUrls.CREATE_NEW_ACCOUNT_TYPE);

			ApiModelZbox api_body_skle = new ApiModelZbox();
			api_body_skle.setData(new Data());
			api_body_skle.getData().setPayLoad(new PayLoad());
			api_body_skle.getData().getPayLoad().setLkpAccountClassificationId("1");
			api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 0"+TestUtils.getRandomNum());
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
			api_body_skle.getData().getPayLoad().setMaxAmtLimit("200000");
			String request_json_body = TestUtils.gsonString(api_body_skle);
			System.out.println(request_json_body.toString());
			Response response_create_Account = negativePostApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
			
			AllureUtils.logStep("Step 2: Verifying the Response of API "+ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
			AllureUtils.attachData("Response Body", response_create_Account.asPrettyString());
			Assert.assertEquals(response_create_Account.getStatusCode(), 400);
			Assert.assertEquals(response_create_Account.jsonPath().getString("responseCode"), "012000");
	}

	@Feature("Account Type")
	@Story("Account Type Negative Testcases")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type negative_accountType",description="Verify that get API should not respond when bearer token is not provided")
	public void negative_getall_account_types() {
		
		AllureUtils.logStep("Step 1: Hit Create Account Type API with empty token  "+ApiUrls.GET_ALL_ACCOUNT_TYPES);
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
		AllureUtils.logStep("Step 2: Verifying the Response of API"+ApiUrls.GET_ALL_ACCOUNT_TYPES);
		AllureUtils.attachData("Response Body", response_get_all_accounttypes.asPrettyString());
		Assert.assertEquals(response_get_all_accounttypes.getStatusCode(), 400,"The Status code should be 400");
		Assert.assertEquals(response_get_all_accounttypes.jsonPath().getString("responseCode"), "012000");
	}
	
	public void assertions(Response apiResponse, ApiModelZbox api_body_skle) {
		
		Assert.assertEquals(apiResponse.jsonPath().getString("responseCode"), "010000");
		Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.accountLevelId"),"accountLevelId value should not be null or empty");
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.accountLevelCode"),"accountLevelCode Attribute value should not be null");
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.createdate"), "createdate value should not be null or empty");
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
		Assert.assertEquals(apiResponse.jsonPath().getString("payLoad.maxAmtLimit"), api_body_skle.getData().getPayLoad().getMaxAmtLimit());
		
		Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.lkpStatus.statusId"),"lkpStatus.statusId Attribute value should not be null");
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.lkpStatus.createdate"),"lkpStatus.createdate Attribute value should not be null");
		//Assert.assertTrue(TestUtils.isDateFormatValid(apiResponse.jsonPath().getString("payLoad.lkpStatus.createdate")),"Invalid Date Formate of lkpStatus.createdate"+apiResponse.jsonPath().getString("payLoad.createdate"));
		//Assert.assertNotNull(apiResponse.jsonPath().getString("payLoad.lkpStatus.createuser"),"lkpStatus.createuser Attribute value should not be null");

		
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
