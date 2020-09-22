package pojoDeserialization;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class CoursesList {
	
	@Test
	public void OAuth2Authorization() {
		
		String AuthCode = "4%2F2AGd5ekxFBpoTd-Ih9pLGJ7mTI_BplAwq-AC3bIWZBqO3AzBaRM4X0rE4sqdVnv2UD5DxFwhjZSztgDxVHNAC18";
		
		//To get the accessToken
		String response = given().log().all().urlEncodingEnabled(false)
		.queryParam("code", AuthCode).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		
		JsonPath js = new JsonPath(response);
	    String accesstoken = js.getString("access_token");
	    System.out.println("the access token is :"+accesstoken);
	    
	   // Actual request to retrieve the course details
	    
	    PojoCoursesList pcl =  given().log().all().queryParam("access_token",accesstoken ).expect().defaultParser(Parser.JSON)
	    .when().get("https://rahulshettyacademy.com/getCourse.php").as(PojoCoursesList.class);
	    
	    System.out.println(pcl.getInstructor());
	    System.out.println(pcl.getCourses());
	    System.out.println(pcl.getLinkedIn());
	    
	   
	    }

}
