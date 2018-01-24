package com.example.bookee.weatherinfo.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.list.ListActivity;
import com.example.bookee.weatherinfo.splash.SplashActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    Presenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        model = new Model(repository);
    }
    @Override
    public void bindView(MvpContract.View view) {
        this.view = view;
    }
    @Override
    public void getData(String desiredCity, final Context context) {
        model.fetchData(desiredCity, new MvpContract.FetchNewCityWeatherInfoCallback() {
            @Override
            public void fetchNewWeather(TemperatureData newTemperatureData) {
                if (view == null) return;
                updateGlobalCityList(newTemperatureData,context);
                view.receiveDataFromPresenter(newTemperatureData);
            }
            @Override
            public void error(String message) {
                if(view == null) return;
                view.errorHappened(message);
            }
        });
    }

    @SuppressLint("ApplySharedPref")
    private void updateGlobalCityList(TemperatureData newTemperatureData, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String oldGlobalCityList = sharedPreferences.getString(SplashActivity.GLOBAL_CITY_LIST_NAME, "");
        Type type = new TypeToken<ArrayList<TemperatureData>>(){}.getType();
        ArrayList<TemperatureData> globalCityList = gson.fromJson(oldGlobalCityList, type);

        globalCityList.add(newTemperatureData);

        String newGlobalCityList = gson.toJson(globalCityList);
        editor.putString(String.valueOf(SplashActivity.GLOBAL_CITY_LIST_NAME), newGlobalCityList);
        editor.commit();
    }

    @Override
    public void menuAction(AppCompatActivity searchActivity, MenuItem item, MvpContract.PresenterActivityCallback callback) {
        Intent openList=new Intent(searchActivity, ListActivity.class);
        callback.openActivity(openList);
    }
    @Override
    public void unbindView() {
        view = null;
    }
}
