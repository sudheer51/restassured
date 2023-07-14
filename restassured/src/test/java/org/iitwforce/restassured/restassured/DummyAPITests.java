package org.iitwforce.restassured.restassured;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

//import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;
//import net.minidev.json.JSONArray;

public class DummyAPITests extends RestLibrary{
	
	
	Response response;
	static String   token;
	@BeforeClass
	public void getAuthorizationToken()
	{
		 
		token="124151515";
		setSysProperty("token", token);
		String result = getSysProperty("token");
		System.out.println(result);
	}
	
	@Test(description="Retrieve All the Employees") 
	public void verifyGetAllEmployees() {
		try 
		{
			response =  getServiceResponse(pro.getProperty("checkgetapi"));
			System.out.println(response.getStatusCode());//429
			System.out.println(response.prettyPrint());
			JSONArray jsonArr = JsonPath.read(response.getBody(), "$..data.summary.total") ;
			 
//			String expectedEmployeeName="Tiger Nixon";
			System.out.println("Employee Name received from Response " +jsonArr );

			//Assert.assertEquals(actualEmployeeName,expectedEmployeeName);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for employees");
		}
	}
	@Test(description="Create New Employee") 
	public void verifyCreateNewEmployee() {
		try 
		{
			 
			String payloadString=readPayload("Payload.txt");
			response =  postServiceResponse(pro.getProperty("createNewEmployee"),payloadString);
			System.out.println(response.getStatusCode());
			System.out.println(response.prettyPrint());
			JsonPath jsonPathEvaluator = response.jsonPath();
			String actualMsg = jsonPathEvaluator.get("message");
			String expectedMsg="Successfully! Record has been added.";
			System.out.println("Employee Name received from Response " + expectedMsg);
			Assert.assertEquals(actualMsg,expectedMsg);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for Enterprises");
		}
	}
	@Test(description="Update Employee",enabled=false) 
	public void verifyUpdateEmployee() {
		try 
		{
			String payloadString="{\"name\":\"restapi\",\"salary\":\"12345678\",\"age\":\"83\"}";
			response =  updateServiceResponse(pro.getProperty("updateEmployee"),payloadString);
			System.out.println(response.getStatusCode());
			System.out.println(response.prettyPrint());
			JsonPath jsonPathEvaluator = response.jsonPath();
			String actualMsg = jsonPathEvaluator.get("message");
			String expectedMsg="Successfully! Record has been updated.";
			System.out.println("Employee Name received from Response " + expectedMsg);
			Assert.assertEquals(actualMsg,expectedMsg);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for Enterprises");
		}
	}
	@Test(description="Delete Employee",enabled=false) 
	public void verifyDeleteEmployee() {
		try 
		{
			response =  deleteServiceResponse(pro.getProperty("deleteEmployee"));
			System.out.println(response.getStatusCode());
			System.out.println(response.prettyPrint());
			JsonPath jsonPathEvaluator = response.jsonPath();
			String actualMsg = jsonPathEvaluator.get("message");
			String expectedMsg="Successfully! Record has been deleted";
			System.out.println("Employee Name received from Response " + expectedMsg);
			Assert.assertEquals(actualMsg,expectedMsg);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for Enterprises");
		}
	}
}
