package com.example.bookee.weatherinfo.mvp;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherData;

public interface BaseData {

        public void getData();

        public void getData(String s);

}
