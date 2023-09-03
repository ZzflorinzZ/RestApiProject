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

public class BaseComponentHomeworkC40Ex1 {
	
	public static RequestSpecification requestSpecNoAuth;
	public static RequestSpecification requestSpecAuth;
	public static RequestSpecification requestSpecAuthWithWrongToken;
	public static ResponseSpecification positiveResponseSpec;
	public static ResponseSpecification negativeResponseSpec;
	public static String token = "genericValue";
	public static int StatusCode;
	
	@BeforeClass
	public static void createRequestSpecificationNoAuth() {
		
		requestSpecNoAuth = new RequestSpecBuilder()
				.setBaseUri("https://dev-todo.herokuapp.com/")
				.setBasePath("api/auth")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
	}
	
	@BeforeClass
	public static void createRequestSpecificationAuth() {
		
		requestSpecAuth = new RequestSpecBuilder()
				.setBaseUri("https://dev-todo.herokuapp.com/")
				.setBasePath("api/auth")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.addHeader("token", token)
				.build();
	}
	
	@BeforeClass
	public static void createRequestSpecificationAuthWithWrongToken() {
		
		requestSpecAuthWithWrongToken = new RequestSpecBuilder()
				.setBaseUri("https://dev-todo.herokuapp.com/")
				.setBasePath("api/auth")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.addHeader("token", "wrongToken")
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
//				.expectStatusCode(either(is(not(200))))				//cum as putea sa scriu sintaxa ca StatusCode este diferit de 200? ;  nu ii place cu << is(not()) >>
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
			resp = given().spec(requestSpecNoAuth).body(body).post();
			break;	
		case "POSTNOAUTH" :
			resp = given().spec(requestSpecNoAuth).body(body).post("/save");
			break;
		case "POSTAUTH" :
			resp = given().spec(requestSpecAuth).body(body).post("/save");
			break;	
/*		case "PUT" :
			resp = given().spec(requestSpecNoAuth).body(body).post("todos/"+id);
			break;		
*/		case "DELETENOAUTH" :
			resp = given().spec(requestSpecNoAuth).delete("/delete/"+id);
			break;	
		case "DELETEAUTH" :
			resp = given().spec(requestSpecAuth).delete("/delete/"+id);
			break;	
		case "DELETEAUTHWRONGTOKEN" :
			resp = given().spec(requestSpecAuthWithWrongToken).delete("/delete/"+id);
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
