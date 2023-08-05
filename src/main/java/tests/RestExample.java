package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestExample {
	
	//trimitem un POST pe url:
	//https://keytodorestapi.herokuapp.com/api/save
	//validam raspunsul - contine: Todo saved! Nice job!
	
	@SuppressWarnings("unchecked")
	@Test
	public void restExampleTest() {
		
		JSONObject requestPayload = new JSONObject();
		requestPayload.put("title", "Test JSON object title");
		requestPayload.put("body", "Test JSON object body");
		
		File fisier = new File("data.json");
		
		Response response = 
				given()
					.header("Content-Type", "application/json")
					.header("Accept", "*/*")
					//exemplul 1 - direct in body ca String
					//.body("{\"title\": \"florin_test_01\", \"body\": \"test_01\"}")
					
					//exemplul 2 - body as json object
					//.body(requestPayload.toJSONString())
					
					//exemplul 3 - body as file
					//.body(new File("data.json"))
					.body(fisier)
				.when()
					.post("https://keytodorestapi.herokuapp.com/api/save")				
				.then()
					.assertThat().statusCode(200)
					.extract().response();
		
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		System.out.println(response.jsonPath().getString("id"));
		System.out.println(response.jsonPath().getString("info"));
		
		String info = response.jsonPath().getString("info");
		assertEquals(info, "Todo saved! Nice job!");
		
	}

}
