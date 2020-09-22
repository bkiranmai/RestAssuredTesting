package APITestingTestNG;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.mk_latn.No;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuth2Automation {
	
	@Test
	public void OAuth2Authorization() {
		
		String AuthCode = "4%2F1gEqFYyz2nqMZi9_zQeK-R1nmh1P7dfMXSTXKOxcNLWfc9X1BodRi6XYhut_QhVlyPZWIbKyBUphENgk7uv0yDU";
		
		//To get the accessToken
		String response = given().log().all().urlEncodingEnabled(false)
				.queryParam("code", AuthCode).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		
		JsonPath js = new JsonPath(response);
	    String accesstoken = js.getString("access_token");
	    System.out.println("the access token is :"+accesstoken);
	    
	   // Actual request to retrieve the course details
	    
	     given().log().all().queryParam("access_token",accesstoken )
	    .when().get("https://rahulshettyacademy.com/getCourse.php")
	    .then().log().all().statusCode(200);
	   
	    }

}
