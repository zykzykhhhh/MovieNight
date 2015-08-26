package com.example.yzha502.movienight;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzha502.movienight.entities.Movies;
import com.example.yzha502.movienight.entities.Reviews;
import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.GPSTracker;
import com.example.yzha502.movienight.webCom.MovieWeb;
import com.example.yzha502.movienight.webCom.geocoding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class AddReviewActivity extends Activity {

    private TextView movieName;
    private EditText comment;
    private RatingBar movieMark;
    private Button addReview;
    private Users user;
    private Movies event;
    private double rate;
    private GPSTracker gps;
    private Geocoder geocoder;
    private List<Address> addresses;
    private Reviews reviews;
    private String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

//initialize
        user=new Users();
        event=new Movies();
        rate=0;
        Intent i = getIntent();
//        geocoder = new Geocoder(this, Locale.getDefault());
        event = (Movies)i.getParcelableExtra("event");
        user = (Users)i.getParcelableExtra("user");
        reviews = new Reviews();
        movieName=(TextView)findViewById(R.id.textView);
        comment=(EditText)findViewById(R.id.comment);
        movieMark=(RatingBar)findViewById(R.id.ratingBar2);
        addReview=(Button)findViewById(R.id.addARview);

        movieName.setText(event.getMovieName());
        movieMark.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate=rating*2;
            }
        });


        addReview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gps = new GPSTracker(getApplication());

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    geocoding locationAddress = new geocoding();
                    locationAddress.getAddressFromLocation(latitude, longitude,getApplicationContext(), new GeocoderHandler());
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();


                }
                reviews.setReviewComments(comment.getText().toString());
                reviews.setReviewScores(rate);
                reviews.setMovieId(event);
                reviews.setUserId(user);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateS = format.format(new Date());
                reviews.setReviewDate(dateS+"T00:00:00");
                reviews.setReviewLocation("Melbourne");
                try {
                    String numOfReviws = new getNumReviews().execute("").get();
                    int num = Integer.parseInt(numOfReviws);
                    reviews.setReviewId(String.valueOf(num+1));
                }catch(Exception e){}

                new setReviews().execute(reviews);

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MovieDetailActivity.class);
                //Movies event = events.get(position);
                event.setMovieDate(event.getMovieDate()+"T00:00:00");

                double oldRate = event.getFilmRating();
                int rateTimes = event.getFilmRatingTimes();
                double totalRate=oldRate*rateTimes;
                double newRate = rate+totalRate;
                event.setFilmRatingTimes(rateTimes+1);
                event.setFilmRating(newRate/(rateTimes+1));
                new changeMovie().execute(event);

                intent.putExtra("event", event);
                intent.putExtra("user", user);
                startActivity(intent);
            }

        });

    }


    private class changeMovie extends AsyncTask<Movies, Void, String> {

        protected String doInBackground(Movies... u) {
            MovieWeb.editMovie(u[0]);
            return null;
        }
        protected void onPostExecute(String result) {

        }
    }

    private class setReviews extends AsyncTask<Reviews, Void, String> {

        protected String doInBackground(Reviews... r) {

            //String result;
            MovieWeb.setReview(r[0]);
            return null;
        }

        protected void onPostExecute(String result) {
            if (result != null) {

            }
        }
    }


    private class getNumReviews extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return MovieWeb.getNumOfReviews();
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                super.onPostExecute(result);
            }
        }
    }



    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            location = locationAddress;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
