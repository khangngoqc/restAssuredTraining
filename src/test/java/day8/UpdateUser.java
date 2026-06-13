package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void test_updateUser(ITestContext context) {
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", faker.demographic().sex());
		data.put("email", faker.internet().emailAddress());
		data.put("status", faker.options().option("active", "inactive"));
		
		//int id = (int) context.getAttribute("user_id"); //this should come form createUser request
		int id = (int) context.getSuite().getAttribute("user_id"); //this should come form createUser request
		
		String bearerToken = "token";
		
		
		given()
			.header("Authorization", "Bearer " + bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		
		//System.out.println("Generated id: " + id);
	}
		
		
}
