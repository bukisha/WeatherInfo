package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.CityForecastInfo;


interface MvpContract {

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

       void fetchData(String city,FetchNewCityWeatherInfoCallback callback);
   }

   interface FetchNewCityWeatherInfoCallback {

       void fetchNewWeather(String cityName, CityForecastInfo info);
       void error(Throwable t);

   }
}
