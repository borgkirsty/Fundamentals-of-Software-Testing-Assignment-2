package javaPackage.alertLimit;

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

public class AlertOperator {

    int alertCount = 0;
    int alertsReturned = 0;
    boolean returnLimitExceeded = false;
    final static CloseableHttpClient httpClient = HttpClients.createDefault();

    static JsonObject dummyProduct1 = Json.createObjectBuilder()
            .add("alertType", 1)
            .add("heading", "product1")
            .add("description", "product1 description")
            .add("url", "https://www.product1.com")
            .add("imageUrl", "https://www.product1.com/image.jpg")
            .add("postedBy", "1f32da0b-e868-41d7-8a20-aa9cda53c09e")
            .add("priceInCents", "1000")
            .build();

    public void counting() throws Exception {
        String postAlertUrl = "https://api.marketalertum.com/Alert";

        HttpPost request = new HttpPost(postAlertUrl);

        //Product 1
        StringEntity entityPost1 = new StringEntity(dummyProduct1.toString());
        request.setEntity(entityPost1);
        request.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response1 = httpClient.execute(request);
        //System.out.println("Post Alert 1 Status Code: " + response1.getCode());
        response1.close();

        HttpGet eventsLogRequest = new HttpGet("https://api.marketalertum.com/EventsLog/1f32da0b-e868-41d7-8a20-aa9cda53c09e");
        CloseableHttpResponse eventsLogResponse = httpClient.execute(eventsLogRequest);

        String responseBody = EntityUtils.toString(eventsLogResponse.getEntity());
        //System.out.println(responseBody);

        if (responseBody.length() > 2) {
            int index = responseBody.indexOf("eventLogType");
            if ((responseBody.charAt(index + 14)) == '0') {
                System.out.println("Successfully Posted Alert!");
                alertCount++;
            } else {
                System.out.println("Something went wrong when posting alert! :/");
            }
        } else {
            System.out.println("Something went wrong when posting alert! :/");
        }

    }

    public void deleted() throws Exception{
        HttpDelete request = new HttpDelete("https://api.marketalertum.com/Alert?userId=1f32da0b-e868-41d7-8a20-aa9cda53c09e");
        request.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = httpClient.execute(request);

        //System.out.println("Delete Alert Status Code: " + response.getCode());

        HttpGet eventsLogRequest = new HttpGet("https://api.marketalertum.com/EventsLog/1f32da0b-e868-41d7-8a20-aa9cda53c09e");
        CloseableHttpResponse eventsLogResponse = httpClient.execute(eventsLogRequest);

        String responseBody = EntityUtils.toString(eventsLogResponse.getEntity());
        //System.out.println(responseBody);

        if(responseBody.length() > 2){
            int index = responseBody.indexOf("eventLogType");
            if((responseBody.charAt(index+14)) == '1'){
                System.out.println("Successfully Deleted Alerts!");
                alertCount = 0;
            }else{
                System.out.println("Something went wrong when deleting alerts! :/");
            }
        }else{
            System.out.println("Something went wrong when deleting alerts! :/");
        }
    }

    public void returned() throws Exception{
        HttpGet request = new HttpGet("https://www.marketalertum.com/Alerts/List");
        CloseableHttpResponse response = httpClient.execute(request);

        //System.out.println(response.toString());

        String html = EntityUtils.toString(response.getEntity());
        //System.out.println(html);

        String headerTable = "<table border=\"1\" width=\"80%\">";

        alertsReturned = 0;
        int index = 0;

        while (index != -1) {
            index = html.indexOf(headerTable, index);
            if (index != -1) {
                alertsReturned++;
                index += headerTable.length();
            }
        }

        if(alertsReturned <= 5){
            System.out.println("Number of Alerts within limit! [" +alertsReturned+ "]");
        }else{
            System.out.println("Number of Alerts out of limit! :/");
        }
    }


    public int getAlertCount() {
        return alertCount;
    }

    public int getAlertsReturned() {
        return alertsReturned;
    }


}
