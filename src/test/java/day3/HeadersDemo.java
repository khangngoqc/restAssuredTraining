package day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class HeadersDemo {
	
	//@Test(priority = 1)
	void testHeaders() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
			
		
		.then()
			.header("content-type", "text/html; charset=ISO-8859-1")
			.and()
			.header("content-encoding","gzip")
			.and()
			.header("server", "gws");
	}
	
		
	@Test(priority = 2)
	void getCookiesInfo() {
		
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single header info
		String header_value = res.getHeader("content-type");
		System.out.println("The value of Content-Type header is: "  + header_value);
		
		
		//get all headers info
		Headers myHeaders = res.getHeaders();
		
		for(Header h : myHeaders) {
			 System.out.println(h.getName() + " 		" + h.getValue());
		}
		
		
	}
	

}
