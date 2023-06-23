package com.restassured.zindgi.testcases;

import java.io.IOException;
import java.util.List;

public class LoginObj {

	private static LoginObj single_instance = null;

    public List<String> loginSession;
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    LoginObj() throws IOException {
       
			try {
				loginSession = Login.login();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }


    // Static method to create instance of Singleton class
    public static LoginObj getInstance() throws IOException {
        if (single_instance == null)
            single_instance = new LoginObj();

        return single_instance;
    }
}
