package day5;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;	

public class FileUploadAndDownload {
	
	@Test(priority=1)
	void singleFileUpload() {
	
		File myfile = new File("D:\\TestFile.txt");
		
		given()
			.multiPart("file", myfile)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadFile")
		
		.then()
			.statusCode(200)
			.body("fileName", equalTo("TestFile.txt"))
			.log().all();
	}

	
	//@Test
	void multiFileUpload() {
	
		File myfile = new File("D:\\Test1.png");
		File myfile2 = new File("D:\\Test2.png");
		
		given()
			.multiPart("files", myfile)
			.multiPart("files", myfile2)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Test1.png"))
			.body("[1].fileName", equalTo("Test2.png"))

			.log().all();
	}

	//@Test
	void multiFileUpload2() {
	
		File myfile = new File("D:\\Test1.png");
		File myfile2 = new File("D:\\Test2.png");
		
		File filearr[] = {myfile, myfile2};
		
		given()
			.multiPart("files", filearr)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Test1.png"))
			.body("[1].fileName", equalTo("Test2.png"))

			.log().all();
	}
	
	@Test(priority=2)
	void fileDownload() {
		
		given()
		
	.when()
		.get("http://localhost:8080/downloadFile/TestFile.txt")
	
	.then()
		.statusCode(200)
		.log().body();
		
	}
	

	
}
