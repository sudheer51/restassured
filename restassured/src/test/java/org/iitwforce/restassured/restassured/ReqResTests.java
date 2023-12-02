package org.iitwforce.restassured.restassured;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ReqResTests extends RestLibrary{


	Response response;
	
	@Test(description="Verify the create user api ",priority=1) 
	public void  createUser()
	{
		String payloadString;
		try {
			payloadString = readPayload("createuser_payload.txt");
			Random random = new Random();
			int i = random.nextInt(100);
		 
			Response response = postServiceResponse(pro.getProperty("reqrescreateuser"),
											payloadString.replace("$name","QA_AUT_USER"+i));
			System.out.println("Response Code " + response.getStatusCode());
			System.out.println(response.prettyPrint());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
}
