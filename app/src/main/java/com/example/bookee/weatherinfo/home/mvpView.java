package com.example.bookee.weatherinfo.home;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.mvp.BaseView;



public interface mvpView extends BaseView {


       void startNewActivity();
       void updateWithNewData(String name, String temp, String wind, String humid);


}
