package ReUsable;

import io.restassured.path.json.JsonPath;

public class JsonParse {

// JSON parser //
	public static JsonPath jsonPath(String payLoad) {
		
		JsonPath jsp = new JsonPath(payLoad);
		
		return jsp;
	}


}
