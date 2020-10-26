package com.qa.payload_types;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PayloadAsString {

	@Test
	public void AddBook2() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		RequestSpecification reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		RequestSpecification request = given().log().all().spec(reqSpec).body("{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\"bcd\",\r\n" + 
				"\"aisle\":\"0001\",\r\n" + 
				"\"author\":\"sujith kumar\"\r\n" + 
				"}");
		
		String response = when().post("Library/Addbook.php")
				.then().log().all().spec(respSpec).extract().response().asString();
		
		System.out.println("-----------------------------------------");
		System.out.println(response);
		System.out.println("-----------------------------------------");
		
	}
		
}