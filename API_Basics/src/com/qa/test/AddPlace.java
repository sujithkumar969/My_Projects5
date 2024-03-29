package com.qa.test;

import org.testng.annotations.Test;

import com.qa.data.PayLoad;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddPlace {
	
	String place_id;     

	@Test
	public void addPlaceTest() {
		
//-------------------------- Below lines are common for all test scripts -------------------------------//
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		RequestSpecification reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).
				addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
				expectStatusCode(200).build();
//-----------------------------------------------------------------------------------------------------//
		
		RequestSpecification req = given().spec(reqSpec).body(PayLoad.addPlace());
		
		String response = req.when().post("maps/api/place/add/json")
		                  .then().spec(respSpec).body("scope", equalTo("APP"))
		                  .body("status", equalTo("OK"))
		                  .assertThat().extract().response().asString();		         
		System.out.println(response);
		
		JsonPath jsp = new JsonPath(response);
		this.place_id = jsp.getString("place_id");
		System.out.println(place_id);
	}

}
