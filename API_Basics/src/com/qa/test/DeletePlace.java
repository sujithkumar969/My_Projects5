package com.qa.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeletePlace {

	@Test
	public void deletePlaceTest() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RequestSpecification reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.build();
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();
		
		String response = given().spec(reqSpec).body("{     \"place_id\":\"f7eb728c591b74b2252aa18ee6cf417f\" }")
		.when().delete("maps/api/place/delete/json")
		.then().assertThat().extract().response().asString();
		
		System.out.println(response);

	}

}
