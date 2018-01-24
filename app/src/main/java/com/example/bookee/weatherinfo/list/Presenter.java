package com.example.bookee.weatherinfo.list;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.details.DetailsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.View view;
    private  ArrayList<TemperatureData> listOfCityTemps;
    public Presenter() {}
    @Override
    public void getCityList(Context listActivity) {

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(listActivity);
        String cityList=sharedPreferences.getString(String.valueOf(R.string.globalCityListName),"");
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<TemperatureData>>(){}.getType();
         listOfCityTemps=gson.fromJson(cityList,type);

        ArrayList<String> listOfCityNames=new ArrayList<>();
        for(int i=0;i<listOfCityTemps.size();i++) {
            listOfCityNames.add(listOfCityTemps.get(i).getName());
            if(view==null) return;
            view.displaySearchedCities(listOfCityNames);
        }
    }
    @Override
    public void bindView(MvpContract.View v) {
        this.view=v;
    }
    @Override
    public void unbindView() {
        this.view=null;
    }

    @Override
    public void newCitySelected(int i, android.app.ListActivity activity, MvpContract.PresenterToActivityCallback callback) {
        Intent openActivity=new Intent(activity, DetailsActivity.class);

        TemperatureData selectedCityTemperature=listOfCityTemps.get(i);
        openActivity.putExtra("selectedCityTemp",selectedCityTemperature);
        callback.onSucess(openActivity);
    }
}
