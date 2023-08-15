package tests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import static utils.IntegerChecker.integerOnly;

public class PeopleTest extends BaseComponent{
	
	@Test
	public void checkPeople() {
		
		Response response = doGetRequest("/api/people/1/", 200);
		System.out.println(response.asPrettyString());
		
		JsonPath json = response.jsonPath();
		
		String nume = json.getString("name");
		assertThat(nume, is(equalTo("Luke Skywalker")));
		
		String height = json.getString("height");
		assertThat(height, is(greaterThan("171")));
		
		String mass = json.getString("mass");
		assertThat(mass, is(lessThan("80")));
		
		List<String> personAttributesColor = new ArrayList<>();
		personAttributesColor.add(json.getString("hair_color"));
		personAttributesColor.add(json.getString("skin_color"));
		personAttributesColor.add(json.getString("eye_color"));
		assertThat(personAttributesColor, contains("blond", "fair", "blue"));
		
		String birthYear = json.getString("birth_year");
		assertThat(birthYear, is(not(integerOnly())));
		
		List<String> species = json.getList("species");
		assertThat(species, hasSize(0));
		
		List<String> starships = json.getList("starships");
		List<String> vehicles = json.getList("vehicles");
		assertThat(starships, hasSize(equalTo(vehicles.size())));
		assertThat(starships, is(not((vehicles))));

	}

}
