package com.restassured.utils;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;

public class TestUtils {
	
    public static final String RSA_ALGORITHM = "RSA";


    public static String Encrypt(String text, String key) throws Exception {
        // Changed PKCS7Padding to PKCS5Padding as cyper in java does not support PKCS7
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] results = new byte[text.length()];
        try {
            results = cipher.doFinal(text.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println("Error in Decryption" + e.toString());
            throw e;
        }
        //one class for getting vlaue and verification for json
        //
        return Base64.getEncoder().encodeToString(results);
    }
    
    public static String Decrypt(String text, String key) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];

        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] results = new byte[text.length()];
        try {
            //results = cipher.doFinal(android.util.Base64.decode(text, 0));
            results = cipher.doFinal(Base64.getDecoder().decode(text));

        } catch (Exception e) {
            System.out.println("Erron in Decryption ====>" + e.toString());
        }
        System.out.println("Data ====> " + new String(results, "UTF-8"));
        //DataHandler.terminology = "";
        return new String(results, "UTF-8"); // it returns the result as a String

    }
    
    public static String RSAEncode(String publicKey, String data) {
        try {
            publicKey = publicKey.replace("\n", "");

            //Base64.decodeBase64(publicKey);

            byte[] pks = Base64.getDecoder().decode(publicKey);;
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pks);
            KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM);
            PublicKey pk;
            pk = kf.generatePublic(x509EncodedKeySpec);
            Cipher cp = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cp.init(Cipher.ENCRYPT_MODE, pk);
            //org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(rsaSplitCodec(cp, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), Base64.decode(publicKey).getModulus().bitLength()));
            //return Base64.encodeToString(cp.doFinal(data.getBytes(Charset.forName("UTF-8"))), Base64.NO_WRAP);
            //System.out.println(Base64.encodeBase64String(cp.doFinal(data.getBytes(Charset.forName("UTF-8")))));
            return Base64.getEncoder().encodeToString(cp.doFinal(data.getBytes(Charset.forName("UTF-8"))));
        } catch (Exception e) {

            //LogUtil.error("SecurityUtil",e);
            return "";
        }
    }
    
    public static String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public static String gsonString(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
    
    public static boolean isDateFormatValid(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setLenient(false); // Set lenient mode to strict parsing

        try {
            dateFormat.parse(date);
            return true; // Date format is valid
        } catch (ParseException e) {
            return false; // Date format is not valid
        }
    }
	
    public static boolean isWithinRange(String tDate,String sDate,String eDate) {
    		LocalDate startDate = LocalDate.parse(sDate);
    		LocalDate endDate = LocalDate.parse(eDate);
    		LocalDate testDate = LocalDate.parse(eDate);
    	   return !(testDate.isBefore(startDate) || testDate.isAfter(endDate));
    	}
    
    public static Integer getRandomNum() {
    	int selected = 0;
    	Random rand = new Random();
    	selected = rand.nextInt(1000000000);
    	return selected;
    }

    public static String getCurrentDate(String pattern) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Create a DateTimeFormatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // Format the date and return as a string
        return currentDate.format(formatter);
    }
}
