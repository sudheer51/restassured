package org.iitwforce.restassured.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class PetStoreTests extends RestLibrary {

	Response response;
	@Test(description="Add a New Pet") 
	public void verifyAddNewPet() {
		try 
		{
			String payloadString = readPayload("AddPetPayload.txt");
			response =  postServiceResponse(pro.getProperty("addpetstore"),payloadString);	 
			System.out.println(response.getStatusCode());//429
			//System.out.println(response.prettyPrint());
			ResponseBody respBody = response.getBody();
			String actual = respBody.asString();
 			System.out.println("Response Body" + actual);
			boolean result = actual.contains("doggie");
			Assert.assertTrue(result);
			String dogName = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.name");
			System.out.println(dogName);


		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for employees");
		}
	}
}
