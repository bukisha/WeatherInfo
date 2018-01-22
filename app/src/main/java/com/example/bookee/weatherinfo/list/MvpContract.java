package com.example.bookee.weatherinfo.list;


import android.content.Intent;

import java.util.ArrayList;

public interface MvpContract {

    interface View {
        void displaySearchedCities(ArrayList<String> listOfCityNames);
    }

     interface Presenter {
         void getCityList(String cityList);
         void bindView(View v);
         void unbindView();
         void newCitySelected(int i, android.app.ListActivity activity, PresenterToActivityCallback callback);
     }
     interface PresenterToActivityCallback {
        void onSucess(Intent openActivity);
        void onFailure();
     }
}
