package com.example.yzha502.movienight.webCom;

import com.example.yzha502.movienight.entities.Movies;
import com.example.yzha502.movienight.entities.Reviews;
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
import java.util.ArrayList;

/**
 * Created by yzha502 on 5/06/15.
 */
public class MovieWeb {

    private static final String url = "http://118.139.27.157:8080/MovieNight/webresources/entities.movies/";

    public static String getReviews( String id) {

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

    public static String getLikedUser( String id){
        String strResult="";
        String URL = url+"getLikedUser/"+id;
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

    public static int getNumLikedUser(String id){
        String URL = url+"getNumLikedUser/"+id;
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

            return Integer.getInteger(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMoviesByName( String name){
        String URL = url+"getMoviesByName/"+name;
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



    public static String getMoviesByGren( String name){
        String URL = url+"getMoviesByGenre/"+name;
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

    public static String getMoviesByActor(String name){
        String URL = url+"getMoviesByActor/"+name;
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

    public static String getMoviesByDirector(String name){
        String URL = url+"getMoviesByDirector/"+name;
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


    public static String getHotMovie(){
        String URL = url+"getHotMovies";
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

    public static String getLatestMovie(){
        String URL = url+"getLatestMovies";
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

    public static void setReview(Reviews r){
        HttpClient client = new DefaultHttpClient();
        HttpPost httpRequest = new HttpPost("http://118.139.27.157:8080/MovieNight/webresources/entities.reviews/create");
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setHeader("Accept", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(r);
        try
        {
            StringEntity jsonEntity = new StringEntity(json);
            httpRequest.setEntity(jsonEntity);
            client.execute(httpRequest);
        }catch(Exception e)
        {}

    }

    public static String getNumOfReviews(){
        String URL = "http://118.139.27.157:8080/MovieNight/webresources/entities.reviews/count";
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


    public static ArrayList<Movies> parseJsonMovie(String s) {
         ArrayList<Movies> movieses = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(s);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                Movies movie = new Movies();
                JSONObject movieJson = jsonArray.getJSONObject(i);
                String rating = movieJson.getString("filmRating").toString();
                movie.setFilmRating(Double.parseDouble(rating));
                int times = movieJson.getInt("filmRatingTimes");
                movie.setFilmRatingTimes(times);
                String movieActor = movieJson.getString("movieActor").toString();
                movie.setMovieActor(movieActor);
                String movieDate = movieJson.getString("movieDate").toString();
                movieDate = movieDate.substring(0, 10);
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                try{
//                    Date date = formatter.parse(movieDate);
                movie.setMovieDate(movieDate);
//                }catch(Exception e){
//                    e.getMessage();
//                }
                String movieDesc = movieJson.getString("movieDesc").toString();
                movie.setMovieDesc(movieDesc);
                String movieDirector = movieJson.getString("movieDirector").toString();
                movie.setMovieDirector(movieDirector);
                String movieGenre = movieJson.getString("movieGenre").toString();
                movie.setMovieGenre(movieGenre);
                String movieId = movieJson.getString("movieId").toString();
                movie.setMovieId(movieId);
                String movieImage = movieJson.getString("movieImage").toString();
                movie.setMovieImage(movieImage);
                String movieName = movieJson.getString("movieName").toString();
                movie.setMovieName(movieName);
                movieses.add(movie);
                // return movieses;
            }
            return movieses;
        } catch (JSONException e) {
        }
         return null;
    }

    public static void editMovie(Movies m) {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpRequest = new HttpPost(url + "edit/"+m.getMovieId());
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setHeader("Accept", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(m);
        try {
            StringEntity jsonEntity = new StringEntity(json);
            httpRequest.setEntity(jsonEntity);
            client.execute(httpRequest);
        } catch (Exception e) {
        }
    }
}
