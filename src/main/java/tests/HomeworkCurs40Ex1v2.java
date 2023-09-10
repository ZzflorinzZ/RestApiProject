package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponentHomeworkC40Ex1v2;
import utils.DataBuilder;

public class HomeworkCurs40Ex1v2 extends BaseComponentHomeworkC40Ex1v2{
	
	public static String validToken;
	public static String id;
	
//	@Test(priority = 1)
	public void generateTokenToDo() {
		Response resp = doRequest("postRetriveToken", "", DataBuilder.buildToken().toJSONString());
		System.out.println("=====1111111111111111111111111111111111111111111111=====");
		System.out.println(resp.asPrettyString());	
		validToken = resp.jsonPath().getString("token");
		System.out.println(token);
		System.out.println(validToken);
		
		id = resp.jsonPath().getString("id");
		System.out.println(id);

		assertThat(token, is(notNullValue()));
		assertThat(id, is(notNullValue()));		
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
	
//	@Test(priority = 1)
	public void initToken() {
		token = "wrongToken";
	}
	
//	@Test(priority = 2)
	public void postWithoutAuth() {
		System.out.println("==2==");
		System.out.println(token);
		
		Response resp = doRequest("post", "", DataBuilder.buildTodo().toJSONString());
		System.out.println("=====2222222222222222222222222222222222222222222222=====");
		System.out.println(resp.asPrettyString());	
		
		String msg = resp.jsonPath().getString("msg");
		System.out.println(msg);
		assertThat(msg, equalTo("Sorry, you are not authorized"));		//se duce pe request cu token = "genericValue" si intoarce {"info": "Todo saved! Nice job!", si id-ul}
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
	
	@Test(priority = 3)
	public void postWithAuth() {
		System.out.println(token);
		Response resp = doRequest("post", "", DataBuilder.buildTodo().toJSONString());
		System.out.println("=====3333333333333333333333333333333333333333333333=====");
		System.out.println(resp.asPrettyString());	
		
		id = resp.jsonPath().getString("id");
		System.out.println(id);
		assertThat(id, is(notNullValue()));
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}

	
	@Test(priority = 5)
	public void deleteWithWrongToken() {
		Response resp = doRequest("delete", id, "");
		System.out.println("=====5555555555555555555555555555555555555555555555=====");
		System.out.println(resp.asPrettyString());	
		String msg = resp.jsonPath().getString("msg");
		System.out.println(msg);
		assertThat(msg, equalTo("Wrong token"));
		
		int StatusCode = resp.getStatusCode();
		System.out.println(StatusCode);
	}
}
