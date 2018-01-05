package com.example.bookee.weatherinfo.homeweather;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.findcity.FindCityActivity;
import com.example.bookee.weatherinfo.mvp.BaseView;


public class HomeWeatherDetailsActivity extends AppCompatActivity implements BaseView {
     TextView city,temperature,windSpeed,humidity;
     FloatingActionButton floatingActionButton;

     WeatherPresenter WeatherPresenter;
     ActivitySwitcher SwitcherPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        city= findViewById(R.id.current_city);
        temperature= findViewById(R.id.current_temp);
        windSpeed=findViewById(R.id.wind_info);
        humidity=findViewById(R.id.humidity_info);
        floatingActionButton=findViewById(R.id.floatingActionButton);



        WeatherPresenter=new WeatherPresenter(this);
        WeatherPresenter.getData();

        SwitcherPresenter=new ActivitySwitcher(this);
    }


    @Override
    public void setText(CityForecastInfo info) {
       int tempToDisplay;
        city.setText(info.getName());
        tempToDisplay= (int) (info.getMain().getTemp()-273.15 );
        temperature.setText(String.valueOf(tempToDisplay));
        windSpeed.setText(String.valueOf((int) info.getWind().getSpeed()));
        humidity.setText(String.valueOf(info.getMain().getHumidity()));
    }

    @Override
    public void errorHappened() {
        Toast.makeText(this,"Doslo je do greske!",Toast.LENGTH_LONG).show();
    }
    public void findCityForecast(View v) {
        SwitcherPresenter.floatingActionButtonIsClicked();


    }

    @Override
    public void startNewActivity() {
        Intent i=new Intent(this, FindCityActivity.class);
        startActivity(i);
    }


}