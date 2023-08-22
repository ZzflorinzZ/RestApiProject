package tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.DataBuilder;

public class RestAssuredAuth {
	
	String token;
	String bookingId;
	
	@Test(priority = 1)
	public void createToken() {
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(DataBuilder.buildToken().toJSONString())
				.post("https://restful-booker.herokuapp.com/auth")
				.then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(resp.asPrettyString());
		
		token = resp.jsonPath().getString("token");
	}
	
	@Test(priority = 2)
	public void createBooking() {
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(DataBuilder.buildBooking().toJSONString())
				.post("https://restful-booker.herokuapp.com/booking")
				.then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(resp.asPrettyString());
		bookingId = resp.jsonPath().getString("bookingid");
	}
	
	@Test(priority =3)
	public void deleteBooking() {
/*		Response resp = given()
				.contentType(ContentType.JSON)
				.header("Cookie", "token="+token)
				.delete("https://restful-booker.herokuapp.com/booking/"+bookingId)
				.then()
				.statusCode(201)
				.extract().response();
		
		System.out.println(resp.asPrettyString());
		
		System.out.println(bookingId);
		System.out.println(token);
*/
		Response resp = given()
				.contentType(ContentType.JSON)
				.auth().preemptive().basic("admin", "password123")
				.delete("https://restful-booker.herokuapp.com/booking/"+bookingId)
				.then()
				.statusCode(201)
				.extract().response();
		
		System.out.println(resp.asPrettyString());
	}
}
