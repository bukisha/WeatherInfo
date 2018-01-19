package com.example.bookee.weatherinfo.splash;



import com.example.bookee.weatherinfo.data.CityForecastInfo;

public interface MvpContract {

    interface Presenter {
        void fetchInitialWeather();
        void bindView(View view);
    }

    interface View {
        void sendWeatherInfoToMain(String city,String temp,String wind,String humidity);
        void error(String message);
    }
    interface Model  {
        void fetchInitialData(MvpContract.InitialCityForecastFetchCallback callback);
    }

    interface InitialCityForecastFetchCallback {
        void onSuccess(CityForecastInfo info);
        void onFailure(Throwable t);
    }
}
