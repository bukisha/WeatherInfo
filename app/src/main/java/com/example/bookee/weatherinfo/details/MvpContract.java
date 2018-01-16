package com.example.bookee.weatherinfo.details;
import android.os.Bundle;
import com.example.bookee.weatherinfo.data.CityForecastInfo;

interface MvpContract {

    interface View  {
        void startNewActivity();

        void updateWithNewData(String name, String temp, String wind, String humid);

        void errorHappened(String s);
    }

    interface Presenter  {

        void bindView(View v);

        void unbindView();

        void ActionSomethingIsClicked();

        void displayNewData(Bundle extras);

        void getData();

    }

    interface Model  {

        void fetchInitialData(InitialCityForecastFetchCallback callback);
    }

    interface InitialCityForecastFetchCallback {

            void fetchWeatherInfo(CityForecastInfo info);

             void error(Throwable t);
    }
}
