package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;	


import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class CreateUser {
	
	@Test
	void test_createUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", faker.demographic().sex());
		data.put("email", faker.internet().emailAddress());
		data.put("status", faker.options().option("active", "inactive"));
		
		String bearerToken = "token";
		
		
		int id = given()
			.header("Authorization", "Bearer " + bearerToken)
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		
		System.out.println("Generated id: " + id);
		
		//context.setAttribute("user_id", id);
		
		context.getSuite().setAttribute("user_id", id);
		
		
	}

}
