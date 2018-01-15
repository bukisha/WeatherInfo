package com.example.bookee.weatherinfo.home;

import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.BaseModel;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;


public interface MvpContract {


    interface View extends BaseView {
        void startNewActivity();

        void updateWithNewData(String name, String temp, String wind, String humid);

        public void recieveDataFromPresenter(CityForecastInfo info);

        void errorHappened(String s);
    }

    interface Presenter extends BasePresenter {


        void errorMessage(String s);


        void passResultToView(CityForecastInfo body);

        void ActionSomethingIsClicked();

        void displayNewData(Bundle extras);

        void getData();

    }

    interface Model extends BaseModel {

        // public void getData();

         void fetchInitialData(InitialDataFetchCallback callback);


    }

    interface InitialDataFetchCallback {

            void fetchData(CityForecastInfo info);

             void error(Throwable t);
    }


}
