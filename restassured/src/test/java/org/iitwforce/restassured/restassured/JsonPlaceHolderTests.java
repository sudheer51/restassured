package org.iitwforce.restassured.restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class JsonPlaceHolderTests extends RestLibrary{


	Response response;
	
	@Test(description="Retrieve All the users",priority=1) 
	public void verifyJsonPlaceholder_allusers() {
		try 
		{
			response =  getServiceResponse(pro.getProperty("jsonplaceholderallusers"));
			System.out.println(response.getStatusCode());//429
			System.out.println("Response Received :::: " + response.asString());
			
			String expected = "Bret";
			String actual = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.[0].username");
			System.out.println("Actual " + actual);
			
			
			List<String> latList = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.[*].address.geo.lat");
			System.out.println(latList.size());
			
			Integer idValue =  com.jayway.jsonpath.JsonPath.read(response.asString(), "$.[0].id");
			setSysProperty("userID",idValue.toString());
			
			Assert.assertEquals(actual,expected);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for employees");
		}
	}
	@Test(description="Retrieve Single User Details",priority=2) 
	public void verifyJsonPlaceholder_SpecificUser() {
		try 
		{
			response =  getServiceResponse(pro.getProperty("jsonplaceholderspecificuser")+getSysProperty("userID"));
			System.out.println(response.prettyPrint());
			System.out.println(response.getStatusCode());//429
			 

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for employees");
		}
	}
	
	@Test(description="Retrieve All post Details")
	public void verifyAllPosts()
	{
	
		try {
			response=getServiceResponse(pro.getProperty("jsonplaceholdersallposts"));
			System.out.println("Response Code" + response.getStatusCode());
			String expected = "published";
			String actual = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.[1].status");
			Assert.assertEquals(actual, expected);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
