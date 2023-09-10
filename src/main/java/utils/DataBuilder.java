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
	
	public static JSONObject buildToken() {
		JSONObject buildToken = new JSONObject();
		buildToken.put("username", "admin");
		buildToken.put("password", "password123");
		return buildToken;
	}
	
	public static JSONObject buildBooking() {
		JSONObject buildBooking = new JSONObject();
		buildBooking.put("firstname", "Jim");
		buildBooking.put("lastname", "Brown");
		buildBooking.put("totalprice", "111");
		buildBooking.put("depositpaid", "true");	
			JSONObject BookingDates = new JSONObject();
			BookingDates.put("checkin", "2018-01-01");
			BookingDates.put("checkout", "2019-01-01");	
		buildBooking.put("bookingdates", BookingDates);
		buildBooking.put("additionalneeds", "Breakfast");

		return buildBooking;
	}
	
	public static JSONObject buildTodo() {
		Faker fake = new Faker();

		JSONObject buildToDo = new JSONObject();
		buildToDo.put("title" , fake.leagueOfLegends().champion());
		buildToDo.put("body" , fake.chuckNorris().fact());

		return buildToDo;
	}

}
