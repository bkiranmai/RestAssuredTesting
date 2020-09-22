package APITestingTestNG;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import JSONPayloads.AddPlacePayload;

public class GoogleAPI {
	
	

	@Test
	public void StatusCodeValidation() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(AddPlacePayload.addPlaceBody())
		.when().post("/maps/api/place/add/json ")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
		System.out.println(response);
		
		JsonPath Js = new JsonPath(response);
		String placeid = Js.getString("place_id");
		System.out.println(placeid);
		
		
	//Update placeAPI

		String UpdatePlaceResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n"+ 
				"\"address\":\"70 Summer walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
		System.out.println("Updated address is : "+UpdatePlaceResponse);
		
		//Get PlaceAPI
		
		given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo("70 Summer walk, USA"));
		
	}

}
