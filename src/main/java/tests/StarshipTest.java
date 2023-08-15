package tests;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import static utils.CargoCapacityCheck.hasCargoCapacity;

public class StarshipTest extends BaseComponent{
	
	@Test
	public void checkStarship() {
		Response response = doGetRequest("/api/starships/3/", 200);
		System.out.println(response.asPrettyString());
		
		JsonPath json = response.jsonPath();
		
		List<String> films = json.getList("films");	
		assertThat(films, either(hasItem("https://swapi.dev/api/films/2/"))
				.or(hasItem("https://swapi.dev/api/films/6/"))
				.or(hasItem("https://swapi.dev/api/films/5/")));
		
		assertThat(films, is(not(emptyCollectionOf(String.class))));
		
		List<String> pilots = json.getList("pilots");
		assertThat(pilots, is(emptyCollectionOf(String.class)));
		
		String model = json.getString("model");
		assertThat(model, both(containsString("Imperial")).and(containsString("Destroyer")));
		
		String cargoCapacity = json.getString("cargo_capacity");
		assertThat(cargoCapacity, hasCargoCapacity());
		
		String length = json.getString("length").replace(",", "");
		System.out.println(Integer.parseInt(length));
		assertThat(Integer.parseInt(length), either(is(greaterThan(1570))).or(is(lessThan(1630))));

	}

}
