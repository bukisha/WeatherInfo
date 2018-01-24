package com.example.bookee.weatherinfo.list;

import android.content.Context;
import android.content.Intent;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.details.DetailsActivity;
import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.View view;
    private MvpContract.Model model;

    public Presenter(Context context) {
        model=new Model(context);
    }

    @Override
    public void getCityList(Context context) {
        model.getGlobalCityList(new MvpContract.ModelToPresenterCallback() {
            @Override
            public void onSucess(ArrayList<String> listOfCities) {
                view.displaySearchedCities(listOfCities);
            }
            @Override
            public void onFailure() {
            //TODO errors?
            }
        });
    }
        @Override
        public void bindView (MvpContract.View v){
            this.view = v;
        }
        @Override
        public void unbindView () {
            this.view = null;
        }

        @Override
        public void newCitySelected ( int i, android.app.ListActivity activity, MvpContract.PresenterToActivityCallback callback){
            Intent openActivity = new Intent(activity, DetailsActivity.class);

            TemperatureData selectedCityTemperature = model.getSelectedCityTemperature(i);
            openActivity.putExtra(DetailsActivity.SELECTED_CITY_KEY, selectedCityTemperature);

            callback.onSucess(openActivity);
        }
    }
