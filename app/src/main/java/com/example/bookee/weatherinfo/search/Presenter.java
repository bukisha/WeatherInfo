package com.example.bookee.weatherinfo.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.list.ListActivity;

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
    public void getData(String desiredCity) {
        model.fetchData(desiredCity, new MvpContract.FetchNewCityWeatherInfoCallback() {
            @Override
            public void fetchNewWeather(TemperatureData newTemperatureData) {
                if (view == null) return;
                view.receiveDataFromPresenter(newTemperatureData);
            }
            @Override
            public void error(Throwable t) {
                if(view == null) return;

                view.errorHappened(t.getMessage());
            }
        });
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
