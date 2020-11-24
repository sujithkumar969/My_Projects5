package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	AddPlace payload;
	
	public AddPlace addPlacePayload() {
		
		payload = new AddPlace();
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
		
		return payload;
		
	}

}
