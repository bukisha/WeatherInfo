package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.BaseModel;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;


public interface MvpContract {

   interface View extends BaseView {
       void startNewActivity();


   }

   interface Presenter extends BasePresenter {

       public void passResultToView(CityForecastInfo body);

       void errorMessage(String s);

       void getData(String desiredCity);
   }

   interface Model extends BaseModel {

       public void fetchData(String city,FetchNewDataCallback callback);
   }

   public interface FetchNewDataCallback {

       void fetchNewData(String cityName,CityForecastInfo info);
       void error(String message);

   }
}
