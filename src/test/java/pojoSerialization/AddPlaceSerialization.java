package pojoSerialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class AddPlaceSerialization {
	
	@Test
	public void AddPlace() {
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	PojoAddPlace pap = new PojoAddPlace();
	
	pap.setAccuracy(50);
	pap.setAddress("29, side layout, cohen 09");
	pap.setLanguage("French-IN");
	pap.setName("Frontline house");
	pap.setPhone_number("(+91) 983 893 3937");
	pap.setWebsite("http://google.com");
	
	location L = new location();
	pap.setLocation(L);
	
	List<String> ty = new ArrayList<>();
	ty.add("shoe park");
	ty.add("shop");
	pap.setTypes(ty);
	
	
	given().log().all().queryParam("key", "qaclick123")
	.body(pap)
	.when().post("/maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();

}
}