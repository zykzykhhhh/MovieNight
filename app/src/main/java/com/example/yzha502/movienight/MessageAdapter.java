package com.example.yzha502.movienight;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yzha502.movienight.entities.Message;
import com.example.yzha502.movienight.entities.Reviews;

import java.util.ArrayList;

/**
 * Created by yzha502 on 6/06/15.
 */
public class MessageAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Message> events;

    public MessageAdapter(Context context, ArrayList<Message> events) {
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
            convertView = inflater.inflate(R.layout.message_cell, null); // List layout here
        }

        TextView Sender = (TextView)convertView.findViewById(R.id.Sender_message);
        TextView getter = (TextView)convertView.findViewById(R.id.reciver_message);
        TextView message = (TextView)convertView.findViewById(R.id.message_detail);


        Message mov = (Message)getItem(position);
        String senderName = mov.getSenderId();

        String getterName= mov.getResiverId();
        getter.setText(getterName);
        getter.setTextColor(Color.parseColor("#000000"));



        String messageDetail=mov.getConten();


        Sender.setText(senderName);
        Sender.setTextColor(Color.parseColor("#000000"));


        message.setText(messageDetail);
        message.setTextColor(Color.parseColor("#000000"));



        return convertView;
    }
}
