package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	@Test(priority = 1)
	void testLogs() 
	{
	
		given()
		.header("x-api-key","reqres_07fb2db265bd4c91965adb445f84f96d")
	
	.when()
		 .get("https://reqres.in/api/users?id=5")
	
	.then()
		//.log().body()
		//.log().cookies()
		//.log().headers()
		.log().all();
		
	}

}
