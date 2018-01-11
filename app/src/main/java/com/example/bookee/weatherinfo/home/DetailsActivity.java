package com.example.bookee.weatherinfo.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.findcity.SearchActivity;
import com.example.bookee.weatherinfo.mvp.BasePresenter;


public class DetailsActivity extends AppCompatActivity implements MvpContract.View {

    public static final double CELSIOUS_FAHRENHEIT_DIFFERENCE = 273.15;
    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private FloatingActionButton floatingActionButton;

    private static Bundle extras;
    private Presenter weatherPresenter;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        city = findViewById(R.id.current_city);
        temperature = findViewById(R.id.current_temp);
        windSpeed = findViewById(R.id.wind_info);
        humidity = findViewById(R.id.humidity_info);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherPresenter.ActionSomethingIsClicked();
            }
        });

        extras = getIntent().getExtras();
        if (extras != null) {
            Log.i("DEBUG", "pre vadjenja iz extras");

            // displayNewValues();
            weatherPresenter = new Presenter();
            weatherPresenter.bindView(this);
            weatherPresenter.displayNewData(extras);
        } else {
            weatherPresenter = new Presenter();
            weatherPresenter.bindView(this);
            weatherPresenter.getData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        weatherPresenter.unbindView();
    }


    @Override
    public void errorHappened(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }




    @Override
    public void startNewActivity() {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    @Override
    public void updateWithNewData(String name, String temp, String wind, String humid) {
        city.setText(name);
        temperature.setText(temp);
        windSpeed.setText(wind);
        humidity.setText(humid);
    }

    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.weatherPresenter = (Presenter) presenter;
    }

    @Override
    public void recieveDataFromPresenter(CityForecastInfo info) {
        int tempToDisplay;
        city.setText(info.getName());
        tempToDisplay = (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
        temperature.setText(String.valueOf(tempToDisplay));
        windSpeed.setText(String.valueOf((int) info.getWind().getSpeed()));
        humidity.setText(String.valueOf(info.getMain().getHumidity()));

    }

}
