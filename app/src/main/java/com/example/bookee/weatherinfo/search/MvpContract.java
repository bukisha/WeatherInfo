package com.example.bookee.weatherinfo.search;
import com.example.bookee.weatherinfo.data.TemperatureData;

interface MvpContract {

   interface View  {
       void receiveDataFromPresenter(TemperatureData data);
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
       void fetchNewWeather( TemperatureData temperatureData);
       void error(Throwable t);
   }
}
