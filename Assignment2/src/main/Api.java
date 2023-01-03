package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;






import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;


public class Api {
	
	//Create HTTP Client
	final static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	//Dummy Data 
	static JsonObject dummyProduct1 = Json.createObjectBuilder()
			.add("alertType", 1)
			.add("heading", "product1")
			.add("description", "product1 description")
			.add("url", "https://www.product1.com")
			.add("imageUrl", "https://www.product1.com/image.jpg")
			.add("postedBy", "1f32da0b-e868-41d7-8a20-aa9cda53c09e")
			.add("priceInCents", "1000")
			.build();
	
	static JsonObject dummyProduct2 = Json.createObjectBuilder()
			.add("alertType", 2)
			.add("heading", "product2")
			.add("description", "product2 description")
			.add("url", "https://www.product2.com")
			.add("imageUrl", "https://www.product2.com/image.jpg")
			.add("postedBy", "1f32da0b-e868-41d7-8a20-aa9cda53c09e")
			.add("priceInCents", "2000")
			.build();
	
	static JsonObject dummyProduct3 = Json.createObjectBuilder()
			.add("alertType", 3)
			.add("heading", "product3")
			.add("description", "product3 description")
			.add("url", "https://www.product3.com")
			.add("imageUrl", "https://www.product3.com/image.jpg")
			.add("postedBy", "1f32da0b-e868-41d7-8a20-aa9cda53c09e")
			.add("priceInCents", "3000")
			.build();
	
	static JsonObject dummyProduct4 = Json.createObjectBuilder()
			.add("alertType", 4)
			.add("heading", "product4")
			.add("description", "product4 description")
			.add("url", "https://www.product4.com")
			.add("imageUrl", "https://www.product4.com/image.jpg")
			.add("postedBy", "1f32da0b-e868-41d7-8a20-aa9cda53c09e")
			.add("priceInCents", "4000")
			.build();
	
	static JsonObject dummyProduct5 = Json.createObjectBuilder()
			.add("alertType", 5)
			.add("heading", "product5")
			.add("description", "product5 description")
			.add("url", "https://www.product5.com")
			.add("imageUrl", "https://www.product5.com/image.jpg")
			.add("postedBy", "1f32da0b-e868-41d7-8a20-aa9cda53c09e")
			.add("priceInCents", "5000")
			.build();
	
	
	static JsonObject dummyProduct6 = Json.createObjectBuilder()
			.add("alertType", 6)
			.add("heading", "product6")
			.add("description", "product6 description")
			.add("url", "https://www.product6.com")
			.add("imageUrl", "https://www.product6.com/image.jpg")
			.add("postedBy", "1f32da0b-e868-41d7-8a20-aa9cda53c09e")
			.add("priceInCents", "6000")
			.build();
	
	
		/*
		counting() = {*.counting()}
		returned() = {*.returned()}
		numReturnedAlerts() = {*.numReturnedAlerts()}
		deleted() = {*.deleted()}
		*/
		
	public static void invalidLogin() throws Exception{
		//Create HttpPost with the loginURL as the target
		String loginUrl = "https://www.marketalertum.com/Alerts/LoginForm";
		HttpPost httpPost = new HttpPost(loginUrl);
		
		//Set the data as the entity of the HttpPost request
		List<NameValuePair> formData = new ArrayList<>();
		formData.add(new BasicNameValuePair("UserId", "invalidID"));
		httpPost.setEntity(new UrlEncodedFormEntity(formData));
			
		//Execute the HttpPost request and store the response in an HttpResponse object
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		
		httpResponse.close();
		
		/* Check EventsLog - None Expected */
		HttpGet eventsLogRequest = new HttpGet("https://api.marketalertum.com/EventsLog/1f32da0b-e868-41d7-8a20-aa9cda53c09e");
		CloseableHttpResponse eventsLogResponse = httpClient.execute(eventsLogRequest);
		
		String responseBody = EntityUtils.toString(eventsLogResponse.getEntity());
		//System.out.println(responseBody);
		if(responseBody.length() > 2){
			System.out.println("Something went wrong when trying to use an invalid login.");
			//If system is already logged in this will fail
		}else{
			System.out.println("Invalid Login Successfull! ");
		}
		
		//HttpEntity entity = httpResponse.getEntity(); 
		//System.out.println(EntityUtils.toString(entity));		
	}
	
	public static void validLogin() throws Exception{
		//Create HttpPost with the loginURL as the target
		String loginUrl = "https://www.marketalertum.com/Alerts/LoginForm";
		HttpPost httpPost = new HttpPost(loginUrl);
		
		//Set the data as the entity of the HttpPost request
		List<NameValuePair> formData = new ArrayList<>();
		formData.add(new BasicNameValuePair("UserId", "1f32da0b-e868-41d7-8a20-aa9cda53c09e"));
		httpPost.setEntity(new UrlEncodedFormEntity(formData));
			
		//Execute the HttpPost request and store the response in an HttpResponse object
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		
		httpResponse.close();
		
		/* Check EventsLog - 5 Expected */
		HttpGet eventsLogRequest = new HttpGet("https://api.marketalertum.com/EventsLog/1f32da0b-e868-41d7-8a20-aa9cda53c09e");
		CloseableHttpResponse eventsLogResponse = httpClient.execute(eventsLogRequest);
		
		String responseBody = EntityUtils.toString(eventsLogResponse.getEntity());
		//System.out.println(responseBody);
		if(responseBody.length() > 2){
			int firstIndex = responseBody.indexOf("eventLogType");
			if((responseBody.charAt(firstIndex+14)) == '5'){
				int secondIndex = responseBody.indexOf("eventLogType",firstIndex + 1);
				if((responseBody.charAt(secondIndex+14)) == '7'){
					System.out.println("Successfully Logged In and Viewed Alerts!");
				}else{
					System.out.println("Something went wrong when logging in! :/ ");
				}
			}else{
				System.out.println("Something went wrong when logging in! :/ ");
			}
		}else{
			System.out.println("Something went wrong when logging in! :/ ");
		}
		//HttpEntity entity = httpResponse.getEntity(); 
		//System.out.println(EntityUtils.toString(entity));	
	}
	
	public static void Logout() throws Exception{
		//Create HttpPost with the loginURL as the target
		String logoutUrl = "https://www.marketalertum.com/Alerts/Logout";
		HttpPost httpPost = new HttpPost(logoutUrl);
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);	
		
		httpResponse.close();
		
		/* Check EventsLog - 6 Expected */
		HttpGet eventsLogRequest = new HttpGet("https://api.marketalertum.com/EventsLog/1f32da0b-e868-41d7-8a20-aa9cda53c09e");
		CloseableHttpResponse eventsLogResponse = httpClient.execute(eventsLogRequest);
		
		String responseBody = EntityUtils.toString(eventsLogResponse.getEntity());
		//System.out.println(responseBody);
		
		if(responseBody.length() > 2){
			int index = responseBody.indexOf("eventLogType");
			if((responseBody.charAt(index+14)) == '6'){
				System.out.println("Successfully Logged Out!");
			}else{
				System.out.println("Something went wrong when logging out! :/");
			}
		}else{
			System.out.println("Something went wrong when logging out! :/");
		}
		

		

	}
	
	public static void postAlerts() throws Exception{
		String postAlertUrl = "https://api.marketalertum.com/Alert";
		
		HttpPost request = new HttpPost(postAlertUrl);
		
		//Product 1
		StringEntity entityPost1 = new StringEntity(dummyProduct1.toString());
		request.setEntity(entityPost1);
		request.setHeader("Content-Type", "application/json");		
		CloseableHttpResponse response1 = httpClient.execute(request);
		System.out.println("Post Alert 1 Status Code: " + response1.getCode());
		response1.close();
		postAlertEventLogCheck(1);
		
		/*
		//Product 2
		StringEntity entityPost2 = new StringEntity(dummyProduct2.toString());
		request.setEntity(entityPost2);
		request.setHeader("Content-Type", "application/json");		
		CloseableHttpResponse response2 = httpClient.execute(request);		
		System.out.println("Post Alert 2 Status Code: " + response2.getCode());
		response2.close();
		postAlertEventLogCheck(2);		
		
		//Product 3
		StringEntity entityPost3 = new StringEntity(dummyProduct3.toString());
		request.setEntity(entityPost3);
		request.setHeader("Content-Type", "application/json");		
		CloseableHttpResponse response3 = httpClient.execute(request);		
		System.out.println("Post Alert 3 Status Code: " + response3.getCode());
		response3.close();
		postAlertEventLogCheck(3);
		
		//Product 4
		StringEntity entityPost4 = new StringEntity(dummyProduct4.toString());
		request.setEntity(entityPost4);
		request.setHeader("Content-Type", "application/json");		
		CloseableHttpResponse response4 = httpClient.execute(request);		
		System.out.println("Post Alert 4 Status Code: " + response4.getCode());
		response4.close();
		postAlertEventLogCheck(4);
		
		//Product 5
		StringEntity entityPost5 = new StringEntity(dummyProduct5.toString());
		request.setEntity(entityPost5);
		request.setHeader("Content-Type", "application/json");		
		CloseableHttpResponse response5 = httpClient.execute(request);		
		System.out.println("Post Alert 5 Status Code: " + response5.getCode());
		response5.close();
		postAlertEventLogCheck(5);
		
		//Product 6
		StringEntity entityPost6 = new StringEntity(dummyProduct6.toString());
		request.setEntity(entityPost6);
		request.setHeader("Content-Type", "application/json");		
		CloseableHttpResponse response6 = httpClient.execute(request);		
		System.out.println("Post Alert 6 Status Code: " + response6.getCode());
		response6.close();
		postAlertEventLogCheck(6);
		*/
		
		/* Check EventsLog - 0 Expected */
	}
	
	public static void postAlertEventLogCheck(int i) throws Exception{
		HttpGet eventsLogRequest = new HttpGet("https://api.marketalertum.com/EventsLog/1f32da0b-e868-41d7-8a20-aa9cda53c09e");
		CloseableHttpResponse eventsLogResponse = httpClient.execute(eventsLogRequest);
		
		String responseBody = EntityUtils.toString(eventsLogResponse.getEntity());
		//System.out.println(responseBody);
		
		if(responseBody.length() > 2){
			int index = responseBody.indexOf("eventLogType");
			if((responseBody.charAt(index+14)) == '0'){
				System.out.println("Successfully Posted Alert "+ i +"!");
			}else{
				System.out.println("Something went posting alert "+ i +"! :/");
			}
		}else{
			System.out.println("Something went posting alert "+ i +"! :/");
		}
		
	}
	
	public static void deleteAlerts() throws Exception{
		HttpDelete request = new HttpDelete("https://api.marketalertum.com/Alert?userId=1f32da0b-e868-41d7-8a20-aa9cda53c09e");
		request.setHeader("Content-Type", "application/json");

		CloseableHttpResponse response = httpClient.execute(request);
		
		System.out.println("Delete Alert Status Code: " + response.getCode());
		
		HttpGet eventsLogRequest = new HttpGet("https://api.marketalertum.com/EventsLog/1f32da0b-e868-41d7-8a20-aa9cda53c09e");
		CloseableHttpResponse eventsLogResponse = httpClient.execute(eventsLogRequest);
		
		String responseBody = EntityUtils.toString(eventsLogResponse.getEntity());
		//System.out.println(responseBody);
		
		if(responseBody.length() > 2){
			int index = responseBody.indexOf("eventLogType");
			if((responseBody.charAt(index+14)) == '1'){
				System.out.println("Successfully Deleted Alerts!");
			}else{
				System.out.println("Something went wrong when deleting alerts! :/");
			}
		}else{
			System.out.println("Something went wrong when deleting alerts! :/");
		}
		
	}
	
	public static void alertLimitCheck(int alertsCount) throws Exception {
		HttpGet request = new HttpGet("https://www.marketalertum.com/Alerts/List");
		CloseableHttpResponse response = httpClient.execute(request);

		//System.out.println(response.toString());
		
		String html = EntityUtils.toString(response.getEntity());
		//System.out.println(html);
        
		String headerTable = "<table border=\"1\" width=\"80%\">";

		alertsCount = 0; 
		int index = 0; 

		while (index != -1) {
		    index = html.indexOf(headerTable, index);
		    if (index != -1) {
		    	alertsCount++; 
		        index += headerTable.length(); 
		    }
		}
		
		if(alertsCount <= 5){
        	System.out.println("Number of Alerts within limit! [" +alertsCount+ "]");
        }else{
        	System.out.println("Number of Alerts out of limit! :/");
        }
      
	}
	
	public static void main(String[] args) throws Exception {
		
		invalidLogin();
		System.out.println();
		
		validLogin();
		System.out.println();
		
		Logout();		//DOES NOT WORK
		System.out.println();

		deleteAlerts();
		System.out.println();
		
		final Random rand = new Random();
		final int randomNumber = rand.nextInt(10);
		
		for(int i = 0; i < randomNumber; i++){
			postAlerts();
		} 		
		System.out.println();

		int count = 0;
		alertLimitCheck(count);
		System.out.println();
		
		
	}
	
	

}

