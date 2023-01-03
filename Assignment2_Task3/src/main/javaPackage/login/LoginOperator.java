package javaPackage.login;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class LoginOperator {
    private boolean loggedIn = false;
    //Create HTTP Client
    final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public void validLogin() throws Exception {
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
                    loggedIn = true;
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

    public void invalidLogin() throws Exception {
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
            loggedIn = false;
        }
    }

    public void logout() throws Exception {
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
                loggedIn = false;
            }else{
                System.out.println("Something went wrong when logging out! :/");
            }
        }else{
            System.out.println("Something went wrong when logging out! :/");
        }

    }

    public boolean isLoggedIn(){
        return loggedIn;
    }


}