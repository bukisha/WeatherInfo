package com.example.bookee.weatherinfo.model;

/**
 * Created by WIN10 on 12/26/2017.
 */

public class Coord {

    private float lon;

    private float lat;


    public Coord(int lon, int lat) {
        this.lon = lon;
        this.lat = lat;
    }


    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }
}
