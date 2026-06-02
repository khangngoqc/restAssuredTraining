package day5;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;	


public class ParsingXMLResponse {

	//@Test
	void testXMLResponse() {
		
		//Approach1
		/*
		 * given()
		 * 
		 * .when() .get("https://mocktarget.apigee.net/xml") .then() .statusCode(200)
		 * .header("Content-Type","application/xml; charset=utf-8") .body("root.city",
		 * equalTo("San Jose")) .body("root.firstName", equalTo("John"));
		 */
		
		//Approach 2
		
		Response res = 
				given()
				
				.when()
					.get("https://mocktarget.apigee.net/xml");	
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
	
		String city = res.xmlPath().get("root.city").toString();
		assertEquals(city, "San Jose");
		
		String firstName = res.xmlPath().get("root.firstName").toString();
		assertEquals(firstName, "John");

	}
	
	@Test
	void testXMLResponseBody() {
		
		Response res = 
				given()
				
				.when()
					.get("https://cdn.animenewsnetwork.com/encyclopedia/api.xml?title=~isekai");	
		
		XmlPath xmlobj = new XmlPath(res.asString());
		
		List<String> mangas = xmlobj.getList("ann.manga");
		Assert.assertEquals(mangas.size(), 49);
		
		//verify staff name is present in the response
		//List<String> staffNames = xmlobj.getList("ann.manga.staff.person");
	
		List<String> staffNames = xmlobj.getList("ann.anime.cast.person");
		
		boolean status = false;
		
		for (String name : staffNames) {
			System.out.println(name);
			
			if(name.equals("Saori Hayami")) {
				status = true;
				break;
			};
		}
		
		assertEquals(status, true);
		
	
	}
	
}
