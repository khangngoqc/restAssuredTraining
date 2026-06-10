package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;	

import org.testng.annotations.Test;

public class Authentications {
	
	//@Test(priority=1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all()
		;
		
	}
	
	//@Test(priority=2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/digest-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all()
		;
		
	}

	//@Test(priority=3)
	void testPreemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all()
		;
		
	}
	
	//@Test(priority=4)
	void testBearerTokenAuthentication() {
		
		String bearerToken = "token";
		
		given()
			.headers("Authorization","Bearer " + bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all()
		;
	}
	
	//@Test
	void testOAuth1Authentication() {
		given()
			//this is for OAuth1.0 authentication
			.auth().oauth("consumerKey", "consumerSecret", "AccessToken", "TokenSecret") 
			
		.when()
			.get("url")
			
		.then()
			.statusCode(200)
			.log().all()
		;
	}
	
	//@Test
	void testOAuth2Authentication() {
		given()
			//this is for OAuth1.0 authentication
			.auth().oauth2("token") 
			
		.when()
			.get("https://api.github.com/user/repos")
			
		.then()
			.statusCode(200)
			.log().all()
		;
	}
	
	@Test
	void testAPIKeyAuthentication() {
		
		/*
		 * given()
		 * 
		 * .when() .get(
		 * "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=a65c450a5bc9749d11c8aee7f642f7c9")
		 * .then() .statusCode(200) .log().all();
		 */
		
		given()
			.pathParam("mypath", "data/2.5/weather")
		
			.queryParam("lat", "10.99")
			.queryParam("lon", "44.34")
			.queryParam("appid", "a65c450a5bc9749d11c8aee7f642f7c9")
			.queryParam("mode", "json")
			.queryParam("units", "standard")
			.queryParam("lang", "vi")
		
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	
	
	}
	
	
	
	
	
	
}

