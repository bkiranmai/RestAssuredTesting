package APITestingTestNG;

import org.testng.annotations.Test;

import JSONPayloads.MockAPIPayload;
import io.restassured.path.json.JsonPath;

public class CoursePriceValidations {
	
	@Test
	public void NoOfCourses() {
		
		//By using Mock API
		
		JsonPath js = new JsonPath(MockAPIPayload.CoursePriceMockPayload());
		
		//Print no. of courses returned by Api
		
		int count = js.getInt("courses.size()");
		System.out.println("No. of courses returned by API : "+count);
		
		//Print purchase amount
		
		int purchaseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount is :"+purchaseamount);
		
		//Print title of first course
		
		String firstTitle = js.get("courses[0].title");
		System.out.println("The first course Title is :"+firstTitle);
		
		//Print all the course titles, prices and copies
		
		for(int i=0; i<count; i++) {
			String title = js.get("courses["+i+"].title");
			System.out.println(title);
			
			//int price = js.getInt("courses["+i+"].price");
			System.out.println(js.getInt("courses["+i+"].price"));
			
			int copies = js.getInt("courses["+i+"].copies");
			System.out.println(copies);
		}
		
		//Print no. of copies sold by RPA course
		
		for(int k=0; k<count; k++) {
			String CourseTitle = js.get("courses["+k+"].title");
			
			if(CourseTitle.equalsIgnoreCase("Cypress")) {
				int NoOfCopies = js.getInt("courses["+k+"].copies");
				System.out.println("No. of copies for cypress is :"+NoOfCopies);
				break;
			}
			
			
		}
		
		
		
		//Verify if sum of all course price matches purchase amount
		
		int totalAmount =0;
		
		for(int j=0;j<count;j++) {
		
			int Price = js.getInt("courses["+j+"].price");
			int Copies = js.getInt("courses["+j+"].copies");
			
			int amount = Price * Copies;
			totalAmount =totalAmount+amount;
			}
		
		System.out.println("The total amount of all copies is :" +totalAmount);
		
		int PurchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		if(PurchaseAmount==totalAmount)
		{
	    System.out.println("The purchase amount is equal to sum of all course prices ");
		}
	    else
	    	System.out.println("The purchase amount is not equal to sum of all course prices ");
	    	
		
		}
		
	}


