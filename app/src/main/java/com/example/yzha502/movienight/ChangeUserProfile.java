package com.example.yzha502.movienight;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.GPSTracker;
import com.example.yzha502.movienight.webCom.UserWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class ChangeUserProfile extends Activity {

    private Users user;
    private GPSTracker gps;
    private TextView email;
    private EditText name;
    private EditText password;
    private EditText password2;
    private RadioGroup radioGroup;
    private Spinner mySpinner;
    private Button register;
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<String>();
    private String city="";
    private int gender;
    private String emailString="";
    private String nameString="";
    private String passwordString="";
    private String password2String="";
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_profile);

        user=new Users();
        Intent i = getIntent();
        user = (Users)i.getParcelableExtra("user");


        email=(TextView)findViewById(R.id.email_e);
        name=(EditText)findViewById(R.id.name_e);
        password=(EditText)findViewById(R.id.password_e);
        password2= (EditText)findViewById(R.id.password1_e);
        register=(Button)findViewById(R.id.registButton_e);
        group = (RadioGroup)this.findViewById(R.id.radioGroup_e);


        email.setText("Email: "+user.getUserEmail());
        name.setText(user.getUserName());


// Spinner list set and listen.
        setSpinner();
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                city = adapter.getItem(arg2);
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                arg0.setVisibility(View.VISIBLE);
            }
        });

//radio Group Setting

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {

                int radioButtonId = arg0.getCheckedRadioButtonId();
                if(radioButtonId==R.id.radioMale_e)
                {
                    gender=1;
                }else
                    gender=0;
//                RadioButton rb = (RadioButton)RegisterActivity.this.findViewById(radioButtonId);
//                String s = rb.getText().toString();
//                if(s.equals("Man"))
//                    gender=1;
            }
        });


        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    if (password.getText().toString().equals(password2.getText().toString())) {
                        Users users = new Users();

                        users.setUserEmail(user.getUserEmail());
                        users.setUserCity(city);
                        users.setUserGender(gender);
                        users.setUserName(name.getText().toString());
                        String s = password2.getText().toString();
                        users.setPassword(s);


                        gps = new GPSTracker(getApplication());

                        // check if GPS enabled
                        if (gps.canGetLocation()) {

                            double latitude = gps.getLatitude();
                            double longitude = gps.getLongitude();
                            users.setUserLatitude(latitude);
                            users.setUserLongitude(longitude);

                            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                        } else {
                            // can't get location
                            // GPS or Network is not enabled
                            // Ask user to enable GPS/network in settings
                            gps.showSettingsAlert();

                        }
                        new insertUser().execute(users);
                        Intent intent = new Intent(getApplication(), HomeActivity.class);
                        intent.putExtra("user", users);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please check your password", Toast.LENGTH_LONG).show();
                    }
                }

        });
    }


    private void setSpinner()
    {
        list.add("Melbourne");
        list.add("Sydney");
        list.add("Perth");
        list.add("Canberra");
        city="";
        mySpinner = (Spinner)findViewById(R.id.Spinner_city_e);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

    }

    private class insertUser extends AsyncTask<Users, Void, String> {

        protected String doInBackground(Users... u) {
            UserWeb.insertUser(u[0]);
            return null;
        }
        protected void onPostExecute(String result) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_user_profile, menu);
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
