package com.qa.data;

public class PayLoad {
	
	public static String addPlace() {
		String place = "{   \r\n" + 
				"	\"location\": { \r\n" + 
				"    \"lat\": -38.383494,     \r\n" + 
				"    \"lng\": 33.427362 \r\n" + 
				"  },\r\n" + 
				"  \r\n" + 
				"  \"accuracy\": 50, \r\n" + 
				"  \"name\": \"Sujith House1\",   \r\n" + 
				"  \"phone_number\": \"(+91) 7259310103\",   \r\n" + 
				"  \"address\": \"29, side layout, cohen 09\", \r\n" + 
				"  \"types\": [     \r\n" + 
				"  	\"prince park\", \r\n" + 
				"    \"shop\" \r\n" + 
				"  ],   \r\n" + 
				"  \"website\": \"http://google.com\", \r\n" + 
				"  \"language\": \"French-IN\" \r\n" + 
				"	\r\n" + 
				"} " ;
		return place;
	}
	
	// not used //
	public static String deletePlace() {
		String deletePlace = "{     \"place_id\":\"33cf6acdfbdefe944e28bb5d81c06b67\" }";
		return deletePlace;
	}
	
	// not used //	
	public static String updatePlace() {
		String updatePlace = "{ \"place_id\":\"33cf6acdfbdefe944e28bb5d81c06b67\", \r\n" + 
				"   \"address\":\"hunumanth nagar, srinagar, bangalore-50\", \r\n" + 
				"   \"key\":\"qaclick123\" \r\n" + 
				"	\r\n" + 
				"}";
		return updatePlace;
	}

}
