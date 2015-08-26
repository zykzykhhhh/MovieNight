package com.example.yzha502.movienight;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yzha502.movienight.entities.Movies;
import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.MovieWeb;

import java.util.ArrayList;


public class LatestMovie extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks   {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private ListView eventList;
    private Users user;
    private ArrayList<Movies> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_movie);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        user=new Users();
        Intent i = getIntent();
        user = (Users)i.getParcelableExtra("user");

        eventList = (ListView) findViewById(R.id.listViewInHotMovies);
        events = new ArrayList<Movies>();

        try {
            String res = new getLatestMovies().execute(user.getUserEmail()).get();
            ArrayList<Movies> ml = new ArrayList<Movies>();
            ml = MovieWeb.parseJsonMovie(res);
            int size =ml.size();
            for(int j=0;j<size/2;j++) {
                events.add(ml.get(j));
            }
        }catch (Exception e){}

        MoviesAdapter adapter = new MoviesAdapter(getApplication(), events);
        eventList.setAdapter(adapter);


        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MovieDetailActivity.class);
                Movies event = events.get(position);
                intent.putExtra("event", event);
                intent.putExtra("user", user);
                startActivity(intent);


            }
        });
    }


    private class getLatestMovies extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return MovieWeb.getLatestMovie();
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
                (DrawerLayout) findViewById(R.id.drawer_layout_late));
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
//            getMenuInflater().inflate(R.menu.home, menu);
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

}
