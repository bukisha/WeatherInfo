package com.example.bookee.weatherinfo.model;


import java.util.List;

public class CityForecastInfo {

            private Coord coord=null;
            private List<Weather> weather=null;
            private String base;
            private Main main=null;
            private int visibility;
            private Wind wind=null;
            private Cloud clouds=null;
            private int dt;
            private Sys sys=null;
            private int id;
            private String name;
            private int cod;

    public CityForecastInfo(Coord coord, List<Weather> weather, String base, Main main, int visibility, Wind wind, Cloud clouds, int dt, Sys sys, int id, String name, int cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }



}
