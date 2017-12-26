package com.example.bookee.weatherinfo.model;

/**
 * Created by WIN10 on 12/26/2017.
 */

public class Sys {

    private int type;
    private int id;
    private float message;
    private String country;
    private int sunrise;
    private int sunset;

    public Sys(int type, int id, float message, String country, int sunrise, int sunset) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }


    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public float getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }
}
