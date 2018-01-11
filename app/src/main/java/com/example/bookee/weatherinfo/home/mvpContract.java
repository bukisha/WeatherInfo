package com.example.bookee.weatherinfo.home;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.BaseModel;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;


public interface mvpContract {


    interface View extends BaseView {
        void startNewActivity();

        void updateWithNewData(String name, String temp, String wind, String humid);

        public void recieveDataFromPresenter(CityForecastInfo info);

        void errorHappened();
    }

    interface Presenter extends BasePresenter {


        void errorMessage();


        void passResultToView(CityForecastInfo body);

    }

    interface Model extends BaseModel {

         public void getData();



    }
}
