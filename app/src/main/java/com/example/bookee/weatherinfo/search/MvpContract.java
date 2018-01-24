package com.example.bookee.weatherinfo.search;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.bookee.weatherinfo.data.TemperatureData;

interface MvpContract {

   interface View  {
       void receiveDataFromPresenter(TemperatureData data);
       void errorHappened(String message);
   }
    interface Presenter  {
       void bindView(View v);
       void unbindView();
       void getData(String desiredCity,Context context);
        void menuAction(AppCompatActivity searchActivity, MenuItem item, PresenterActivityCallback presenterActivityCallback);
    }
    interface Model {
       void fetchData(String city,FetchNewCityWeatherInfoCallback callback);
   }
    interface FetchNewCityWeatherInfoCallback {
       void fetchNewWeather( TemperatureData temperatureData);
       void error(String message);
   }

    interface PresenterActivityCallback {
       void openActivity(Intent i);
    }
}
