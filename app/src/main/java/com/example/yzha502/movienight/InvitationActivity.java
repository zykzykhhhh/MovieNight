package com.example.yzha502.movienight;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.yzha502.movienight.entities.Users;


public class InvitationActivity extends TabActivity {

    private Users user;
    private TabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        user=new Users();
        Intent i = getIntent();
        user = (Users)i.getParcelableExtra("user");

        mTabHost=getTabHost();
        TabHost.TabSpec spec;

        Intent intent =new Intent(getApplication(),RecivedActivity.class);
        intent.putExtra("user",user);
        spec=mTabHost.newTabSpec("In").setIndicator("In").setContent(intent);
        mTabHost.addTab(spec);

        intent =new Intent(getApplication(),SendedActivity.class);
        intent.putExtra("user",user);
        spec=mTabHost.newTabSpec("Out").setIndicator("Out").setContent(intent);
        mTabHost.addTab(spec);

        mTabHost.setCurrentTab(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invitation, menu);
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
