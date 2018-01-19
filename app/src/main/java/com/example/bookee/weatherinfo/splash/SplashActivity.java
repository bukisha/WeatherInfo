package com.example.bookee.weatherinfo.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookee.weatherinfo.details.DetailsActivity;

public class SplashActivity extends AppCompatActivity implements MvpContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpContract.Presenter presenter=new Presenter();
        presenter.bindView(this);
        presenter.fetchInitialWeather();
    }
    @Override
    public void sendWeatherInfoToMain(String city, String temp, String wind, String humidity) {
        Intent startApp=new Intent(this, DetailsActivity.class);
        Bundle initialData=new Bundle();
        initialData.putString("name",city);
        initialData.putString("temp",temp);
        initialData.putString("wind",wind);
        initialData.putString("humid",humidity);
        startApp.putExtras(initialData);
        startActivity(startApp);
        finish();
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,"Aplikacija nije startovana",Toast.LENGTH_LONG).show();
    }

}
