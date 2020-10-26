package com.qa.payload_types;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HashMapPayload {
	
	
@Test
public void AddBook1() {
	
	RestAssured.baseURI = "http://216.10.245.166";
	RequestSpecification reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
	ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	
	
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("name", "Learn Appium Automation with Java");
	map.put("isbn", "bcd");
	map.put("aisle", "0002");
	map.put("author", "sujith kumar");
	
	RequestSpecification request = given().log().all().spec(reqSpec).body(map);
	
	String response = when().post("Library/Addbook.php")
			.then().log().all().spec(respSpec).extract().response().asString();
	
	System.out.println("-----------------------------------------");
	System.out.println(response);
	System.out.println("-----------------------------------------");
	
}
	
	
	

}
