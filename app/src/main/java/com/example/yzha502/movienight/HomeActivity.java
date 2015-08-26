package com.example.yzha502.movienight;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yzha502.movienight.entities.Movies;
import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.MovieWeb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.yzha502.movienight.webCom.MovieWeb.getMoviesByName;


public class HomeActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Users user;
    private EditText searchBox;
    private Button searchButton;
    private ListView eventList;
    private ArrayList<Movies> events;

    public static final int ADD_EVENT_REQUEST = 1;
    private static final int CHANGE_DETAIL_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        user=new Users();
        Intent i = getIntent();
        user = (Users)i.getParcelableExtra("user");

        // Set up the drawer.



//customer code
        searchBox = (EditText) findViewById(R.id.searchBox);
        searchButton = (Button) findViewById(R.id.searchButton);

        eventList = (ListView) findViewById(R.id.listView);
        events = new ArrayList<Movies>();


        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, MovieDetailActivity.class);
                Movies event = events.get(position);
                intent.putExtra("event", event);
                intent.putExtra("user", user);
                startActivity(intent);

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    events.clear();
                    String result = new getAllMovies().execute(searchBox.getText().toString()).get();
                    String gerne = new getMoviesByGren().execute(searchBox.getText().toString()).get();
                    String actor = new getMoviesByActor().execute(searchBox.getText().toString()).get();
                    String director = new getMoviesByDirector().execute(searchBox.getText().toString()).get();
                    parseJsonMovie(result);
                    parseJsonMovie(gerne);
                    parseJsonMovie(actor);
                    parseJsonMovie(director);
                    Log.i("Movies++++++", result);
                    for(int j=0;j<events.size();j++){
                        for(int k=j+1; k<events.size();k++){
                            if (events.get(j).getMovieId().equals(events.get(k).getMovieId())){
                                events.remove(k);
                                k-=1;
                            }
                        }

                    }


                    MoviesAdapter adapter = new MoviesAdapter(getApplication(), events);
                    eventList.setAdapter(adapter);
//
                } catch (Exception e) {
                    Log.i("adapter+++++", e.getMessage());
                }
            }
        });

    }


    public Users getUser()
    {return user;}


//parse Movie JSONArray to a ArrayList
    private void parseJsonMovie(String s) {
        // ArrayList<Movies> movieses = new ArrayList();
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
                events.add(movie);
                // return movieses;
            }

        } catch (JSONException e) {
        }
        // return null;
    }


    private class getAllMovies extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return getMoviesByName(name[0]);
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                super.onPostExecute(result);
            }
        }
    }

    private class getMoviesByGren extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return MovieWeb.getMoviesByGren(name[0]);
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                super.onPostExecute(result);
            }
        }
    }


    private class getMoviesByDirector extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return MovieWeb.getMoviesByDirector(name[0]);
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                super.onPostExecute(result);
            }
        }
    }


    private class getMoviesByActor extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return MovieWeb.getMoviesByActor(name[0]);
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
                (DrawerLayout) findViewById(R.id.drawer_layout));
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
            getMenuInflater().inflate(R.menu.home, menu);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
            //           return null;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
