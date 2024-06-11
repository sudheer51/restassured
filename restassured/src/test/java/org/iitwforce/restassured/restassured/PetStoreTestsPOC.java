package org.iitwforce.restassured.restassured;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class PetStoreTestsPOC extends RestLibrary{
	
	
	@BeforeClass
	public void preconditions() throws Exception
	{
		
		//Adding a petID
		String requestURL =pro.getProperty("addpetstore");
		String payloadString = readPayload("AddPetPayload.txt");
		Response response = postServiceResponse(requestURL,payloadString);
		System.out.println("Code::: "+ response.getStatusCode());
		System.out.println("Response Body::: "+response.getBody().asString());
		Integer petID = JsonPath.read(response.getBody().asString(),"$.id");
		System.out.println(petID);
		setSysProperty("petID",petID+"");
	}
	
	
	@Test
	public void e2ePetStoreScenario() throws Exception
	{
		SoftAssert sa = new SoftAssert();
		//getPet
		String requestURL =pro.getProperty("getpetdetailsbyid")+getSysProperty("petID");
		Response response = getServiceResponse(requestURL);
		Integer petID = JsonPath.read(response.getBody().asString(),"$.id");
		sa.assertEquals(petID.toString(),getSysProperty("petID"));
		
		//updatePet
		System.out.println("*********************************Executing the updatePet request**********************");
		requestURL =pro.getProperty("addpetstore");
		String payloadString = readPayload("UpdatePetPayload.txt").replace("$name$", "doggie123");
		System.out.println("Updated Payload String" + payloadString);
		response= updateServiceResponse(requestURL, payloadString);
		String actual = JsonPath.read(response.getBody().asString(),"$.name");
		String expected="doggie123";
		sa.assertEquals(actual,expected);
		
		//getPet
		requestURL =pro.getProperty("getpetdetailsbyid")+getSysProperty("petID");
		response = getServiceResponse(requestURL);
		actual = JsonPath.read(response.getBody().asString(),"$.name");
		sa.assertEquals(actual,expected);
	 
		
		//deletePet
		requestURL =pro.getProperty("getpetdetailsbyid")+getSysProperty("petID");
		response = deleteServiceResponse(requestURL);
		actual = JsonPath.read(response.getBody().asString(),"$.type");
		expected="unknown";
		sa.assertEquals(actual,expected);
	 
		
		//getPet
		 requestURL =pro.getProperty("getpetdetailsbyid")+getSysProperty("petID");
		response = getServiceResponse(requestURL);
		actual = JsonPath.read(response.getBody().asString(),"$.message");
		expected="Pet not found";
		sa.assertEquals(actual,expected);
		
		sa.assertAll();

	}
	
	
	
	@Test
	public void addPet() throws Exception
	{
		String requestURL =pro.getProperty("addpetstore");
		String payloadString = readPayload("AddPetPayload.txt");
		Response response = postServiceResponse(requestURL,payloadString);
		System.out.println("Code::: "+ response.getStatusCode());
		System.out.println("Response Body::: "+response.getBody().asString());
	}

	
	@Test
	public void getPetIDDetails() throws Exception
	{
		String requestURL =pro.getProperty("getpetdetailsbyid")+"1";
		Response response = getServiceResponse(requestURL);
		System.out.println("Code::: "+ response.getStatusCode());
		System.out.println("Response Body::: "+response.getBody().asString());
	}
	@Test
	public void getPetIDDetailsforInValidID() throws Exception
	{
		String requestURL =pro.getProperty("getpetdetailsbyid")+"///1211";
		Response response = getServiceResponse(requestURL);
		System.out.println("Code::: "+ response.getStatusCode());
		System.out.println("Response Body::: "+response.getBody().asString());
	}
	@Test
	public void getPetIDDetailsforNotFound() throws Exception
	{
		
		String requestURL =pro.getProperty("getpetdetailsbyid")+"121414";
		Response response = getServiceResponse(requestURL);
		System.out.println("Code::: "+ response.getStatusCode());
		System.out.println("Response Body::: "+response.getBody().asString());
	}

	
	
}
