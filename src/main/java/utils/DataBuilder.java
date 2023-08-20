package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder extends JsonUtils{
	
	public static JSONObject buildUser() {		
		JSONObject bodyBuilder = new JSONObject();
		Faker fake = new Faker();
		
		bodyBuilder.put("name", fake.name().firstName());
		bodyBuilder.put("email", fake.internet().safeEmailAddress());
		bodyBuilder.put("age", fake.number().numberBetween(5, 130));
		bodyBuilder.put("gender", "m");
		
		return bodyBuilder;
	}
	
	public static JSONObject updateBuildUser() {		
		JSONObject bodyBuilder = new JSONObject();
		Faker fake = new Faker();
		
		bodyBuilder.put("name", fake.name().firstName());
		bodyBuilder.put("email", fake.internet().safeEmailAddress());
		bodyBuilder.put("age", fake.number().numberBetween(5, 130));
		bodyBuilder.put("gender", "male");
		
		return bodyBuilder;
	}
	 
	
	public static File postToDo(JSONObject jsonObjectName, String jsonFileName){		
		Faker fake = new Faker();
		jsonObjectName.put("title", fake.name().firstName());
		jsonObjectName.put("body", fake.internet().safeEmailAddress());
		
		writeJsonFile(jsonObjectName, jsonFileName); 
		return returnJsonFile(jsonFileName); 	
	}

}
