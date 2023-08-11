package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import static utils.IntegerChecker.integerOnly;;

public class HamcrestMatcherExample extends BaseComponent{
	
	@Test
	public void hamcrestMatcher() {
		
		Response resp = doGetRequest("/api/planets/1", 200);
		System.out.println(resp.asPrettyString());
		
		JsonPath json = resp.jsonPath();
		
		String nume = json.getString("name");
		System.out.println("Valoare name: " + nume);
		
		assertEquals(nume, "Tatooine");
		
		assertThat(nume, equalTo("Tatooine"));
		assertThat(nume, is(equalTo("Tatooine")));
		assertThat(nume, is("Tatooine"));
		
		assertNotEquals(nume, "Terra");
		
		assertThat(nume, is(not("Terra")));
		assertThat(nume, is(not(equalTo("Terra"))));
		assertThat(nume, is(not(instanceOf(Integer.class))));
		assertThat(nume, is(instanceOf(String.class)));
		
		//starts-with
		assertThat(resp.asString(), startsWith("{\"name\""));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME\""));
		
		//ends-with
		assertThat(resp.asString(), endsWith("/planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("aPi/pLaNeTs/1/\"}"));
		
		//contains
		assertThat(resp.asString(), containsString("gravity"));
		assertThat(nume, containsStringIgnoringCase("tatoo"));
		
		//matches pattern
		assertThat(json.getString("climate"), matchesPattern("[A-Za-z]+"));
		assertThat(json.getString("gravity"), matchesPattern("[A-Za-z0-9\\s]+"));
		assertThat(json.getString("diameter"), matchesPattern("[0-9]+"));
		
		//collections
		List<String> films = json.getList("films");
		System.out.println(films);
		
		assertThat(films, contains(
				"https://swapi.dev/api/films/1/",
		        "https://swapi.dev/api/films/3/",
		        "https://swapi.dev/api/films/4/",
		        "https://swapi.dev/api/films/5/",
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, containsInAnyOrder(
				"https://swapi.dev/api/films/4/",
		        "https://swapi.dev/api/films/3/",
		        "https://swapi.dev/api/films/1/",
		        "https://swapi.dev/api/films/6/",
		        "https://swapi.dev/api/films/5/"));
		
		assertThat(films, contains(
				startsWith("https"),
				endsWith("3/"),
				equalTo("https://swapi.dev/api/films/4/"),
				containsString("swapi"),
				equalTo("https://swapi.dev/api/films/6/")));
		
		assertThat(films, hasItem("https://swapi.dev/api/films/6/"));
		
		assertThat(films, hasItems(
				startsWith("https"),
				endsWith("3/")));
		
		assertThat(films, hasSize(5));
		
		assertThat(films, hasSize(lessThanOrEqualTo(10)));
		
		assertThat(films, hasSize(greaterThan(3)));
		
		assertThat(films, both(hasSize(lessThan(10))).and(hasToString(containsString("swapi"))));
		
		//and
		String gravity = json.getString("gravity");
		assertThat(gravity, both(containsString("1")).and(containsString("standard")));
		
		//or
		assertThat(nume, either(is("Terra")).or(is("Mars")).or(is("Tatooine")));
		
		String diameter = json.getString("diameter");
		String gravity2 = json.getString("gravity");
		assertThat(diameter, is(integerOnly()));
		assertThat(gravity2, is(not(integerOnly())));
	}

}
