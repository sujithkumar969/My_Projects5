package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@deletePlaceAPI")
	public void precondition() throws IOException {	
		
		
		if(StepDefinitions.placeId == null) {
			
		StepDefinitions sd = new StepDefinitions();
		sd.place_payload("100", "daniel", "(+91) 9876543287", "120, machiel layout, cohen 23");
		sd.user_calls_with_http_request("addPlaceAPI", "post");
		sd.verify_that_maps_to_by_calling_with_http_request("daniel", "getPlaceAPI", "get");
		}
	}


}
