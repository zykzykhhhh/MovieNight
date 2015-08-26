package com.example.yzha502.movienight;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.yzha502.movienight.entities.Message;
import com.example.yzha502.movienight.entities.Users;
import com.example.yzha502.movienight.webCom.UserWeb;

import java.util.ArrayList;


public class SendedActivity extends Activity {

    private ListView eventList;
    private Users user;
    private ArrayList<Message> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sended);

        user=new Users();
        Intent i = getIntent();
        user = (Users)i.getParcelableExtra("user");

        eventList = (ListView) findViewById(R.id.send_Message_list);
        events = new ArrayList<Message>();

        try{
            String s = new getSendedMessage().execute(user.getUserEmail()).get();
            if(s.contains("conten")){
                events = UserWeb.parseMessage(s);
            }
        }catch(Exception e){}

        MessageAdapter adapter = new MessageAdapter(getApplication(), events);
        eventList.setAdapter(adapter);
    }


    private class getSendedMessage extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... name) {

            //String result;
            return UserWeb.findSender(name[0]);
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                super.onPostExecute(result);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sended, menu);
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
