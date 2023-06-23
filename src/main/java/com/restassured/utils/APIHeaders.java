package com.restassured.utils;

import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIHeaders {
	public Map<String, Object> GetHeadersForLogin(String UserState) throws Exception {

        ConfigManager getKey = new ConfigManager();
        System.out.println("*** Creating Headers ***");
        System.out.println(" ");

        //ServiceHelper serviceObj = new ServiceHelper();
        ObjectMapper objMap = new ObjectMapper();
        Yaml yaml = new Yaml();


        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("PostHeaders.yml");

        Map<String, Object> headersFromYml = yaml.load(inputStream);

        String user_msisdn = TestUtils.Encrypt(getKey.getKeyValue("MSISDN"), getKey.getKeyValue("EncKey1Android"));

        headersFromYml.put("NO", user_msisdn);


        /*if (UserState == "loggedIn") {
            headersFromYml.remove("USER");
            getHeaderList.put("x-auth-token",LoginObj.getInstance().loginSession);
            String user_id = TestUtils.Encrypt(getKey.getKeyValue("MSISDN"), getKey.getKeyValue("EncKey1Android"));
            headersFromYml.put("x-lemon-sign", serviceHelperObj.getSecureKey(requestBody));
            //headersFromYml.remove("x-auth-token");
        }*/

        return headersFromYml;
	}
}