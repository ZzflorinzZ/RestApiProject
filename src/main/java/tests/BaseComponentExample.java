package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;


import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;


public class BaseComponentExample extends BaseComponent{
	
	String id;
	String name;
	String email;
	String age;
	String gender;
	
	@Test(priority = 1)
	public void createNewUser() {

		Response response = doPostRequest("api/users", DataBuilder.buildUser().toJSONString(), 201);
		assertEquals(response.jsonPath().getString("success"), "true");
		id = response.jsonPath().getString("result._id");
		name = response.jsonPath().getString("result.name");
		email = response.jsonPath().getString("result.email");
		age = response.jsonPath().getString("result.age");
		gender = response.jsonPath().getString("result.gender");
	}
	
	@Test(priority = 2, dependsOnMethods = "createNewUser")
	public void getUser() {

		Response response = doGetRequest("api/users/" + id, 200);
		assertEquals(response.jsonPath().getString("result._id"), id);
		assertEquals(response.jsonPath().getString("result.name"), name);
		assertEquals(response.jsonPath().getString("result.email"), email);
		assertEquals(response.jsonPath().getString("result.age"), age);
		assertEquals(response.jsonPath().getString("result.gender"), gender);
	}
	
	@Test(priority = 3, dependsOnMethods = "createNewUser")
	public void updateUser() {

		Response response = doPutRequest("api/users/" + id, DataBuilder.updateBuildUser().toJSONString(), 200);
		assertEquals(response.jsonPath().getString("success"), "true");
		assertEquals(response.jsonPath().getString("msg"), "Successfully updated!");
		assertEquals(response.jsonPath().getString("result._id"), id);
	}
	
	@Test(priority = 4, dependsOnMethods = "createNewUser")
	public void deleteUser() {

		Response response = doDeleteRequest("api/users/" + id, 200);
		assertEquals(response.jsonPath().getString("success"), "true");
		assertEquals(response.jsonPath().getString("msg"), "It has been deleted.");
		assertEquals(response.jsonPath().getString("result._id"), id);
	}
}
