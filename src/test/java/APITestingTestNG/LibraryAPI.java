package APITestingTestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import JSONPayloads.AddBookPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAPI {
	
	@Test
	public void ADDNewBook() {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String isbn = AddBookPayload.GenerateRandomAlphabets();
		String aisle = AddBookPayload.GenerateRandomNumeric();
		
		String response = given().log().all().header("Content-Type","application/json")
		.body(AddBookPayload.AddBookBody(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg",equalTo( "successfully added")).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		//Assert.assertEquals( "abcdefgigh11111122", id);
		System.out.println(id);
		
	}

}
