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

public class BaseComponentHomeworkC40Ex1v2 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification positiveResponseSpec;
	public static ResponseSpecification negativeResponseSpec;
//	public static String token = null;		//java.lang.IllegalArgumentException: Header value cannot be null
	public static String token = "genericValue";
	public static int StatusCode;
	
	
	@BeforeClass
	public static void createRequestSpecificationAuth() {
		
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://dev-todo.herokuapp.com/")
				.setBasePath("api/auth")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.addHeader("token", token)
				.build();
	}
	
	@BeforeClass
	public static void createPositiveResponseSpecification() {
		positiveResponseSpec = new ResponseSpecBuilder()
				.expectStatusCode(is(200))
				.build();
	}
	
	@BeforeClass
	public static void createNegativeResponseSpecification() {
		negativeResponseSpec = new ResponseSpecBuilder()
				.expectStatusCode(either(is(401)).or(is(403)))
				.build(); 
	}

	public static Response doRequest(String httpMethod, String id, String body) {
		Response resp = null;
		switch(httpMethod.toUpperCase()) {
		
/*		case "GET" :
			resp = given().spec(requestSpecNoAuth).get(id);
			break;
		case "GETALL" :
			resp = given().spec(requestSpecNoAuth).get();
			break;	
*/		case "POSTRETRIVETOKEN" :
			resp = given().spec(requestSpec).body(body).post();
			break;	
		case "POST" :
			resp = given().spec(requestSpec).body(body).post("/save");
			break;
/*		case "PUT" :
			resp = given().spec(requestSpecNoAuth).body(body).post("todos/"+id);
			break;		
*/		case "DELETE" :
			resp = given().spec(requestSpec).delete("/delete/"+id);
			break;		
		}
		
		if(resp != null) {
			StatusCode = resp.getStatusCode();
			if(StatusCode == 200) {			
				resp = resp.then().spec(positiveResponseSpec).extract().response();
			} else {
				resp = resp.then().spec(negativeResponseSpec).extract().response();
			}
		}
		
		return resp; 
	}


}
