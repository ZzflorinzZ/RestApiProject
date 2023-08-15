package utils;

import org.hamcrest.core.AnyOf;
import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BaseComponent2 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://keytrcrud.herokuapp.com")
				.setBasePath("/api/users/")
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
	
	
	public static Response doPost(String body) {
		Response resp = 
			given()
				.spec(requestSpec)
				.body(body)
			.when()
				.post()
			.then()
				.spec(responseSpec)
				.extract().response();			
		return resp;		
	}
	
	public static Response doPut(String id, String body) {
		Response resp = given()
				.spec(requestSpec)
				.body(body)
				.put(id)
				.then()
				.spec(responseSpec)
				.extract().response();			
		return resp;		
	}
	
	public static Response doDelete(String id) {
		Response resp = 
			given()
				.spec(requestSpec)
			.when()
				.delete(id)
			.then()
				.spec(responseSpec)
				.extract().response();			
		return resp;		
	}
	
	public static Response doGet(String id) {
		Response resp = 
			given()
				.spec(requestSpec)
			.when()
				.get(id)
			.then()
				.spec(responseSpec)
				.extract().response();			
		return resp;		
	}
	
	public static Response doGetAll() {
		Response resp = 
			given()
				.spec(requestSpec)
			.when()
				.get()
			.then()
				.spec(responseSpec)
				.extract().response();			
		return resp;		
	}

}
