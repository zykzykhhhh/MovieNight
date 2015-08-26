package com.example.yzha502.movienight.webCom;

import android.util.Log;

import com.example.yzha502.movienight.entities.Message;
import com.example.yzha502.movienight.entities.Reviews;
import com.example.yzha502.movienight.entities.Users;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yzha502 on 5/06/15.
 */
public class UserWeb {

    private static final String url = "http://118.139.27.157:8080/MovieNight/webresources/entities.users/";
    private static final String urlMessage = "http://118.139.27.157:8080/MovieNight/webresources/apis.message/";
    public static String passwordVerification (String sid,String password)
    {
        String strResult="";
        String URL = url+"checkPasswd/"+sid;
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(URL);

        request.setHeader("Content-type", "text/plain");
        request.setHeader("Accept", "text/plain");

        try
        {
            StringEntity entity = new StringEntity(password);
            request.setEntity(entity);
            HttpResponse response =  client.execute(request);
            InputStream input = response.getEntity().getContent();
            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }

//            if (sb.toString().equals("true"))
//                return true;
//            else
//                return false;
            Log.i("result+++",sb.toString());
            return sb.toString();
        }catch(Exception e)
        {e.printStackTrace();
//         return false;
        }
        return null;
    }

    public static void insertUser(Users user){
        HttpClient client = new DefaultHttpClient();
        HttpPost httpRequest = new HttpPost(url+"create");
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setHeader("Accept", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        try
        {
            StringEntity jsonEntity = new StringEntity(json);
            httpRequest.setEntity(jsonEntity);
            client.execute(httpRequest);
        }catch(Exception e)
        {}
    }


    public static String setLikeMovies (String sid, String movId ){

        String URL = url+"setLikeMovies/"+sid;
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(URL);

        request.setHeader("Content-type", "text/plain");
        request.setHeader("Accept", "text/plain");

        try
        {
            StringEntity entity = new StringEntity(movId);
            request.setEntity(entity);
            HttpResponse response =  client.execute(request);
            InputStream input = response.getEntity().getContent();
            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
            Log.i("result+++", sb.toString());
            return sb.toString();
        }catch(Exception e)
        {e.printStackTrace();
//         return false;
        }
        return null;
    }


    public static String getReviews(String id) {

        String strResult="";
        String URL = url+"getReviews/"+id;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);

        try {

            HttpResponse response = client.execute(request);
            InputStream input = response.getEntity().getContent();
            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
            //System.out.println(sb.toString());

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUser(String id){
        String URL = url+id;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);

        try {
            HttpResponse response = client.execute(request);
            InputStream input = response.getEntity().getContent();
            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
            //System.out.println(sb.toString());

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getLikeMovies(String id) {

        String strResult="";
        String URL = url+"getLikeMovies/"+id;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);

        try {

            HttpResponse response = client.execute(request);
            InputStream input = response.getEntity().getContent();
            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
            //System.out.println(sb.toString());

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void setMessage(Message m) {

        HttpClient client = new DefaultHttpClient();
        HttpPost httpRequest = new HttpPost(urlMessage+"create");
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setHeader("Accept", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(m);
        try
        {
            StringEntity jsonEntity = new StringEntity(json);
            httpRequest.setEntity(jsonEntity);
            client.execute(httpRequest);
        }catch(Exception e)
        {}
    }

    public static String findSender(String id) {

        String strResult="";
        String URL = urlMessage+"findSender/"+id;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);

        try {

            HttpResponse response = client.execute(request);
            InputStream input = response.getEntity().getContent();
            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
            //System.out.println(sb.toString());

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String findReciver(String id) {

        String strResult="";
        String URL = urlMessage+"findReciver/"+id;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);

        try {

            HttpResponse response = client.execute(request);
            InputStream input = response.getEntity().getContent();
            String result = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            while ((result = reader.readLine()) != null) {
                sb.append(result);
            }
            //System.out.println(sb.toString());

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Users parseUser(String u){
        try {
            JSONObject jsonObject = new JSONObject(u);
            String passwd = jsonObject.getString("passwd").toString();
            String userCity = jsonObject.getString("userCity").toString();

            String userEmail = jsonObject.getString("userEmail").toString();
            int userGender = jsonObject.getInt("userGender");
            String userName = jsonObject.getString("userName").toString();
            String deviceId="";
            double userLatitude=0;
            double userLongitude=0;
            if(u.contains("deviceId")&&u.contains("userLatitude")&&u.contains("userLatitude")) {
                deviceId = jsonObject.getString("deviceId").toString();
                userLatitude = jsonObject.getDouble("userLatitude");
                userLongitude = jsonObject.getDouble("userLongitude");
            }
            Users user = new Users();
            user.setPassword(passwd);
            user.setUserName(userName);
            user.setUserGender(userGender);
            user.setUserCity(userCity);
            user.setUserEmail(userEmail);
            user.setDeviceId(deviceId);
            user.setUserLatitude(userLatitude);
            user.setUserLongitude(userLongitude);
            return user;
        }catch(JSONException e){}
        return null;
    }


    public static ArrayList<Reviews> parseReview(String r)
    {   ArrayList<Reviews> reviewList = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(r);
            int length = jsonArray.length();
            for(int i=0;i<length;i++)
            {
                String reviewLocation="";
                String reviewDate="";
                JSONObject jo = jsonArray.getJSONObject(i);
                String reviewComment=jo.getString("reviewComments");
                if(r.contains("reviewDate")){
                    reviewDate = jo.getString("reviewDate");
                }
                if(r.contains("reviewLocation")) {
                    reviewLocation = jo.getString("reviewLocation");
                }
                double reviewScores=jo.getDouble("reviewScores");
                JSONObject user = jo.getJSONObject("userId");
                String userName=user.getString("userName");
                String userEmail=user.getString("userEmail");
                Users u = new Users();
                Reviews reviews = new Reviews();
                u.setUserEmail(userEmail);
                u.setUserName(userName);
                reviews.setReviewComments(reviewComment);
                reviews.setReviewLocation(reviewLocation);
                reviews.setReviewScores(reviewScores);


                reviewDate= reviewDate.substring(0,10);

                reviews.setReviewDate(reviewDate);
                reviews.setUserId(u);
                reviewList.add(reviews);

            }
        }catch(Exception e){}
        return reviewList;
    }


    public static ArrayList<Message> parseMessage(String r){
        ArrayList<Message> reviewList = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(r);
            int length = jsonArray.length();
            for(int i=0;i<length;i++)
            {
                String reviewLocation="";
                String reviewDate="";
                JSONObject jo = jsonArray.getJSONObject(i);
                String Comment=jo.getString("conten");
                String resiverId=jo.getString("resiverId");
                String senderId=jo.getString("senderId");

                Message m = new Message();
               m.setConten(Comment);
                m.setResiverId(resiverId);
                m.setSenderId(senderId);
                reviewList.add(m);

            }
        }catch(Exception e){}
        return reviewList;
    }





}
