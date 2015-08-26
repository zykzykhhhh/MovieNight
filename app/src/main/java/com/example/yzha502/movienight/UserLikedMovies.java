package com.example.yzha502.movienight;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yzha502.movienight.entities.Movies;
import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.MovieWeb;
import com.example.yzha502.movienight.webCom.UserWeb;

import java.util.ArrayList;

import static com.example.yzha502.movienight.webCom.MovieWeb.getMoviesByName;


public class UserLikedMovies extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private ListView eventList;
    private ArrayList<Movies> events;
    private Users user;


    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

//    mNavigationDrawerFragment = (NavigationDrawerFragment)
//    getFragmentManager().findFragmentById(R.id.navigation_drawer);
//    mTitle = getTitle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_liked_movies);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mNavigationDrawerFragment.setPosition(3);
        mTitle = getTitle();


        user=new Users();
        Intent i = getIntent();
        user = (Users)i.getParcelableExtra("user");

        eventList = (ListView) findViewById(R.id.listViewInLikeMovies);
        events = new ArrayList<Movies>();

        try {
            String res = new getLikeMovies().execute(user.getUserEmail()).get();
            events= MovieWeb.parseJsonMovie(res);
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


    private class getLikeMovies extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return UserWeb.getLikeMovies(name[0]);
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
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
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
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }

    public void restoreActionBar() {
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout_like));
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
        getMenuInflater().inflate(R.menu.menu_user_liked_movies, menu);
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
            View rootView = inflater.inflate(R.layout.user_like_movies_fragment, container, false);
            return rootView;
            //           return null;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((UserLikedMovies) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


}
