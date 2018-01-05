package com.example.bookee.weatherinfo.mvp;


import android.view.View;

import com.example.bookee.weatherinfo.data.CityForecastInfo;

public interface BaseView {


        public void setText(CityForecastInfo info);

        void errorHappened();


        void startNewActivity();
}



