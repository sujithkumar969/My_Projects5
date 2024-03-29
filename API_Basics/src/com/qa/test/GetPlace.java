package com.qa.test; // program to getplace from google server, addplace should be executed before executing getplace //

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetPlace {

	@Test
	public void getPlaceTest() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RequestSpecification reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123").addQueryParam("place_id", "f7eb728c591b74b2252aa18ee6cf417f").build();
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();
		
		RequestSpecification req = given().spec(reqSpec);
		
		String response = req.when().get("maps/api/place/get/json")
		.then().spec(respSpec).assertThat().body("name", equalTo("Sujith House1"))
		.body("phone_number", equalTo("(+91) 7259310103"))
		.extract().response().asString();
		
		System.out.println("----------extract response as string------------------");
		System.out.println(response);
		System.out.println("------------------------------------------------------");
		
		JsonPath jsp = new JsonPath(response);   // parsing the json path //
		String name = jsp.getString("name");
		String phone_number = jsp.getString("phone_number");
		String address = jsp.getString("address");
		String types = jsp.getString("types");
		String website = jsp.getString("website");
		String language = jsp.getString("language");
		
		System.out.println(name);
		System.out.println(phone_number);
		System.out.println(address);
		System.out.println(types);
		System.out.println(website);
		System.out.println(language);
		
		
	}


}
