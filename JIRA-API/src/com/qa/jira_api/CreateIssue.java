package com.qa.jira_api;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//--------------------login to jira application(session id is created) and create issue in jira application------------------------//
public class CreateIssue {

// Login, then create session object //

	@Test
	public void createSessionId() {
		
//-------------------------- Below lines are common for all test scripts -------------------------------//
		RestAssured.baseURI = "http://localhost:8080/";
		
    // A session filter can be used record the session id returned from the server as well as automatically apply this session id in subsequent requests.//
		SessionFilter session = new SessionFilter();
		
		RequestSpecification reqs = new RequestSpecBuilder().setContentType(ContentType.JSON).addFilter(session).build();
		
		ResponseSpecification resp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
//-----------------------------------------------------------------------------------------------------//
		
		File file1 = new File("jsonPayloads/createSessionId.json");
		
		RequestSpecification reqs1  =  given().spec(reqs).body(file1);  // passing json file as data in body() method //
		                      
		String response1 = reqs1.when().post("rest/auth/1/session")
		.then().spec(resp).assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsp1 = new JsonPath(response1);
		String sessionId = jsp1.getString("session.value");
		
		System.out.println("-------------------------------");
		System.out.println("Session Id is :- "+sessionId);
		System.out.println("-------------------------------");
System.out.println("***********************************************************************************************");

//create new issue using session id //
        RequestSpecification reqs2 = given().spec(reqs).body(new File("jsonPayloads/createIssue.json"));

        String response2 = reqs2.when().post("rest/api/2/issue")
        .then().spec(resp).assertThat().statusCode(201).extract().response().asString();
        
		System.out.println("-------------------------------");
		System.out.println(response2);
		JsonPath jsp2 = new JsonPath(response2);
		String issueId = jsp2.getString("id");
		System.out.println("Issue id is :- "+issueId);
		System.out.println("-------------------------------");
System.out.println("***********************************************************************************************");

// Adds comments to an existing issue //
        RequestSpecification reqs3 = given().spec(reqs).body(new File("jsonPayloads/addcomment.json")).pathParam("key",issueId); // issue id through path parameter//

        String response3 =	reqs3.when().post("rest/api/2/issue/{key}/comment")
                            .then().spec(resp).assertThat().statusCode(201).extract().response().asString();
        System.out.println("-------------------------------");
		System.out.println(response3);
		JsonPath jsp3 = new JsonPath(response3);
		String commentId = jsp3.getString("id");
		System.out.println("Comment id is :- "+commentId);
		System.out.println("-------------------------------");
System.out.println("***********************************************************************************************");


// adds attachment to an existing issue using multipart(), header("Content-Type","multipart/form-data") //
        RequestSpecification reqs4 = given().filter(session).header("X-Atlassian-Token","no-check").pathParam("key", issueId).header("Content-Type","multipart/form-data").
        		multiPart("file",new File("jira-bug-attachments/exceptions.docx"));
        String response4 = reqs4.when().post("rest/api/2/issue/{key}/attachments")
                           .then().spec(resp).assertThat().statusCode(200).extract().response().asString();
        System.out.println("-------------------------------");
		System.out.println(response4);
		System.out.println("-------------------------------");
System.out.println("***********************************************************************************************");

	}



}
