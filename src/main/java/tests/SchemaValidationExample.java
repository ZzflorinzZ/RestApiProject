package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationExample {
	
	@Test
	public void validateSchema() {
		Response resp = given()
				.get("https://keytrcrud.herokuapp.com/api/users/64e4efaba3352a0013a5c6a2")
				.then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(resp.asString());
		
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("schema.json"));
	}

}
