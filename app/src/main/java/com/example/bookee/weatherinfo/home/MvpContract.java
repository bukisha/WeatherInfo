package com.example.bookee.weatherinfo.home;

import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;


public interface MvpContract {


    interface View  {
        void startNewActivity();

        void updateWithNewData(String name, String temp, String wind, String humid);

       // void recieveDataFromPresenter(CityForecastInfo info);

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

        void fetchInitialData(InitialDataFetchCallback callback);


    }
//todo da li pored Initial postoji jos neki DataFetchCallback. Mislim da nema. Onda je Initial nepotrebno. Takodje, za ovo data vazi isto sto sam vec negde napisao :D
    interface InitialDataFetchCallback {

            void fetchData(CityForecastInfo info);

             void error();
    }


}
