package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import org.testng.annotations.Test;

import add_place_pojo_serialization.AddPlace;
import add_place_pojo_serialization.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlaceTest {     // Serialization -> java object to json format //
	
@Test
public void addPlaceTest() {

	AddPlace payload = new AddPlace();
	payload.setAccuracy(50);
	payload.setName("Sujith House1");
	payload.setPhone_number("(+91) 7259310103");
	payload.setAddress("29, side layout, cohen 09");
	
	ArrayList<String> list = new ArrayList<String>();
	list.add("prince park");
	list.add("shop");
	payload.setTypes(list);
	
	Location lcn = new Location();
	lcn.setLat(-38.383494);
	lcn.setLng(33.427362);
	payload.setLocation(lcn);
	
	payload.setWebsite("https://rahulshettyacademy.com");
	payload.setLanguage("French-IN");
	
	
	//-------------------------- Below lines are common for all test scripts -------------------------------//
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
			RequestSpecification reqSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).
					addQueryParam("key", "qaclick123").build();
			
			ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
					expectStatusCode(200).build();
	//-----------------------------------------------------------------------------------------------------//
			
			RequestSpecification req = given().spec(reqSpec).body(payload);
			
			String response = req.when().post("maps/api/place/add/json")
			                  .then().spec(respSpec).body("scope", equalTo("APP"))
			                  .body("status", equalTo("OK"))
			                  .assertThat().extract().response().asString();		         
			System.out.println(response);

}

}
