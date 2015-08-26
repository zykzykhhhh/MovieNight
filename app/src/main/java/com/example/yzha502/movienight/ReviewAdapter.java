package com.example.yzha502.movienight;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yzha502.movienight.entities.Reviews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yzha502 on 5/06/15.
 */
public class ReviewAdapter  extends BaseAdapter {

    private Context context;
    private ArrayList<Reviews> events;

    public ReviewAdapter(Context context, ArrayList<Reviews> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.review_cell, null); // List layout here
        }

        TextView eventComments = (TextView)convertView.findViewById(R.id.descriptionAdapter);
        TextView eventName = (TextView)convertView.findViewById(R.id.userNameAdapter);
        TextView eventRate = (TextView)convertView.findViewById(R.id.rateAdapter);
        TextView eventDate = (TextView)convertView.findViewById(R.id.dateAdapter);
        TextView eventLocation = (TextView)convertView.findViewById(R.id.locationAdapter);

        Reviews mov = (Reviews)getItem(position);
        String userName = mov.getUserId().getUserName();
        int gender = mov.getUserId().getUserGender();
        String Location= mov.getReviewLocation();
        String date=mov.getReviewDate();
        double rate = mov.getReviewScores();

        String comments = mov.getReviewComments();
        eventComments.setText(comments);
        eventComments.setTextColor(Color.parseColor("#000000"));
        eventName.setText(userName);

        eventRate.setText("      "+String.valueOf(rate).substring(0,3));
        eventRate.setTextColor(Color.parseColor("#000000"));

//        String DATE_FORMAT_NOW = "yyyy-MM-dd";
//        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
//        String stringDate = sdf.format(date);
        eventDate.setText(" "+date);
        eventDate.setTextColor(Color.parseColor("#000000"));
        eventLocation.setText(" "+Location);
        eventLocation.setTextColor(Color.parseColor("#000000"));
        eventName.setTextColor(Color.parseColor("#E60066"));
//        if(gender==0)
//            eventName.setTextColor(Color.parseColor("#E60066"));
//        else
//            eventName.setTextColor(Color.parseColor("#00B366"));

        return convertView;
    }
}

