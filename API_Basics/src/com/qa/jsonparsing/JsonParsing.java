package com.qa.jsonparsing;

import com.qa.data.ComplexJsonPayload;
import com.qa.reusablemethods.JsonParse;

import io.restassured.path.json.JsonPath;

public class JsonParsing {

      public static void main(String[] args) {
		
		JsonPath jsp = JsonParse.jsonPath(ComplexJsonPayload.CoursePrice());
		
		// print no of courses returned by API //
		int size = jsp.getInt("courses.size()");
        System.out.println("size of courses = " +size);
        System.out.println("----------------------------------");
        
        // Print purchase amount //
        int purchaseAmount = jsp.getInt("dashboard.purchaseAmount");  // parent node.child node //
        System.out.println("purchase Amount = "+purchaseAmount);
        System.out.println("----------------------------------");
        
        // Print title, price, copies of first course //
        String title = jsp.getString("courses[0].title");
        int price = jsp.getInt("courses[0].price");
        int copies = jsp.getInt("courses[0].copies");
        System.out.println("title = "+title);
        System.out.println("price = "+price);
        System.out.println("copies = "+copies);
        System.out.println("----------------------------------");
        
        // Print all courses titles and their respective prices //
        int size1 = jsp.getInt("courses.size()");
        for(int i=0; i<size1; i++) {
        	String courseTitle = jsp.getString("courses["+i+"].title");
        	int price1 = jsp.getInt("courses["+i+"].price");
        	System.out.println("Course Title = "+courseTitle+" , Course Price = "+price1);
        }
        System.out.println("----------------------------------");
        
        // Print no of copies sold by RPA Course //
        for(int j=0; j<size1; j++) {
        	String courseTitle = jsp.getString("courses["+j+"].title");
        	
            if(courseTitle.equalsIgnoreCase("RPA")) {
            	int price2 = jsp.getInt("courses["+j+"].copies");
            	System.out.println("Course Title = "+courseTitle+" , copies = "+price2);
            	break;
            }
	    }
        System.out.println("----------------------------------");
        
        // verify sum of all courses price(price x copies) matches with purchase amount //
        int sumOfAllCoursePrice = 0;
        for(int k=0; k<size1; k++) {
        	int price3 = jsp.getInt("courses["+k+"].price");
            int copies3 = jsp.getInt("courses["+k+"].copies");
            int everyCoursePrice = price3 * copies3;
            sumOfAllCoursePrice = sumOfAllCoursePrice + everyCoursePrice;
        }
        
        System.out.println("sum of all course price = "+sumOfAllCoursePrice);
        System.out.println("Purchase amount = "+purchaseAmount);
        
        if(sumOfAllCoursePrice == purchaseAmount) {
        	System.out.println("sum of all courses price(price x copies) matches with purchase amount");
        }
        else {
        	System.out.println("sum of all courses price(price x copies) not matches with purchase amount");
        }
        System.out.println("----------------------------------");
  }

}
