package com.example.bookee.weatherinfo.model;

/**
 * Created by WIN10 on 12/26/2017.
 */

public class Main {

    private float temp;
    private int pressure;
    private int humidity;
    private float temp_min;
    private float temp_max;
    private int sea_level;
    private int gnd_level;


    public Main(int temp, int pressure, int humidity, int temp_min, int temp_max, int sea_level, int gnd_level) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.sea_level = sea_level;
        this.gnd_level = gnd_level;
    }

    public float getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public int getSea_level() {
        return sea_level;
    }

    public int getGnd_level() {
        return gnd_level;
    }
}
