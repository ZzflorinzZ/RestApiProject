package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder;

public class BaseComponent2Example extends BaseComponent2{
	
	String id;
	
	@Test
	public void postAnUser() {
		Response resp = doPost(DataBuilder.buildUser().toJSONString());
		id = resp.jsonPath().getString("result._id");
	}
	
	public void getAnUser() {
		Response resp = doGet(id);
	}

}
