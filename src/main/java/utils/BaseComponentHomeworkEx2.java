package utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileWriter;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseComponentHomeworkEx2 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://keytodorestapi.herokuapp.com")
				.setBasePath("/api/")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "*/*")
				.build();
	}
	
	@BeforeClass
	public static void createResponseSpecification() {
		responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(either(is(200)).or(is(201)).or(is(204)))
				.build();
	}
	
	
	public static Response doPost(File file) {
		Response resp = 
			given()
				.spec(requestSpec)
				.body(file)
			.when()
				.post("save")
			.then()
				.spec(responseSpec)
				.extract().response();			
		return resp;		
	}
	
	public static Response doPut(String id, String body) {
		Response resp = given()
				.spec(requestSpec)
				.body(body)
				.put("todos/" + id)
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
				.delete("delete/" + id)
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
