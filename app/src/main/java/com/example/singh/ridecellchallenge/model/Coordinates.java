package com.example.singh.ridecellchallenge.model;

import java.io.Serializable;

/**
 * Created by singh on 09-Apr-17.
 */

public class Coordinates implements Serializable{

    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean validate(){
        return  !(latitude == null || longitude == null);

    }


    public Coordinates(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }
}
