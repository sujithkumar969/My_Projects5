package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.TestData;
import resources.Utils;


public class StepDefinitions extends Utils {        // CTRL + SHIFT + O > to import all packages at once //	
	
	RequestSpecification req;
    static TestData data;
	Response response;
	JsonPath jsp;
	public static String placeId;  // a variable's data if we are using across the multiple tests in single run, that variable should be static. //
	                        // If a scenario or a test execution is completed, then all non static global variables will become null //
	
	@Given("Place payload {string} {string} {string} {string}")
	public void place_payload(String accuracy, String name, String phone_no, String address) throws IOException {

		        data = new TestData();				
				req = given().spec(requestSpecification()).body(data.addPlacePayload(accuracy, name, phone_no, address));
	}
	

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
		
    	ApiResources apiResource = ApiResources.valueOf(resource); // it returns ApiResource object //
    	System.out.println(apiResource + "------" + httpMethod);
    	
    	if("post".equalsIgnoreCase(httpMethod)) {
    		
		    response = req.when().post(apiResource.getResourceURL())
		               .then().spec(responseSpecification())
                       .assertThat().extract().response();
    	}
    	else if ("get".equalsIgnoreCase(httpMethod)) {
			
    		response = req.when().get(apiResource.getResourceURL())
 		              .then().spec(responseSpecification())
                      .assertThat().extract().response();	
		}
    	else if ("put".equalsIgnoreCase(httpMethod)) {
			
    		response = req.when().put(apiResource.getResourceURL())
 		              .then().spec(responseSpecification())
                      .assertThat().extract().response();
		}
    	else if ("delete".equalsIgnoreCase(httpMethod)) {
			
    		response = req.when().delete(apiResource.getResourceURL())
 		              .then().spec(responseSpecification())
                      .assertThat().extract().response();
		}
    	
	}
	
	@Then("API call is successfull with status code {int}")
	public void api_call_is_successfull_with_status_code(Integer statusCode) {	
                 
		 assertEquals(response.getStatusCode(), 200);
	}
	
	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String keyValue, String expectedValue) {          
		        
		 assertEquals(getResponseValue(response,keyValue), expectedValue);	        
	}
	
	@Then("verify that place Id maps to {string} by calling {string} with {string} http request")
	public void verify_that_maps_to_by_calling_with_http_request(String name, String resource, String httpMethod) throws IOException {
		
		placeId = getResponseValue(response, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, httpMethod);
		assertEquals(getResponseValue(response, "name"), name);
	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
	    
		req = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

	
}
