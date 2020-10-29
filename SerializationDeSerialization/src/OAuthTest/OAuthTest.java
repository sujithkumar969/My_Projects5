package OAuthTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo_classes.MainCourses;
import pojo_classes.WebAutomation;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuthTest {
	
@Test
public void testAuthorisationCode() {
	
	String[] expectedCourseList = {"Selenium Webdriver Java","Cypress","Protractor"};
	
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
	
      String code = "4%2F0AfDhmri4D5Rs9DZBl5w5pZmwBY3VuRhLYBnA2bjpGzJQM2YouCLvY0bd5whddXQwOewmkw";
	
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

//Actual request. To get course details from raghulshetty.com website //
	    RequestSpecification request2 = given().spec(reqspec).urlEncodingEnabled(false)
	                                   .queryParams("access_token", accessToken);
	    
	    // setDefaultParser(Parser.JSON); converts the text format response to json format //
	    MainCourses response2 = request2.expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").as(MainCourses.class); 
	    
	    System.out.println("----------------------------------------");
	    System.out.println(response2.getInstructor());
	    System.out.println(response2.getUrl());
	    System.out.println(response2.getServices());
	    System.out.println(response2.getExpertise());
	    System.out.println("----------------------------------------");
	    // get courses of WebAutomation //
	    List<WebAutomation> webAutomationCourses = response2.getCourses().getWebAutomation();
	    for(int k=0; k<webAutomationCourses.size(); k++) {
	    System.out.println(webAutomationCourses.get(k).getCourseTitle());
	    System.out.println(webAutomationCourses.get(k).getPrice());
	    }
	    System.out.println("----------------------------------------");
	    System.out.println(response2.getCourses().getApi().get(1).getCourseTitle());
	    System.out.println(response2.getCourses().getApi().get(1).getPrice());
	    System.out.println("----------------------------------------");
	    // get courses and price of mobile //
	    for(int i=0; i<response2.getCourses().getMobile().size(); i++) {
	    	
	    	System.out.println(response2.getCourses().getMobile().get(i).getCourseTitle());
	    	System.out.println(response2.getCourses().getMobile().get(i).getPrice());
	    }
	    System.out.println("----------------------------------------");
	    // scan for course title = cypress, price = 40 in webAutomation //
	    for(int j=0; j<response2.getCourses().getWebAutomation().size(); j++) {
	    	
	    	String courseTitle = response2.getCourses().getWebAutomation().get(j).getCourseTitle();
	    	if(courseTitle.equalsIgnoreCase("cypress")) {
	    		System.out.println(response2.getCourses().getWebAutomation().get(j).getCourseTitle());
	    		System.out.println(response2.getCourses().getWebAutomation().get(j).getPrice());
	    		break;
	    	}	
	    }
	    System.out.println("----------------------------------------");
	    // compare webAutomation courses list with expected course list //
	    ArrayList<String> actual = new ArrayList<String>();
	    for(int m=0; m<webAutomationCourses.size(); m++) {
	    	
	    	actual.add(webAutomationCourses.get(m).getCourseTitle());
	    	
	    }
	    assertEquals(actual, Arrays.asList(expectedCourseList));
	    System.out.println(actual);
	    System.out.println("----------------------Done------------------");
	}
	

}
