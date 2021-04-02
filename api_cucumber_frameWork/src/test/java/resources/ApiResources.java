package resources;

// enum is a special class in java, which is a collection of constants or methods //
public enum ApiResources {
	
	addPlaceAPI("maps/api/place/add/json"),
	deletePlaceAPI("maps/api/place/delete/json"),
	getPlaceAPI("maps/api/place/get/json");
	
	String resource;
	
	/*
	 * valueOf(resource) method will call the below constructor.
	 * constructor will pass the method value(resource) to global variable.
	 * finally we will call getter method to get method value(resource).
	 */
	ApiResources(String resource){  // constructor initializes the variable with method value //
		
		this.resource = resource;
	}
	
	public String getResourceURL() {
		return resource;
	}
	

}
