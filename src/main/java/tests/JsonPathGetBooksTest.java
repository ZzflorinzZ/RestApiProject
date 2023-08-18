package tests;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponentHomeworkEx1;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class JsonPathGetBooksTest extends BaseComponentHomeworkEx1{
	
	
	@Test
	public void pageCount() {
		
		Response result = doGet();
		JsonPath json = result.jsonPath();
		
		List<String> pageCount = json.getList("findAll{it.pageCount >= 1000 && it.pageCount <= 2000}");
		System.out.println(pageCount);
		
		System.out.println(pageCount.size());
		assertThat(pageCount.size(), is(equalTo(11)));		
	}

}
