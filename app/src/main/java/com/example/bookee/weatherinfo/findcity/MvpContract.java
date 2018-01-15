package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;


public interface MvpContract {

   interface View extends BaseView {
       void startNewActivity();


   }

   interface Presenter extends BasePresenter {



       void getData(String desiredCity);
   }

   interface Model {

       void fetchData(String city,FetchNewDataCallback callback);
   }

   interface FetchNewDataCallback {

       void fetchNewData(String cityName,CityForecastInfo info);
       void error();

   }
}
