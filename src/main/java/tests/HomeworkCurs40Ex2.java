package tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

import org.junit.internal.matchers.StringContains;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class HomeworkCurs40Ex2 {
	
	@Test(priority = 1)
	public void positiveValidationSchema() {
		
		Response resp = given()
				.get("https://swapi.dev/api/people/4/")
				.then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(resp.asPrettyString());
		System.out.println("=========================================================");
		
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("positiveSchemaC40Ex2.json"));		
	}
	
	@Test(priority = 2)
	public void negativeValidationSchema() {
		
		Response resp = given()
				.get("https://swapi.dev/api/people/4/")
				.then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(resp.asPrettyString());
/*		
		try {
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("negativeSchemaC40Ex2.json"));		
		} catch (NoSuchMethodError e) {
			System.out.println(e);
			assertTrue(e.toString().contains("org.hamcrest.Description"));
		}
*/		
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("negativeSchemaC40Ex2.json"));	
	}
}
