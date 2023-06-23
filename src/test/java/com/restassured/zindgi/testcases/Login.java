package com.restassured.zindgi.testcases;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.constants.ApiUrls;
import com.restassured.models.zindagi.LoginAuth;
import com.restassured.models.zindagi.LoginReqDto;
import com.restassured.utils.APIHeaders;
import com.restassured.utils.ConfigManager;
import com.restassured.utils.TestUtils;

import io.restassured.response.Response;

public class Login {

	public static List<String> login() throws Exception{
	
			ConfigManager getKey = new ConfigManager();
			LoginAuth user_authentication = new LoginAuth();
			LoginReqDto reqDTO_login = new LoginReqDto();
			APIHeaders getHeadersList = new APIHeaders();
			
			
			getKey.getKeyValue("MSIDN");
			
			reqDTO_login.setAndroidVersion(getKey.getKeyValue("androidVersion"));
	        reqDTO_login.setAppVersion(getKey.getKeyValue("appVersion"));
	        reqDTO_login.setBuildVersion(Integer.valueOf(getKey.getKeyValue("buildVersion")));
	        reqDTO_login.setDeviceName(getKey.getKeyValue("deviceName"));
	        reqDTO_login.setFirebaseToken(getKey.getKeyValue("firebaseToken"));
	        reqDTO_login.setImei(getKey.getKeyValue("imei"));
	        reqDTO_login.setMobileNo(getKey.getKeyValue("MSISDN"));
	        reqDTO_login.setEnableFingerPrint(Boolean.parseBoolean(getKey.getKeyValue("EnableFingerPrint")));
	        
	        String user_OTP = getKey.getKeyValue("PIN");
	        String encrypted_OTP = TestUtils.RSAEncode(getKey.getKeyValue("base64PublicKey"), user_OTP);
	        reqDTO_login.setOtpPin(encrypted_OTP);
	        
	        String jsonBody = TestUtils.gsonString(reqDTO_login);
	        
	        //System.out.print("HELLO"+jsonBody);
	        
	        String Key = getKey.getKeyValue("EncKey1Android");
	        String get_encrypted_request_body = null;
	        
	        get_encrypted_request_body = TestUtils.Encrypt(jsonBody,getKey.getKeyValue("EncKey1Android"));
	        
	        user_authentication.setText(get_encrypted_request_body);
	        //System.out.println("HELLO"+user_authentication.getText());
	        
	        Map<String, Object> getHeaderList = new HashMap<String, Object>();
            
	        try {
	            getHeaderList = getHeadersList.GetHeadersForLogin("");
	            System.out.println("*** Print User Headers Listing  ***");
	            System.out.println(getHeaderList);
	            System.out.println(" ");
	        }
	        catch (Exception ex) {
	            System.out.println("*** Header Creation Failed ***");
	        }

	        Response apiResponse;

	        // Sending Post Request

	        apiResponse = given().
	                headers(getHeaderList).
	                body(user_authentication).
	                baseUri(ApiUrls.ZINDAGI_BASE_URL).
	                basePath(ApiUrls.USER_AUTHENTICATION).
	                when().
	                post().
	                then().
	                statusCode(200).
	                assertThat().
	                log().
	                all().
	                extract().
	                response();
			
	        String get_substring_for_decrypt = apiResponse.asString();
	        get_substring_for_decrypt = get_substring_for_decrypt.substring(9, (get_substring_for_decrypt.length()-2));
	        
	        String response_result = null;

	        try {
	            response_result = TestUtils.Decrypt(get_substring_for_decrypt, Key);
	        }
	        catch (Exception e)
	        {
	            System.out.println("*** Response body decryption failed ***");
	        }
	        
	        //System.out.println("HELLO"+response_result);
	        
	        ObjectMapper map = new ObjectMapper();
	        JsonNode node = map.readTree(response_result);

	        String session_value = node.get("access_token").toString();
	        
	        if (session_value == null || session_value == "")
	        {
	            // Manually Introducing Exception To Halt Execution In Case Session Is Null...
	            throw new RuntimeException();
	        }
	        
	        String user_value = node.get("UserID").toString();

	        System.out.println(node.get("access_token"));

	        List <String> return_singleton = new ArrayList<String>();

	        return_singleton.add(session_value.substring(1,session_value.length()-1));
	        return_singleton.add(user_value.substring(1,user_value.length()-1));

	        // Using sub string to remove double qoutes from session key...

	        System.out.println(node.toPrettyString());

	        return return_singleton;

	}
	
	
}
