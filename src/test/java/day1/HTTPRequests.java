package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


/*
	given()
		content type, set cookies, add auth, add param, set headers info etc...

	when()
		get, post, put, delete

	then()
		validate status code, extract response, extract headers, cookies & repsonse body...
		
*/


public class HTTPRequests {
	
	int id;
	
	@Test(priority = 1)
	void getUser() {
		
		given()
			.header("x-api-key", "reqres_07fb2db265bd4c91965adb445f84f96d")
		
		.when()
			.get("https://reqres.in/api/user?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("name", "Bob");
		data.put("job", "trainer");
		
		
		id=given()
			.header("x-api-key", "reqres_07fb2db265bd4c91965adb445f84f96d")
			.contentType("application/json")
			.body(data)


		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
			//.then()
			//.statusCode(201)
			//.log().all();
		
	}
	
	@Test(priority=3, dependsOnMethods = {"createUser"})
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "kakashi");
		data.put("job", "teacher");
		
		
		given()
			.header("x-api-key", "reqres_07fb2db265bd4c91965adb445f84f96d")
			.contentType("application/json")
			.body(data)


		.when()
			.put("https://reqres.in/api/users/" + id)
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority = 4)
	void deleteUser() {
		
		given()
			.header("x-api-key", "reqres_07fb2db265bd4c91965adb445f84f96d")

		.when()
			.delete("https://reqres.in/api/users/" + id)
		
		.then()
			.statusCode(204)
			.log().all();
		
	}
	
	

}
