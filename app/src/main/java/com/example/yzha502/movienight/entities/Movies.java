package com.example.yzha502.movienight.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yzha502 on 5/06/15.
 */
public class Movies implements Parcelable{
    private String movieDate;
    private String movieDesc;
    private Double filmRating;
    private Integer filmRatingTimes;
    private String movieId;
    private String movieName;
    private String movieGenre;
    private String movieDirector;
    private String movieActor;
    private String movieImage;
    private String movieVedio;

    public Movies() {
    }

    public Movies(String movieDate,String movieDesc,Double filmRating,Integer filmRatingTimes,String movieId,String movieName,String movieGenre,String movieDirector,String movieActor,String movieImage,String movieVedio ) {
        this.movieId = movieId;
        this.movieDate=movieDate;
        this.movieDesc=movieDesc;
        this.filmRating=filmRating;
        this.filmRatingTimes=filmRatingTimes;
        this.movieName=movieName;
        this.movieGenre=movieGenre;
        this.movieDirector=movieDirector;
        this.movieActor=movieActor;
        this.movieImage=movieImage;
        this.movieVedio=movieVedio;
    }


    public Movies(Parcel in) {

        this.movieDate=in.readString();
        this.movieId = in.readString();
        this.movieDesc=in.readString();
        this.filmRating=in.readDouble();
        this.filmRatingTimes=in.readInt();
        this.movieName=in.readString();
        this.movieGenre=in.readString();
        this.movieDirector=in.readString();
        this.movieActor=in.readString();
        this.movieImage=in.readString();
        this.movieVedio=in.readString();
    }


    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public double getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(double filmRating) {
        this.filmRating = filmRating;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieVedio() {
        return movieVedio;
    }

    public void setMovieVedio(String movieVedio) {
        this.movieVedio = movieVedio;
    }

    public void setFilmRating(Double filmRating) {
        this.filmRating = filmRating;
    }

    public Integer getFilmRatingTimes() {
        return filmRatingTimes;
    }

    public void setFilmRatingTimes(Integer filmRatingTimes) {
        this.filmRatingTimes = filmRatingTimes;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(movieDate);
        dest.writeString(movieId);
        dest.writeString(movieDesc);
        dest.writeDouble(filmRating);
        dest.writeInt(filmRatingTimes);
        dest.writeString(movieName);
        dest.writeString(movieGenre);
        dest.writeString(movieDirector);
        dest.writeString(movieActor);
        dest.writeString(movieImage);
        dest.writeString(movieVedio);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }
        public Movies[] newArray(int size) {
            return new Movies[size]; }
    };
}
