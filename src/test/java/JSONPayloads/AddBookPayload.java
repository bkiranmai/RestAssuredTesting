package JSONPayloads;

import org.apache.commons.lang3.RandomStringUtils;

public class AddBookPayload {
	
	public static String AddBookBody(String name, String isbn ,String aisle,String author ) {
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\""+name+"\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\""+author+"\"\r\n" + 
				"}\r\n" + 
				"";
		
	}
	
	public static String AddBookBody( String isbn ,String aisle ) {
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"BhagavadGita \",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"Lord SriKrishna\"\r\n" + 
				"}";
		
	}
	
	public static String GenerateRandomAlphabets() {
		
		String Generatedisbn= RandomStringUtils.randomAlphabetic(5);
		return Generatedisbn;
		
		}
	
   public static String GenerateRandomNumeric() {
		
		String GeneratedAisle= RandomStringUtils.randomNumeric(4);
		return GeneratedAisle;
		
		}

}
