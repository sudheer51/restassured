package org.iitwforce.restassured.restassured;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestLibrary {
	private static Map<String,Object> headersMap;
	protected Properties pro;
	@BeforeClass
	public void setUp() throws IOException
	{
		System.out.println("in BeforeClass");
		/**
		 * Load the Property File
		 */
		  // Create  FileInputStream object 
		  FileInputStream fis=new FileInputStream(new File("configuration.properties"));

		  // Create Properties class object to read properties file
		   pro=new Properties();

		  // Load file so we can use into our script 
		  pro.load(fis);


		
	} 
	public static Map<String, Object> provideHeaders( ) throws Exception{
		try{
			headersMap = new LinkedHashMap<String, Object>();
			headersMap.put("Content-type","application/json");
			return headersMap;
		}catch(Exception e){
 
		}
		return headersMap;
	}
	 public static Response getServiceResponse(String serviceURL) throws Exception 
	 {
	 		RequestSpecification request = RestAssured.given();
	 		request.headers(provideHeaders());
	 		Response response =  request.when().get(serviceURL);
	 	
	 		//RestAssured.given().headers(provideHeaders()).when().get(serviceURL);
	 		
	 		return response;
	 }  
	 public static String getSysProperty(String propName) {
			String propValue = System.getProperty(propName);
			return propValue != null ? propValue : null;
		}
		public static void setSysProperty(String propName, String propValue) {
			if (propValue != null) {
				System.setProperty(propName, propValue);
			} else {
				System.setProperty(propName, (String) null);
			}

		}  
		 public static Response postServiceResponse(String serviceURL,String payloadString) throws Exception 
		 {
		 		RequestSpecification request = RestAssured.given();
		 		request.headers(provideHeaders());
		 		Response response =  request.when().body(payloadString).post(serviceURL);
		 		return response;
		 }  
		 public static Response updateServiceResponse(String serviceURL,String payloadString) throws Exception 
		 {
		 		RequestSpecification request = RestAssured.given();
		 		request.headers(provideHeaders());
		 		Response response =  request.when().body(payloadString).put(serviceURL);
		 		return response;
		 } 
		 public static Response deleteServiceResponse(String serviceURL) throws Exception 
		 {
		 		RequestSpecification request = RestAssured.given();
		 		request.headers(provideHeaders());
		 		Response response =  request.when().delete(serviceURL);
		 		return response;
		 } 
}
