package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//pojo --serialization--> JSON Object --deserialization--> pojo

public class SerializationDeserialization {

	
	//Pojo -------> JSON (serialization)
	//@Test
	void convertPojo2Json() throws JsonProcessingException {
		
		//created java object using pojo class
		Student stuPojo =  new Student(); //pojo class object
		stuPojo.setName("Lynn");
		stuPojo.setLocation("Germany");
		stuPojo.setPhone("215237348");
		
		String courseArr[] = {"HTML", "CSS"};
		stuPojo.setCourses(courseArr);
		
		
		//convert java object --> json object (serialization)
		ObjectMapper objMapper =  new ObjectMapper();
		
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuPojo);

		System.out.println(jsondata);
		
	}

	//Json -------> Pojo (de-serialization)
	@Test
	void convertJson2Pojo() throws JsonProcessingException {
		
		String jsondata = "{\r\n"
				+ "  \"name\" : \"Lynn\",\r\n"
				+ "  \"location\" : \"Germany\",\r\n"
				+ "  \"phone\" : \"215237348\",\r\n"
				+ "  \"courses\" : [ \"HTML\", \"CSS\" ]\r\n"
				+ "}";
		
		//convert json data ---> Pojo object
		ObjectMapper objMapper =  new ObjectMapper();
		Student stuPojo = objMapper.readValue(jsondata, Student.class);
		
		System.out.println("Name: " + stuPojo.getName());
		System.out.println("Location: " + stuPojo.getLocation());
		System.out.println("Phone: " + stuPojo.getPhone());
		System.out.println("Course 1: " + stuPojo.getCourses()[0]);
		System.out.println("Course 2: " + stuPojo.getCourses()[1]);
	}
	
	
	
}
