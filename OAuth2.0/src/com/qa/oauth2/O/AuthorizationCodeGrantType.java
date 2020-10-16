package com.qa.oauth2.O;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AuthorizationCodeGrantType {

@Test
	public void testAuthorisationCode() {
		
	  //  google website will not support for automated login so we are doing manual interaction //
	/*  System.setProperty("webdriver.chrome.driver","E:\\automation tool\\Selenium Softwares(new)\\drivers executables\\chromedriver 2.35\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyjsdss");
		driver.manage().window().maximize();
		driver.findElement(By.id("identifierId")).sendKeys("sujithkumar969@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		driver.findElement(By.id("")).sendKeys("aa---aa");
		driver.findElement(By.id("")).click();    
		String URL = driver.getCurrentUrl();
		String partialCode = URL.split("code=")[1];
		String code = partialCode.split("&scope")[0];   */
	
        String code = "4%2F5QFgl3JA83In3NslIEjQJK428nZNzS7UVqJpjCsgOAerzIFn8aHFvU_SN_LNRbC44TRU38Chfoi6JF2Bbz7ZlvI";
	
		RequestSpecification reqspec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
		ResponseSpecification responspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
/* urlEncodingEnabled(false) -> we have make url encoding as false because, RESTASSURED will not encode any special character in URL. Ex: % .
		Otherwise wrong URL will be passed to RESTASSURED */
		RequestSpecification request1 = given().spec(reqspec).urlEncodingEnabled(false)
			   .queryParams("code", code)
		       .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		       .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		       .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		       .queryParams("grant_type", "authorization_code");
	    
		String response1 = request1.when().post("https://www.googleapis.com/oauth2/v4/token")
				           .then().spec(responspec).extract().response().asString();
	    
	    JsonPath jsp = new JsonPath(response1);
	    String accessToken = jsp.getString("access_token");
	    System.out.println("Access Token = "+accessToken);

// Actual request. To get course details from raghulshetty.com website //
	    RequestSpecification request2 = given().spec(reqspec).urlEncodingEnabled(false)
	    .queryParams("access_token", accessToken);
	    
	    String response2 = request2.when().post("https://rahulshettyacademy.com/getCourse.php")
	    .then().extract().response().asString();
	    
	    System.out.println(response2);    
		
	}
	
}
