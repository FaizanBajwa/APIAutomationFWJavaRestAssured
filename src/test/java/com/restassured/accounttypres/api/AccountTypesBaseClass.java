package com.restassured.accounttypres.api;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.simple.JSONArray;


import com.restassured.constants.ApiUrls;
import com.restassured.utils.DBQueryExecutor;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AccountTypesBaseClass {

	public static Response postApiResponse(Map<String, Object> getHeadersList, String Body, String url) {

		try {
			return given().headers(getHeadersList).contentType(ContentType.JSON).body(Body)
					.baseUri(ApiUrls.ZBOX_BASE_URL_QA).basePath(url).when().post().then().statusCode(200).assertThat()
					.log().all().extract().response();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
	}

	public static Response getApiResponse(Map<String, Object> getHeadersList, String url) {

		try {
			return given().headers(getHeadersList).contentType(ContentType.JSON).baseUri(ApiUrls.ZBOX_BASE_URL_QA)
					.basePath(url).when().get().then().statusCode(200).assertThat().log().all().extract().response();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
	}

	public static Response negativePostApiResponse(Map<String, Object> getHeadersList, String Body, String url) {

		try {
			return given().headers(getHeadersList).contentType(ContentType.JSON).body(Body)
					.baseUri(ApiUrls.ZBOX_BASE_URL_QA).basePath(url).when().post().then().log().all().extract()
					.response();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static JSONArray getSearchCountFromDb() {
		
		String query ="SELECT *\r\n"
				+ "FROM lkp_account_level\r\n"
				+ "WHERE account_classification_id = 1\r\n"
				+ "AND trunc(createdate) between '01-JUL-2023' AND '05-JUL-2023'";
		System.out.println(query);
		DBQueryExecutor dbQueryExecutor = new DBQueryExecutor();
		JSONArray dbResponse = dbQueryExecutor.getDataByQuery(query);
			
		return dbResponse;
	}

}
