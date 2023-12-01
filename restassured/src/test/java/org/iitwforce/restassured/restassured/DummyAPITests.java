package org.iitwforce.restassured.restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import net.minidev.json.JSONArray;
import io.restassured.response.ResponseBody;

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
			response =  getServiceResponse(pro.getProperty("getEmployees"));
			System.out.println(response.getStatusCode());//429
			//System.out.println(response.prettyPrint());
			ResponseBody respBody = response.getBody();
			String actual = respBody.asString();
			System.out.println("Response Body" + actual);
			boolean result = actual.contains("Tiger Nixon");
			Assert.assertTrue(result);
			JsonPath path=	response.jsonPath();
			Object obj = path.get("$.data[1].employee_name");
			String str = obj.toString();
			System.out.println(str);


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
