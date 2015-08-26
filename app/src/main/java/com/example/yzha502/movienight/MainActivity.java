package com.example.yzha502.movienight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.UserWeb;

import java.util.concurrent.ExecutionException;


public class MainActivity extends Activity {


        private EditText StudentID;
        private EditText password;
        private TextView Stidentid;
        private TextView passwordid;
        private TextView message;
        public String hasStudent = "";
        public String truePassword = "";
        public static Users user;

    public static Users getUser(){
        return user;
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            StudentID = (EditText) this.findViewById(R.id.StudentIDText);
            password = (EditText) this.findViewById(R.id.PasswordeditText);
            Stidentid = (TextView) this.findViewById(R.id.StudentIDTextView);
            passwordid = (TextView) this.findViewById(R.id.PasswordTextView);
            message = (TextView) this.findViewById(R.id.message_login);





            ((Button) findViewById(R.id.LoginButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(isOnline())
                    {

                        try {

                            truePassword = new truePassword().execute(StudentID.getText().toString(), password.getText().toString()).get();

                        } catch (InterruptedException | ExecutionException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//                        while (truePassword.equals("")) {
//
//                        }
                        if (!truePassword.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Wrong PassWord please try again", Toast.LENGTH_LONG).show();

                        } else if (truePassword.equals("true")) {
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            //Users user;
                            try {
                                String userS = new getUser().execute(StudentID.getText().toString()).get();
                                user = UserWeb.parseUser(userS);
                                intent.putExtra("user",user);
                            }catch(Exception e){}
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "You are offLine,\n please check your internet connection", Toast.LENGTH_LONG).show();
                    }
                }
            });


            ((Button) findViewById(R.id.Signupbutton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isOnline()) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "You are offLine,\n please check your internet connection", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }


        private class truePassword extends AsyncTask<String, Void, String> {

            protected String doInBackground(String... stuID) {

                return UserWeb.passwordVerification(stuID[0], stuID[1]);
            }

            protected void onPostExecute(String result) {
                if (result != null) {
                    super.onPostExecute(result);
                }

            }

        }


        private class getUser extends AsyncTask<String, Void, String> {

            protected String doInBackground(String... stuID) {

                return UserWeb.getUser(stuID[0]);
            }

            protected void onPostExecute(String result) {
                if (result != null) {
                    super.onPostExecute(result);
                }

            }

        }




    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }


}
