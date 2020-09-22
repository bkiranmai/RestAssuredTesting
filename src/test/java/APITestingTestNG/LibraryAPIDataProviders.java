package APITestingTestNG;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import JSONPayloads.AddBookPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPIDataProviders {
	
	@BeforeTest
	public void BaseURI() {
		RestAssured.baseURI="http://216.10.245.166";
	}
	
	@Test(dataProvider="AddBookData")
	public void ADDNewBook(String isbn,String aisle) {
		
		String response = given().log().all().header("Content-Type","application/json")
		.body(AddBookPayload.AddBookBody(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg",equalTo( "successfully added")).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		//Assert.assertEquals( "abcdefgigh11111122", id);
		System.out.println(id);
		
	}
	
	@DataProvider(name = "AddBookData")
	public Object[][] DataSet() {
		
		return new Object[][] {{"ghfsgkkjkj","54636764768"},
			                   {"khfgdfjkubn","08669765390454"},
			                   {"lbbgghhffhvj","0820497687342"}
			                                     };
	}
	
	@Test(dataProvider="DeleteData")
	public void DeleteBook(String data) {
		
		given().log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+data+"\"\r\n" + 
				" \r\n" + 
				"} \r\n" + 
				"")
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("book is successfully deleted"));
		}
	
	@DataProvider(name="DeleteData")
	public Object[][] DeleteDataSet() {
		
		return new Object[][] {{"ghfsgkkjkj54636764768"},
			            {"khfgdfjkubn08669765390454"},
			            {"lbbgghhffhvj0820497687342"}};
	}

}


