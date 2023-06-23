package com.restassured.zindgi.testcases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.constants.ApiUrls;
import com.restassured.models.zindagi.CustomerLimits;
import com.restassured.utils.APIHeaders;
import com.restassured.utils.ConfigManager;
import com.restassured.utils.TestUtils;

import io.qameta.allure.Step;

public class Z2ZTransfer extends Z2ZTransferBaseClass {

	@Test
	public void Z2ZTransaction() throws Exception {
		
		ConfigManager getKey = new ConfigManager();
		APIHeaders getHeadersList = new APIHeaders();
		Map<String, Object> getHeaderList = new HashMap<String, Object>();
		CustomerLimits customer_limit_obj = new CustomerLimits();
		customer_limit_obj.setAccountNumber("03475375068");
		getHeaderList = getHeadersList.GetHeadersForLogin("");
		getHeaderList.remove("Authorization");
		getHeaderList.put("Authorization", "Bearer " + LoginObj.getInstance().loginSession.get(0));
		getHeaderList.remove("USER");
		getHeaderList.put("USER",TestUtils.Encrypt(LoginObj.getInstance().loginSession.get(1),getKey.getKeyValue("EncKey1Android")));
		String response = postApiRequest(getHeaderList, customer_limit_obj,ApiUrls.MOVE_MONEY_MENU);
		ObjectMapper map = new ObjectMapper();
        JsonNode node = map.readTree(response);
        Assert.assertTrue(Integer.parseInt(node.get("DailyRemainingDebitLimit").toString()) > 0);
        Assert.assertTrue(Integer.parseInt(node.get("DailyRemainingCreditLimit").toString()) > 0);
		
	}
	
}
