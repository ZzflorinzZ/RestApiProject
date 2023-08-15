package tests;

import java.io.File;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;

public class JsonPathExample extends BaseComponent2{
	
	@Test
	public void getAllUsers() {
		
		Response result = doGetAll();
		System.out.println(result.asString());
		
		JsonPath json = result.jsonPath();
		
		System.out.println(json.getString("users"));
		System.out.println("Get based on index: " + json.getString("users[1]"));
		System.out.println("Get based on index: " + json.getString("users[2]"));
		System.out.println("=================================================");
		System.out.println("Get based on index: " + json.getString("users.email[3]"));
		System.out.println("Get based on index: " + json.getString("users.name[3]"));
		System.out.println("Get based on index: " + json.getString("users._id[3]"));
		System.out.println("=================================================");
		System.out.println(json.getString("users.size()"));
		System.out.println("Get all email addresses: " + json.getString("users.email"));
		System.out.println("Get all ids: " + json.getString("users._id"));
		System.out.println("Get all genders: " + json.getString("users.gender"));
		System.out.println("=================================================");
		List<String> allMales = json.getList("users.findAll{it.gender == 'm'}.gender");
		System.out.println("All males: " + allMales);
		List<String> allMalesEmail = json.getList("users.findAll{it.gender == 'm'}.email");
		System.out.println("All males emails: " + allMalesEmail);
//		List<String> allMalesIds = json.getList("users.findAll{it.gender == 'm'}._id");
//		System.out.println("All males ids: " + allMalesIds);
		System.out.println("All males ids: " + json.getString("users.findAll{it.gender == 'm'}._id"));
		System.out.println("=================================================");
		List<String> allFritz = json.getList("users.findAll{it.name == 'Fritz'}.age");
		System.out.println(allFritz);
		System.out.println(allFritz.size());
		String fritzObject = json.getString("users.find{it.name == 'Fritz' && it.age ==45}");
		System.out.println(fritzObject);
		String fritzObjectEmail = json.getString("users.find{it.name == 'Fritz' && it.age ==45}.email");
		System.out.println(fritzObjectEmail);
		List<String> allOlderFritz = json.getList("users.findAll{it.name == 'Fritz' && it.age >=45}");
		System.out.println(allOlderFritz);
		List<String> allOlder = json.getList("users.findAll{it.age >=45}");
		System.out.println(allOlder);
		List<String> allNames = json.getList("users.findAll{it.name == 'Fritz' | it.name == 'Nathanael80'}");
		System.out.println(allNames);
		
		
		//JSON PATH in json file vs.JSON PARSER
		//Run multiple JSON objects from jason file(Postman example)
		
		File jsonFile = new File("data.json");
		JsonPath json2 = JsonPath.from(jsonFile);
		System.out.println(json2.getString("body"));
	}

}
