package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.*;

public class CrudExamples {

	// POST
	// https://keytodorestapi.herokuapp.com/api/save
	// GET
	// https://keytodorestapi.herokuapp.com/api/id
	// GET ALL
	// https://keytodorestapi.herokuapp.com/api
	// PUT
	// https://keytodorestapi.herokuapp.com/api/todos/id
	// DELETE
	// https://keytodorestapi.herokuapp.com/api/delete/is

	JSONObject body;
	String id;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com/";
		body = new JSONObject();
		Faker fake = new Faker();

		body.put("title", fake.lordOfTheRings().character());
		body.put("body", fake.backToTheFuture().quote());
	}

	@Test(priority = 1)
	public void createTodo() {
		Response resp = 
		given()
				// .header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.body(body.toJSONString())
			.post("api/save")
		.then()
			.statusCode(200)
			.body("info", is(equalTo("Todo saved! Nice job!")))
			.body("id", is(anything()))
			.log().all()
			.extract().response();

		id = resp.jsonPath().getString("id");
	}

	@Test(priority = 2)
	public void getTodo() {
		Response resp = 
			given()
				.contentType(ContentType.JSON)
				.get("api/" + id)
			.then()
				.extract().response();

		assertThat(id, is(equalTo(resp.jsonPath().getString("_id"))));

		assertEquals(id, resp.jsonPath().getString("_id"));
	}

	@Test(priority = 3)
	public void updateTodo() {
		given()
			.contentType(ContentType.JSON)
			.body(body.toJSONString()).put("api/todos/" + id)
		.then()
			.statusCode(201)
			.body("msg", is(equalTo("Item updated")))
			.log().all();
	}

	@Test(priority = 4)
	public void deleteTodo() {
		given()
			.contentType(ContentType.JSON)
			.delete("api/delete/" + id)
		.then()
			.statusCode(200)
			.body("msg", is(equalTo("Event deleted.")));
	}
}
