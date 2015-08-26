package com.example.yzha502.movienight;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzha502.movienight.entities.Message;
import com.example.yzha502.movienight.entities.Movies;
import com.example.yzha502.movienight.entities.Reviews;
import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.MovieWeb;
import com.example.yzha502.movienight.webCom.UserWeb;

import java.io.InputStream;
import java.util.ArrayList;


public class MovieDetailActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private RatingBar ratingBar;
    private Users user;
    private Movies event;
    private TextView movieName;
    private TextView movieDesc;
    private TextView movieActor;
    private TextView movieGener;
    private TextView movieDirector;
    private ImageView imageView;
    private ListView eventList;
    private ArrayList<Reviews> reviewses;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        user=new Users();
        event=new Movies();
        Intent i = getIntent();
        event = (Movies)i.getParcelableExtra("event");
        user = (Users)i.getParcelableExtra("user");

// initailize widget
        movieActor = (TextView)findViewById(R.id.actorDetail);
        movieDesc=(TextView)findViewById(R.id.descriptionDetail);
        movieDirector=(TextView)findViewById(R.id.director);
        movieGener = (TextView)findViewById(R.id.gerneDetail);
        movieName = (TextView)findViewById(R.id.movieNameDetail);
        imageView = (ImageView)findViewById(R.id.movieImage);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        eventList = (ListView) findViewById(R.id.listView2);
        ratingBar.setRating((float)event.getFilmRating()/2);
        movieName.setText("Name:" + event.getMovieName());
        movieGener.setText("Genre:"+event.getMovieGenre());
        movieActor.setText("Actors:"+event.getMovieActor());
        movieDirector.setText("Director:"+event.getMovieDirector());
        movieDesc.setText(event.getMovieDesc());
        reviewses = new ArrayList<Reviews>();
        new DownloadImageTask(imageView).execute(event.getMovieImage());

        String reviewResult="";
        try {
            reviewResult = new getAllReviews().execute(event.getMovieId()).get();
        }catch (Exception e){}
        reviewses= UserWeb.parseReview(reviewResult);
        ReviewAdapter adapter = new ReviewAdapter(getApplication(), reviewses);
        eventList.setAdapter(adapter);


        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


//                Intent intent = new Intent();
//                intent.setClass(getApplication(), UserLikedMovies.class);
                Reviews rev = reviewses.get(position);
//                intent.putExtra("userName", event.getUserId().getUserEmail());
//                intent.putExtra("user", user);
//                startActivity(intent);

                Message message=new Message();
                message.setSenderId(user.getUserEmail());
                message.setConten("I invite you to watch "+event.getMovieName()+" together");
                message.setMessageId(1);
                message.setResiverId(rev.getUserId().getUserEmail());
                new createInvitastion().execute(message);
                Toast.makeText(getApplicationContext(), "invitation sended successfully", Toast.LENGTH_LONG).show();

            }
        });




    }



//customer method


    private class createInvitastion extends AsyncTask<Message, Void, String> {

        protected String doInBackground(Message... m) {
            UserWeb.setMessage(m[0]);
            return null;
        }
        protected void onPostExecute(String result) {

        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


    private class getAllReviews extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return MovieWeb.getReviews(name[0]);
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                super.onPostExecute(result);
            }
        }
    }

    private class setLikeMovies extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return UserWeb.setLikeMovies(name[0],name[1]);
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                super.onPostExecute(result);
            }
        }
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.User_like_movie_Rela, PlaceholderFragment.newInstance(position + 1))
//                .commit();
        Intent i;
        switch (position) {
            case 1:
                i = new Intent(getApplicationContext(), HomeActivity.class);
                i.putExtra("user", user);
                startActivity(i);
                break;
            case 2:
                i = new Intent(getApplicationContext(), LatestMovie.class);
                i.putExtra("user", user);
                startActivity(i);
                break;
            case 3:
                i = new Intent(getApplicationContext(), HotMovie.class);
                i.putExtra("user", user);
                startActivity(i);
                break;
            case 4:
                i = new Intent(getApplicationContext(), UserLikedMovies.class);
                i.putExtra("user", user);
                startActivity(i);
                break;
            case 5:
                i = new Intent(getApplicationContext(), InvitationActivity.class);
                i.putExtra("user", user);
                startActivity(i);
                break;
            case 6:
                i = new Intent(getApplicationContext(), ChangeUserProfile.class);
                i.putExtra("user", user);
                startActivity(i);
                break;
            case 7:
                i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("user", user);
                startActivity(i);
                break;


        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
//            case 6:
//                mTitle = getString(R.string.title_section6);
//                break;
        }
    }

    public void restoreActionBar() {
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout_detail));
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id==R.id.add_like){
            new setLikeMovies().execute(user.getUserEmail(),event.getMovieId());
            Toast.makeText(getApplicationContext(), "this movie now is in your favour list", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.add_review) {
            Intent i = new Intent(this, AddReviewActivity.class);
            i.putExtra("user",user);
            i.putExtra("event",event);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
