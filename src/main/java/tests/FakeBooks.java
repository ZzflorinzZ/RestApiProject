package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class FakeBooks {

	public String readJsonFile(String jsonFileName, String bodyParam) throws ParseException {
		try {
			File fisier = new File(jsonFileName);
			JSONParser parser = new JSONParser();
			FileReader jsonFile = new FileReader(jsonFileName);
			JSONObject jsonObj = (JSONObject) parser.parse(jsonFile);
			return jsonObj.get(bodyParam).toString();
		} catch (IOException e) {
			return e.getMessage();		
		}

	}
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/";
	}	
	
	@Test
	public void AddBook() throws IOException, ParseException {		
		File fisier = new File("AddBook_Data.json");
	
		Response resp = 
			given()
				.contentType(ContentType.JSON)
				.body(fisier)
				.post("api/v1/Books")
			.then()
				.log().all()
				.statusCode(200)
				.extract().response();
		
		assertEquals(readJsonFile("AddBook_Data.json", "id"), resp.jsonPath().getString("id"));
	}
	
	
}
