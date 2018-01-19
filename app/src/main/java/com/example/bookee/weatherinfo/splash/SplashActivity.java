package com.example.bookee.weatherinfo.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookee.weatherinfo.details.DetailsActivity;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity implements MvpContract.View {
    //todo a sta ovaj splash prikazuje? On bi trebalo da radi branding, da prikaze logo, neki progress bar....
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpContract.Presenter presenter=new Presenter();
        presenter.bindView(this);
        presenter.fetchInitialWeather();
    }

    public void sendWeatherInfoToMain(String city, String temp, String wind, String humidity) {//todo realno, ovo ne salje nikakve info vec otvara novi activity. Najbolje ga je i imenovati tako.
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
