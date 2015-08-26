package com.example.yzha502.movienight.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yzha502 on 5/06/15.
 */
public class Users implements Parcelable{

    private String passwd;
    private String deviceId;
    private String userEmail;
    private String userName;
    private int userGender;
    private String userCity;
    private double userLatitude;
    private double userLongitude;
//private List<Movies> moviesList;
//private List<Reviews> reviewsList;

    public Users(){}

    public Users(String userEmail, String password, String deviceId,String userName, int userGender,String userCity,double userLatitude, double userLongitude) {
        this.userEmail=userEmail;
        this.passwd=password;
        this.deviceId=deviceId;
        this.userName = userName;
        this.userGender=userGender;
        this.userCity = userCity;
        this.userLatitude=userLatitude;
        this.userLongitude=userLongitude;

    }

    public Users(Parcel in) {
        this.userEmail=in.readString();
        this.passwd=in.readString();
        this.deviceId=in.readString();
        this.userName = in.readString();
        this.userGender=in.readInt();
        this.userCity = in.readString();
        this.userLatitude= in.readDouble();
        this.userLongitude=in.readDouble();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        public Users[] newArray(int size) {
            return new Users[size];
        }
    };


    public String getUserEmail(){
        return userEmail;
    }

    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public Integer getUserGender(){
        return userGender;
    }

    public void setUserGender(Integer userGender){
        this.userGender=userGender;
    }

    public String getUserCity(){
        return userCity;
    }

    public void setUserCity(String userCity){
        this.userCity=userCity;
    }

    public Double getUserLatitude(){
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude){
        this.userLatitude=userLatitude;
    }

    public Double getUserLongitude(){
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude){
        this.userLongitude=userLongitude;
    }

    public String getDeviceId(){
        return deviceId;
    }

    public void setDeviceId(String deviceId){
        this.deviceId=deviceId;
    }

    public String getPassword(){
        return passwd;
    }

    public void setPassword(String password){
        this.passwd=password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(userEmail);
        dest.writeString(passwd);
        dest.writeString(deviceId);
        dest.writeString(userName);
        dest.writeInt(userGender);
        dest.writeString(userCity);
        dest.writeDouble(userLatitude);
        dest.writeDouble(userLongitude);


    }


}
