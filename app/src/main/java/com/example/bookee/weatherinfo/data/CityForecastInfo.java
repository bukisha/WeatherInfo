package com.example.bookee.weatherinfo.data;

import com.google.gson.annotations.SerializedName;

public class CityForecastInfo {
    private int id;
    private Main main = null;
    private Wind wind = null;
    private String name;

    public static class CityForecastInfoBuilder {
        @SerializedName("main")
        private Main main = null;
        @SerializedName("wind")
        private Wind wind = null;
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;

        public CityForecastInfoBuilder setId(int id) {
            this.id = id;
            return this;
        }
        public CityForecastInfoBuilder setName(String name) {
            this.name = name;
            return this;
        }
    }

    private CityForecastInfo(CityForecastInfoBuilder builder) {

        this.main = builder.main;
        this.wind = builder.wind;
        this.id = builder.id;
        this.name = builder.name;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Main {
        @SerializedName ("temp")
        private double temp;
        @SerializedName("humidity")
        private int humidity;

        public Main(double temp,int humidity) {
            this.temp = temp;
            this.humidity = humidity;
        }

        public double getTemp() {
            return temp;
        }

        public int getHumidity() {
            return humidity;
        }
    }

    public static class Wind {
        @SerializedName("speed")
        private double speed;

        public Wind(double speed) {
            this.speed = speed;
        }
        public double getSpeed() {
            return speed;
        }
    }
}
