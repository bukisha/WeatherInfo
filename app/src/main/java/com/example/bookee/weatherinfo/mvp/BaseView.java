package com.example.bookee.weatherinfo.mvp;


import android.view.View;

import com.example.bookee.weatherinfo.data.CityForecastInfo;

public interface BaseView  {
        //bind view to its presenter
        public void bindPresenter(BasePresenter presenter);

        public void recieveDataFromPresenter(CityForecastInfo info);
        void errorHappened();


        void startNewActivity();




        ;
}



