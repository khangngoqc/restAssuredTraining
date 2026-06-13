package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void test_deleteUser(ITestContext context) {
		
		//int id = (int) context.getAttribute("user_id"); //this should come form createUser request
		int id = (int) context.getSuite().getAttribute("user_id"); //this should come form createUser request

		String bearerToken = "token";
		
		given()
			.header("Authorization", "Bearer " + bearerToken)
			.pathParam("id", id)
			
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			;
 
		
	}
	
}
