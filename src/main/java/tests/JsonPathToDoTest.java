package tests;

import java.io.File;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponentHomeworkEx2;
import utils.DataBuilder;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class JsonPathToDoTest extends BaseComponentHomeworkEx2 {
	
	JSONObject bodyBuilder = new JSONObject();
	String id;
	
	
	@Test(priority = 1)
	public void postToDo() {
		
		Response result = doPost(DataBuilder.postToDo(bodyBuilder, "todo.json"));
		System.out.println(result.asPrettyString());
		
		JsonPath json = result.jsonPath();
		String info = json.getString("info");
		assertThat(info, is(equalTo("Todo saved! Nice job!")));		
	}

	@Test(priority = 2)
	public void getAllToDo() {
		
		Response result = doGetAll();
		System.out.println(result.asPrettyString());		
		JsonPath json = result.jsonPath();
		
		File jsonFile = new File("todo.json");
		JsonPath jsonFromFile = JsonPath.from(jsonFile);
		String title = jsonFromFile.getString("title");
		System.out.println(title);

		id = json.getString("find{it.title == '"+title+"'}._id");
		System.out.println(id);	
	}
	
	@Test(priority = 3)
	public void deleteToDo() {
		Response result = doDelete(id);
		System.out.println(result.asPrettyString());
		
		JsonPath json = result.jsonPath();
		String msg = json.getString("msg");
		assertThat(msg, is(equalTo("Event deleted.")));		
	}
}
