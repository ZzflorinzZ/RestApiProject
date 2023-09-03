package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponentHomeworkC40Ex1;
import utils.DataBuilder;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HomeworkCurs40Ex1 extends BaseComponentHomeworkC40Ex1{
	
	public static String id;
	
	@Test(priority = 1)
	public void generateTokenToDo() {
		Response resp = doRequest("postRetriveToken", "", DataBuilder.buildToken().toJSONString());
		System.out.println("=====1111111111111111111111111111111111111111111111=====");
		System.out.println(resp.asPrettyString());	
		token = resp.jsonPath().getString("token");
		System.out.println(token);
		
		id = resp.jsonPath().getString("id");
		System.out.println(id);
//		assertThat(token, is(not(null)));	//java.lang.NullPointerException: Cannot invoke "org.hamcrest.Matcher.matches(Object)" because "this.matcher" is null
											//care este diferenta dintre << is(not(null)) >> si << is(notNullValue()) >> ??
		assertThat(token, is(notNullValue()));
		assertThat(id, is(notNullValue()));		
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
	
	@Test(priority = 2)
	public void postWithoutAuth() {
		Response resp = doRequest("postNoAuth", "", DataBuilder.buildTodo().toJSONString());
		System.out.println("=====2222222222222222222222222222222222222222222222=====");
		System.out.println(resp.asPrettyString());	
		String msg = resp.jsonPath().getString("msg");
		System.out.println(msg);
		assertThat(msg, equalTo("Sorry, you are not authorized"));
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
	
	@Test(priority = 3)
	public void postWithAuth() {
		Response resp = doRequest("postAuth", "", DataBuilder.buildTodo().toJSONString());
		System.out.println("=====3333333333333333333333333333333333333333333333=====");
		System.out.println(resp.asPrettyString());	
		
		id = resp.jsonPath().getString("id");
		System.out.println(id);
		assertThat(id, is(notNullValue()));
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}

	@Test(priority = 4)
	public void deleteWithoutToken() {
		Response resp = doRequest("deleteNoAuth", id, "");
		System.out.println("=====4444444444444444444444444444444444444444444444=====");
		System.out.println(resp.asPrettyString());	
		String msg = resp.jsonPath().getString("msg");
		System.out.println(msg);
		assertThat(msg, equalTo("Sorry, you are not authorized"));
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
	
	@Test(priority = 5)
	public void deleteWithWrongToken() {
		Response resp = doRequest("deleteAuthWrongToken", id, "");
		System.out.println("=====5555555555555555555555555555555555555555555555=====");
		System.out.println(resp.asPrettyString());	
		String msg = resp.jsonPath().getString("msg");
		System.out.println(msg);
		assertThat(msg, equalTo("Wrong token"));
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
	
	@Test(priority = 6)
	public void deleteWithAuth() {
		Response resp = doRequest("deleteAuth", id, "");
		System.out.println("=====6666666666666666666666666666666666666666666666=====");
		System.out.println(resp.asPrettyString());	
		String msg = resp.jsonPath().getString("msg");
		System.out.println(msg);
		assertThat(msg, equalTo("Event deleted."));
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
}
