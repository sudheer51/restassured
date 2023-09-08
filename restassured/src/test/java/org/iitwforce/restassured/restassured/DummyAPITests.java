package org.iitwforce.restassured.restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

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
			response =  getServiceResponse(pro.getProperty("getEmployees"));
			System.out.println(response.getStatusCode());//429
			System.out.println(response.prettyPrint());
			
			String json ="{\r\n   \"lotto\":{\r\n      \"lottoId\":5,\r\n      \"winning-numbers\":[2,45,34,23,7,5,3],\r\n      \"winners\":[\r\n         {\r\n            \"winnerId\":23,\r\n            \"numbers\":[2,45,34,23,3,5]\r\n         },\r\n         {\r\n            \"winnerId\":54,\r\n            \"numbers\":[52,3,12,11,18,22]\r\n         }\r\n      ]\r\n   }\r\n}";
					 
			JsonPath path = JsonPath.compile("$.lotto.lottoId");
			int successList = path.read(json);
			System.out.println("Actual Status:: " +successList  );
			
			path = JsonPath.compile("$.lotto.winning-numbers");
			List<Object> winningList = path.read(json);
			System.out.println("Actual Size:: " +winningList.size()  );
			System.out.println("Actual Value:: " +winningList.get(0)  );
			
			String expected="success";
			// Assert.assertEquals(actual,expected);
//			String expectedEmployeeName="Tiger Nixon";
		 

			 

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
