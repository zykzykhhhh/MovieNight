package com.example.yzha502.movienight.entities;

import java.util.Date;

/**
 * Created by yzha502 on 5/06/15.
 */
public class Reviews {

    private Double reviewScores;
    private String reviewId;
    private String reviewDate;
    private String reviewLocation;
    private String reviewComments;
    private Movies movieId;
    private Users userId;

    public Reviews() {
    }

    public Reviews(String reviewId,String reviewLocation, String reviewComments, Movies movieId, Users userId,String reviewDate, Double reviewScores) {
        this.reviewId = reviewId;
        this.reviewLocation=reviewLocation;
        this.reviewComments=reviewComments;
        this.movieId=movieId;
        this.userId=userId;
        this.reviewDate=reviewDate;
        this.reviewScores=reviewScores;
    }

//    public Reviews(Parcel in) {
//        this.reviewId=in.readString();
//        this.reviewLocation=in.readString();
//        this.reviewComments=in.readString();
//        this.movieId=(Movies)in.readSerializable();
//        this.userId=(Users)in.readSerializable();
//        this.reviewDate=(Date)in.readSerializable();
//        this.reviewScores=in.readDouble();
//    }

//
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public Reviews createFromParcel(Parcel in) {
//            return new Reviews(in);
//        }
//        public Reviews[] newArray(int size) {
//            return new Reviews[size]; }
//    };

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewLocation() {
        return reviewLocation;
    }

    public void setReviewLocation(String reviewLocation) {
        this.reviewLocation = reviewLocation;
    }

//    public double getReviewScores() {
//        return reviewScores;
//    }

//    public void setReviewScores(double reviewScores) {
//        this.reviewScores = reviewScores;
//    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Movies getMovieId() {
        return movieId;
    }

    public void setMovieId(Movies movieId) {
        this.movieId = movieId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Double getReviewScores() {
        return reviewScores;
    }

    public void setReviewScores(Double reviewScores) {
        this.reviewScores = reviewScores;
    }
}
