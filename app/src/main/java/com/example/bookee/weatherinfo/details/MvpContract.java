package com.example.bookee.weatherinfo.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.bookee.weatherinfo.data.TemperatureData;

interface MvpContract {

    interface View  {
        void startNewActivity();
        void updateWithNewData(TemperatureData temperatureData);
        void errorHappened(String s);
    }

    interface Presenter  {
        void bindView(View v);
        void unbindView();
        void actionSomethingIsClicked();
        void displayNewData(TemperatureData neTemperatureData);

        void menuAction(AppCompatActivity detailsActivity, int item, PresenterActivityCallback callback);
    }

    interface Model  {
        void fetchInitialData(InitialCityForecastFetchCallback callback);
    }

    interface InitialCityForecastFetchCallback {
        void onSuccess(TemperatureData temperatureData);
        void error(Throwable t);
    }
    interface PresenterActivityCallback {

        void openActivity(Intent i);
    }
}
