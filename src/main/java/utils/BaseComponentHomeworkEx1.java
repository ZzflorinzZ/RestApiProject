package utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseComponentHomeworkEx1 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://fakerestapi.azurewebsites.net")
				.setBasePath("/api/v1/Books")
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
	
	
	public static Response doGet() {
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
