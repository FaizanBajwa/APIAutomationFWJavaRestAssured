package com.restassured.usermanagment.api.testcases;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.BeforeTest;

import com.google.gson.Gson;
import com.restassured.constants.ApiUrls;
import com.restassured.login.authentication.Login;
import com.restassured.models.common.Data;
import com.restassured.models.common.ApiModelZbox;
import com.restassured.models.common.PayLoad;
import com.restassured.utils.TestUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserManagnmentBaseClass {
	
	@BeforeTest
	public Response beforeTest() {
		
//		Example example = new Example();
//		example.setData(new Data());
//		example.getData().setPayLoad(new PayLoad());
//		example.getData().getPayLoad().setUserName("Kashi");
//		example.getData().getPayLoad().setUserPassword("kashi@123");
//		
//		TestUtils testUtils = new TestUtils();
//		
//		String json = testUtils.gsonString(example);
//		System.out.println(json);
//		Response response = loginGetAuthToken(ApiUrls.login, json);
		//Login login = new Login();
		//Response response = login.login("Kashi@123", "kashi@123");
		return null; 
		
	}
	
	public static Response getApiResponse(Map<String, Object> getHeadersList,String url) {
		
		try {
			return given()
					.headers(getHeadersList)
					.contentType(ContentType.JSON)
					.baseUri(ApiUrls.ZBOX_BASE_URL_QA)
					.basePath(url)
					.when()
					.get()
					.then()
					.statusCode(200)
					.assertThat()
					.log()
					.all()
					.extract().response();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static Response postApiResponse(Map<String, Object> getHeadersList,String Body,String url) {
		
		try {
			return given()
					.headers(getHeadersList)
					.contentType(ContentType.JSON)
					.body(Body)
					.baseUri(ApiUrls.ZBOX_BASE_URL_QA)
					.basePath(url)
					.when()
					.post()
					.then()
					.statusCode(200)
					.assertThat()
					.log()
					.all()
					.extract().response();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static Response loginGetAuthToken(String Url,String Body) {
		try {
			return given()
					.contentType(ContentType.JSON)
					.body(Body)
					.when()
					.post(Url)
					.then()
					.extract().response();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
		
	}	
}
