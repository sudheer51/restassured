package org.iitwforce.restassured.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DummyAPITests extends RestLibrary{
	Response response;
	@Test(description="Retrieve All the Customers") 
	public void verifyGetAllCustomers() {
		try 
		{
			response =  getServiceResponse(pro.getProperty("getEmployees"));
			System.out.println(response.getStatusCode());
			System.out.println(response.prettyPrint());
			JsonPath jsonPathEvaluator = response.jsonPath();
			String actualEmployeeName = jsonPathEvaluator.get("data[0].employee_name");
			String expectedEmployeeName="Tiger Nixon";
			System.out.println("Employee Name received from Response " + actualEmployeeName);

			Assert.assertEquals(actualEmployeeName,expectedEmployeeName);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for employees");
		}
	}
	@Test(description="Create New Employee") 
	public void verifyCreateNewEmployee() {
		try 
		{
			String payloadString="{\"name\":\"restapi\",\"salary\":\"12345678\",\"age\":\"83\"}";
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
	@Test(description="Update Employee") 
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
	@Test(description="Delete Employee") 
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
	@Test
	public void verifyEmployeeCount()
	{
		
	}
}
