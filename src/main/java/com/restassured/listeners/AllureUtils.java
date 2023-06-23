package com.restassured.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class AllureUtils {	
	//Alt + Shift + S + V to quickly implement/override method
	 @Attachment(value = "Screenshot", type = "image/png")
	    public static byte[] takeScreenshot(WebDriver driver) {
	        // Capture screenshot as byte array
	        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    }

	  @Step("{stepDescription}")
	    public static void logStep(String stepDescription) {
	        // Log the step using Allure
	        Allure.step(stepDescription);
	    }
	  
	  public static void attachData(String name,String jsonData) {
		  //Allure.attachment("Json", jsonData);
		  Allure.addAttachment(name, jsonData);
	  }
}
