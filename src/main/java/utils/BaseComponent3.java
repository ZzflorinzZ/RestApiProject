package utils;

import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class BaseComponent3 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
	}
	
	@BeforeClass
	public static void createResponseSpecification() {
		responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(either(is(200)).or(is(201)).or(is(204)))
				.build();
	}
	
	public static Response doRequest(String httpMethod, String id, String body) {
		Response resp = null;
		switch(httpMethod.toUpperCase()) {
		
		case "GET" :
			resp = given().spec(requestSpec).get(id);
			break;
		case "GETALL" :
			resp = given().spec(requestSpec).get();
			break;	
		case "POST" :
			resp = given().spec(requestSpec).body(body).post("save");
			break;	
		case "PUT" :
			resp = given().spec(requestSpec).body(body).post("todos/"+id);
			break;		
		case "DELETE" :
			resp = given().spec(requestSpec).delete("delete/"+id);
			break;	
		}
		if(resp != null) {
			resp = resp.then().spec(responseSpec).extract().response();
		}
		
		return resp;
	}

}
