package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class StepDefinitions {        // CTRL + SHIFT + O > to import all packages at once //	
	
	@Given("Place payload")
	public void place_payload() {
		
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
	@When("user calls AddPlaceAPI with POST http request")
	public void user_calls_add_place_api_with_post_http_request() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("API call is successfull with status code {string}")
	public void api_call_is_successfull_with_status_code(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	

}
