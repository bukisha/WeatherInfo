package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.CityForecastInfo;


public interface MvpContract {

   interface View  {
       void startNewActivity();

       void reciveDataFromPresenter(String name,String temp,String windSpeed,String humidity);


       void errorHappened(String message);
   }

   interface Presenter  {

       void bindView(View v);

       void unbindView();

       void getData(String desiredCity);
   }

   interface Model {

       void fetchData(String city,FetchNewDataCallback callback);
   }

   interface FetchNewDataCallback {

       void fetchNewData(String cityName,CityForecastInfo info);
       void error();

   }
}
