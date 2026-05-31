package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.json.JSONObject;	

public class ParsingJSONResponseData {

	//@Test(priority=1)
	void testJsonResponse() {
		//Approach 1
		/*
		 * given() .contentType("ContentType.JSON")
		 * 
		 * .when() .get("http://localhost:3000/store")
		 * 
		 * .then() .statusCode(200) .header("Content-Type",
		 * "application/json; charset=utf-8") .body("book[3].title",
		 * equalTo("The Lord of the Rings"));
		 */
		
		
		//Approach 2
		Response res = given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(),200, "Incorrect status code!");
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName , "The Lord of the Rings");
		
	}
	
	
	@Test(priority=2)
	void testJsonResponseBodyData() {
	
		Response res = given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/store");
		
		
		///JSONObject Class
		JSONObject jo = new JSONObject(res.asString()); //converting response to json object type
		
		boolean status = false;
		
		//search for title of a book on json - valiation 1
		for(int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			//System.out.println(bookTitle);
			
			if (bookTitle.equals("The Lord of the Rings")) {
				status = true;
				break;
			}
		};
		
		Assert.assertEquals(status, true, "The book is not avaible in store!"); 
		
		double totalPrice = 0;
		
		//validate total price of books
		for(int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			
			//System.out.println(bookPrice);
			
			totalPrice = totalPrice + Double.parseDouble(price);
			
		};
		
		System.out.println("The total price of books is: "+ totalPrice);
		
		Assert.assertEquals(totalPrice, 53.29 ,"Incorrect total price!");
		
	}
	
	
	
	
	
	
	
}
