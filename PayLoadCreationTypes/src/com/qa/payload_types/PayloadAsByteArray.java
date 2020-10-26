package com.qa.payload_types;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PayloadAsByteArray {

	
	@Test
	public void AddBook4() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		RequestSpecification reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		byte[] myJson = Files.readAllBytes(Paths.get("E:\\My_Projects5\\PayLoadCreationTypes\\JsonPayload\\AddBook.json"));
		
		RequestSpecification request = given().log().all().spec(reqSpec).body(myJson);
		
		String response = when().post("Library/Addbook.php")
				.then().log().all().spec(respSpec).extract().response().asString();
		
		System.out.println("-----------------------------------------");
		System.out.println(response);
		System.out.println("-----------------------------------------");
		
	}
}
