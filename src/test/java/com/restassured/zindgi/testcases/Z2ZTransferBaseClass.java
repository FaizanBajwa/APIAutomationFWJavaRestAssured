package com.restassured.zindgi.testcases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;
import com.restassured.constants.ApiUrls;
import com.restassured.utils.APIHeaders;
import com.restassured.utils.ConfigManager;
import com.restassured.utils.TestUtils;
import com.restassured.models.zindagi.EncryptedModelBody;

import io.restassured.response.Response;

public class Z2ZTransferBaseClass {

	public String postApiRequest(Map<String, Object> getHeadersList, Object Body, String Endpoint) throws IOException {

		ConfigManager getKey = new ConfigManager();

		String json_string_for_encrypyion = TestUtils.gsonString(Body);

		// Call Encryption Methods
		String get_encrypted_request_body = null;
		try {
			get_encrypted_request_body = TestUtils.Encrypt(json_string_for_encrypyion,
					getKey.getKeyValue("EncKey1Android"));
		} catch (Exception e) {
			System.out.println("*** Request body encryption failed ***");
		}

		EncryptedModelBody encBody = new EncryptedModelBody();
		encBody.setText(get_encrypted_request_body);

		get_encrypted_request_body = TestUtils.gsonString(encBody);
		
		Response api_enc_response = given().
				headers(getHeadersList).
				body(get_encrypted_request_body).
				baseUri(ApiUrls.ZINDAGI_BASE_URL).
				basePath(Endpoint).
				when().
				post().
				then().
				statusCode(200).
				assertThat().
				log().
				all().
				extract().
				response();

		String get_substring_for_decrypt = api_enc_response.asString();
		get_substring_for_decrypt = get_substring_for_decrypt.substring(9, (get_substring_for_decrypt.length() - 2));

		String response_result = null;

		try {
			response_result = TestUtils.Decrypt(get_substring_for_decrypt, getKey.getKeyValue("EncKey1Android"));
			return response_result;
		} catch (Exception e) {
			System.out.println("*** Response body decryption failed ***");
			return null;
		}

	}
}
