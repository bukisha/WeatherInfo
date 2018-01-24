package com.example.bookee.weatherinfo.list;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.splash.SplashActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Model implements MvpContract.Model {
    private Context appContext;

    public Model(Context context) {
        appContext = context;
    }

    @Override
    public void getGlobalCityList(MvpContract.ModelToPresenterCallback callback) {

        ArrayList<TemperatureData> listOfCityTemps=getAllCitiesTemperature();
        ArrayList<String> listOfCityNames = new ArrayList<>();
        for (int i = 0; i < listOfCityTemps.size(); i++) {
            listOfCityNames.add(listOfCityTemps.get(i).getName());
        }
        callback.onSucess(listOfCityNames);
    }

    @Override
    public TemperatureData getSelectedCityTemperature(int i) {
        ArrayList<TemperatureData> listOfCityTemps=getAllCitiesTemperature();
        return  listOfCityTemps.get(i);
    }

    public ArrayList<TemperatureData> getAllCitiesTemperature() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        String cityList = sharedPreferences.getString(SplashActivity.GLOBAL_CITY_LIST_NAME, "");

        Gson gson = new Gson();

        Type type = new TypeToken<ArrayList<TemperatureData>>() {}.getType();
        ArrayList<TemperatureData> listOfCityTemps = gson.fromJson(cityList, type);

        return listOfCityTemps;
    }
}