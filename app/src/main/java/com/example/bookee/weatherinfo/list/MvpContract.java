package com.example.bookee.weatherinfo.list;


import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
//todo di je ovodje Model? Ovo neki _VP? :D
public interface MvpContract {

    interface View {
        void displaySearchedCities(ArrayList<String> listOfCityNames);
    }

     interface Presenter {
         void getCityList(Context context);
         void bindView(View v);
         void unbindView();
         void newCitySelected(int i, android.app.ListActivity activity, PresenterToActivityCallback callback);
     }
     interface PresenterToActivityCallback {
        void onSucess(Intent openActivity);
        void onFailure();
     }
}
