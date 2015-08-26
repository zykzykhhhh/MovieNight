package com.example.yzha502.movienight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzha502.movienight.entities.Movies;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by yzha502 on 5/06/15.
 */
public class MoviesAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Movies> events;

    public MoviesAdapter(Context context, ArrayList<Movies> events) {
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
            convertView = inflater.inflate(R.layout.movies_cell, null); // List layout here
        }

        TextView eventTitle = (TextView)convertView.findViewById(R.id.TitleTextViewInCell);
        TextView eventDescription = (TextView)convertView.findViewById(R.id.descriptionInCell);
        TextView eventRate = (TextView)convertView.findViewById(R.id.marksCell);
        TextView eventTime = (TextView)convertView.findViewById(R.id.timeCell);

        Movies mov = (Movies)getItem(position);
        eventTitle.setText(mov.getMovieName());
        eventDescription.setText(mov.getMovieDesc());
        eventTime.setText("   "+mov.getMovieDate().substring(0,10));

        eventRate.setText(String.valueOf(mov.getFilmRating()).substring(0,3));

        if(mov.getMovieImage() == null || mov.getMovieImage().equals("")||mov.getMovieImage().equals("N/A"))
        {
            ImageView item_thumbnail_iv = (ImageView) convertView.findViewById(R.id.imageView);
            item_thumbnail_iv.setVisibility(View.GONE);
        }else
        {
            new DownloadImageTask((ImageView) convertView.findViewById(R.id.imageView))
                    .execute(mov.getMovieImage());
        }
        eventTitle.setTextColor(Color.parseColor("#000000"));
        eventDescription.setTextColor(Color.parseColor("#000000"));
        eventRate.setTextColor(Color.parseColor("#000000"));
        eventTime.setTextColor(Color.parseColor("#000000"));
        return convertView;
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

}
