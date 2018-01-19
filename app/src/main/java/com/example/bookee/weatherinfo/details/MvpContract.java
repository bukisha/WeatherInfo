package com.example.bookee.weatherinfo.details;

import android.os.Bundle;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
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
        void displayNewData(Bundle extras);
        void getData();
    }

    interface Model  {
        void fetchInitialData(InitialCityForecastFetchCallback callback);
    }

    interface InitialCityForecastFetchCallback {
        void onSuccess(CityForecastInfo info);
        void error(Throwable t);
    }
}
