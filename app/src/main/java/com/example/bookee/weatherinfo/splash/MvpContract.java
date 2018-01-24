package com.example.bookee.weatherinfo.splash;

import com.example.bookee.weatherinfo.data.TemperatureData;

public interface MvpContract {

    interface Presenter {
        void fetchInitialWeather(SplashActivity splashActivity);
        void bindView(View view);
    }

    interface View {
        void startMainWithInitialData(TemperatureData data,long fetchDuration);
        void error(String message);
    }
    interface Model  {
        void fetchInitialData(MvpContract.InitialCityForecastFetchCallback callback);
    }

    interface InitialCityForecastFetchCallback {
        void onSuccess(TemperatureData initTempData,long durationOfFetching);
        void onFailure(Throwable t);
    }
}
