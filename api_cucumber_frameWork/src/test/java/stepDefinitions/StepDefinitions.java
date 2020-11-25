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
import resources.TestData;
import resources.Utils;


public class StepDefinitions extends Utils {        // CTRL + SHIFT + O > to import all packages at once //	
	
	RequestSpecification req;
    TestData data;
	Response response;
	JsonPath jsp;
	
	@Given("Place payload {string} {string} {string} {string}")
	public void place_payload(String accuracy, String name, String phone_no, String address) throws IOException {

		        data = new TestData();				
				req = given().spec(requestSpecification()).body(data.addPlacePayload(accuracy, name, phone_no, address));
	}
	

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
		
		response = req.when().post("maps/api/place/add/json")
		           .then().spec(responseSpecification())
                   .assertThat().extract().response();
		           jsp = new JsonPath(response.asString());  // .asString() or .toString() either we can use //
	}
	
	@Then("API call is successfull with status code {int}")
	public void api_call_is_successfull_with_status_code(Integer statusCode) {	
                 
		 assertEquals(response.getStatusCode(), 200);
	}
	
	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String keyValue, String expectedValue) {          
		        
		 assertEquals(jsp.getString(keyValue), expectedValue);	        
	}
	

}
