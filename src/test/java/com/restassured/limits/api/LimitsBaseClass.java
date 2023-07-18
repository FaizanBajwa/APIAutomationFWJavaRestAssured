package com.restassured.limits.api;
import java.util.Random;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;

import java.util.Map;

import com.restassured.constants.ApiUrls;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class LimitsBaseClass {
	
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
					//.statusCode(200)
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
					//.statusCode(200)
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
	

    public boolean hasDuplicates(List<String> stringList) {
        List<String> uniqueList = new ArrayList<String>();
        for (String str : stringList) {
            if (uniqueList.contains(str)) {
                return true; // Duplicate found
            }
            uniqueList.add(str);
        }
        return false; // No duplicates found
    }
    
    
        public static String GetCurrentDate()
        {
            Date currentDate = new Date();

            // Create a SimpleDateFormat object with the desired date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Convert the Date object to a formatted String
            String dateString = dateFormat.format(currentDate);

            // Print the converted String
            //System.out.println("Date as String: " + dateString);
            return dateString;
 
        }
        
        public static int generateRandomNumber(int min, int max) {
            Random random = new Random();
            return random.nextInt(max - min + 1) + min;
        }
	

}
