package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {
	
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
	
	
	public static File postToDo() throws IOException {		
		JSONObject bodyBuilder = new JSONObject();
		Faker fake = new Faker();
		
		bodyBuilder.put("title", fake.name().firstName());
		bodyBuilder.put("body", fake.internet().safeEmailAddress());
		
//		FileWriter file = null;
		try {
			FileWriter file = new FileWriter("todo.json");
			file.write(bodyBuilder.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		File fisier = new File ("todo.json");
/*		
		try (FileWriter file = new FileWriter("todo.json")){
			file.write(bodyBuilder.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}  
*/		
		return fisier; 
	}

}
