package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	AddPlace payload;
	
	public AddPlace addPlacePayload(String accuracy, String name, String phone_no, String address) {
		
		payload = new AddPlace();
		payload.setAccuracy(Integer.valueOf(accuracy));
		payload.setName(name);
		payload.setPhone_number(phone_no);
		payload.setAddress(address);
		
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
	
	public String deletePlacePayload(String placeId) {
		
		return "{     \r\n    \"place_id\":\""+placeId+"\" \r\n}";

    }
}
