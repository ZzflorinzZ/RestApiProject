package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;


import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;


public class BaseComponentExample extends BaseComponent{
	
	String id;
	
	@Test
	public void createNewUser() {

		Response response = doPostRequest("api/users", DataBuilder.buildUser().toJSONString(), 201);
		assertEquals(response.jsonPath().getString("success"), "true");
		id = response.jsonPath().getString("result._id");
	}

}
