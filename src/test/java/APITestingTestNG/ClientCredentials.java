package APITestingTestNG;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ClientCredentials {
	
	@Test
	public void clientCredentialsAuth() {
		
		String response = given().log().all().urlEncodingEnabled(false)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("grant_type", "client_credentials")
		.queryParam("scope", "https://www.googleapis.com/auth/userinfo.email")
		.when().post("https://www.googleapis.com/oauth2/v4/token")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
	    String accesstoken = js.getString("access_token");
	    System.out.println("the access token is :"+accesstoken);
		
	}

}
