package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec; // a variable's data if we are using across the multiple tests in single run, that variable should be static //
	ResponseSpecification respSpec;

	/*
	 * Below method will be commonly used for all tests
	 * RequestSpefication details are captured
	 * Request and Response logs are captured
	 */
	public RequestSpecification requestSpecification() throws IOException {
		
		if(reqSpec==null) {
		
		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt")); // printStream object writes logs to logging.txt file //
		
	    //	RestAssured.baseURI = "https://rahulshettyacademy.com";   - instead of this we are using setBaseUri() method //

		reqSpec = new RequestSpecBuilder().setBaseUri(getBaseUri()).setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();
		return reqSpec;
		}
		return reqSpec;
	}
	
	/*
	 * Below method will be commonly used for all tests
	 * ResponseSpefication details are captured
	 */
	public ResponseSpecification responseSpecification() {
		
		respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
				   expectStatusCode(200).build();	
		return respSpec;
	}
	
	/*
	 * This method returns the baseUri from properties file
	 */
	public static String getBaseUri() throws IOException {
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("E:\\My_Projects5\\api_cucumber_frameWork\\src\\test\\java\\resources\\globalVariables.properties");
		props.load(fis);
		return props.getProperty("baseUri");
	}
	
	
	public String getResponseValue(Response response, String key) {
		
		JsonPath jsp = new JsonPath(response.asString());  // .asString() or .toString() either we can use //
		return jsp.getString(key);
	}

}
