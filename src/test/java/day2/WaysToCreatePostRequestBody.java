package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


/*
How many ways we create request body
-------------------------------------
1) HashMap
2) using org.json
3) using POJO(Plain Old Java Object)
4) using external json file

*/

public class WaysToCreatePostRequestBody {

	
	//1.HashMap
	//@Test(priority = 1)
	public void  testPostUsingHashMap() {
		
		HashMap data = new HashMap();
		data.put("name", "Prisz");
		data.put("location", "UK");
		data.put("phone", "315135");
		
		String courseArr[] = {"C#", "Python"};
		
		data.put("course", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Prisz"))
			.body("location", equalTo("UK"))
			.body("phone", equalTo("315135"))
			.body("course[0]", equalTo("C#"))
			.body("course[1]", equalTo("Python"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
		
		
	}
	
	

	//2. using org.json
	//@Test(priority = 1)
	public void testPostUsingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		
		data.put("name" , "Peter");
		data.put("location" , "US");
		data.put("phone" , "235235235");
		
		String courseArr[] = {"Selenium", "Java"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/students")
			
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Peter"))
			.body("location", equalTo("US"))
			.body("phone", equalTo("235235235"))
			.body("courses[0]", equalTo("Selenium"))
			.body("courses[1]", equalTo("Java"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
		
		
	}
	
	//3. using POJO class
	//@Test(priority = 1)
	public void testPostUsingPOJO() {
		
		Pojo_PostRequest data =  new Pojo_PostRequest();
		data.setName("Lynn");
		data.setLocation("Germany");
		data.setPhone("215237348");
		
		String courseArr[] = {"HTML", "CSS"};
		data.setCourses(courseArr);

		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		
		.then()
			.statusCode(201)
			.body("name", equalTo(data.getName()))
			.body("location", equalTo(data.getLocation()))
			.body("phone", equalTo(data.getPhone()))
			.body("courses[0]", equalTo(data.getCourses()[0]))
			.body("courses[1]", equalTo(data.getCourses()[1]))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
		
		
	}
	
	
	//4. using external json file
	@Test(priority = 1)
	public void testPostUsingExternalJson() throws FileNotFoundException {
			
		String filepath = "D:\\Personal\\Learning\\API Testing\\[SDET-QA] API Testing\\RestAssured\\restAssuredTraining\\RestAssuredTraining\\src\\test\\java\\day2\\body.json";
		
		File file = new File(filepath);
			
		FileReader fr = new FileReader(file);
			
		JSONTokener jt = new JSONTokener(fr);

		JSONObject data = new JSONObject(jt);
			
			
			
		given()
				.contentType("application/json")
				.body(data.toString())
				
			.when()
				.post("http://localhost:3000/students")
				
			
			.then()
				.statusCode(201)
				.body("name", equalTo("Andy"))
				.body("location", equalTo("Sweden"))
				.body("phone", equalTo("46547458"))
				.body("courses[0]", equalTo("Appium"))
				.body("courses[1]", equalTo("Kotlin"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
			
		}
	
	
	//deleting student record
	@Test(priority = 2)
	void testDelete() {
			given()
			
			.when()
				.delete("http://localhost:3000/students/17")
				
			.then()
				.statusCode(200);
				
	}
		
	
}
