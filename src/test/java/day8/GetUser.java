package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {

	@Test
	void test_getUser(ITestContext context) 
	{
		//int id = (int) context.getAttribute("user_id"); //this should come form createUser request
		int id = (int) context.getSuite().getAttribute("user_id"); //this should come form createUser request
		
		String bearerToken = "token";
		
		given()
			.header("Authorization", "Bearer " + bearerToken)
			.pathParam("id", id)
			
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	
}
