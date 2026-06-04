package day6;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;	

public class XMLSchemaValidation {

	@Test
	void xmlSchemaValidation() {
		given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml")
		
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("generated-schema.xsd"));
		
	}
}
