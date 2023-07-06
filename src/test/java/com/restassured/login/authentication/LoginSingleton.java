package com.restassured.login.authentication;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.constants.ApiUrls;
import com.restassured.models.common.Data;
import com.restassured.models.common.ApiModelZbox;
import com.restassured.models.common.PayLoad;
import com.restassured.utils.ConfigManager;
import com.restassured.utils.TestUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginSingleton {
	
	public static List<String> login() throws IOException{
			
		ConfigManager get_keys = new ConfigManager();
		ApiModelZbox example = new ApiModelZbox();
		example.setData(new Data());
		example.getData().setPayLoad(new PayLoad());
		example.getData().getPayLoad().setUserName(get_keys.getKeyValue("user_name"));
		example.getData().getPayLoad().setUserPassword(get_keys.getKeyValue("user_pass"));
		
		String json_body = TestUtils.gsonString(example);
		
		Response api_response = given().
				contentType(ContentType.JSON).
				body(json_body).
				baseUri(ApiUrls.ZBOX_BASE_URL_QA).
				basePath(ApiUrls.login).
				when().
				post().
				then().
				statusCode(200).
				assertThat().
				log().
				all().
				extract().
				response();
		
		String respose_to_string = api_response.asString();
		
		ObjectMapper map = new ObjectMapper();
        JsonNode node = map.readTree(respose_to_string);
        
        String session_value = node.get("access_token").toString();

        if (session_value == null || session_value == "")
        {
            // Manually Introducing Exception To Halt Execution In Case Session Is Null...
            throw new RuntimeException();
        }
        
        List <String> return_singleton = new ArrayList<String>();
        String user_value = node.get("UserID").toString();
        
        return_singleton.add(session_value.substring(1,session_value.length()-1));
        return_singleton.add(user_value.substring(1,user_value.length()-1));

        return return_singleton;

	}

}
