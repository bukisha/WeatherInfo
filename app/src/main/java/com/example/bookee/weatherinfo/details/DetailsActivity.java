package com.example.bookee.weatherinfo.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.search.SearchActivity;

public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final int REQUEST_NEW_CITY = 2;

    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private MvpContract.Presenter weatherPresenter;
    private boolean newCityData = false;//todo zasto imas ovaj flag ovde
                                            //todo RESPONSE da bih svaki put u onResume mogao da proverim da li startujem app ili u DetailsActivity dolazim iz searcha sa novim podacima

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DEBUG", "onCREATE");
        setContentView(R.layout.details_activity);

        city = findViewById(R.id.current_city);
        temperature = findViewById(R.id.current_temp);
        windSpeed = findViewById(R.id.wind_info);
        humidity = findViewById(R.id.humidity_info);

        weatherPresenter = new Presenter();

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherPresenter.actionSomethingIsClicked();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DEBUG", "onPAUSE");
        weatherPresenter.unbindView();
    }
    @Override
    protected void onResume() {
        Log.i("DEBUG", "onRESUME");
        super.onResume();
        weatherPresenter.bindView(this);
        Intent intent=getIntent();
        Bundle initialWeatherData=intent.getExtras();
        if (!newCityData) {
            weatherPresenter.displayNewData(initialWeatherData);
        }
    }
    @Override
    public void errorHappened(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
    @Override
    public void startNewActivity() {
        Intent newCityIntent = new Intent(this, SearchActivity.class);
        startActivityForResult(newCityIntent, REQUEST_NEW_CITY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_CITY) {
            Bundle newCityWeather = data.getExtras();
            newCityData = true;
            weatherPresenter.bindView(this);
            weatherPresenter.displayNewData(newCityWeather);
        } else {
            errorHappened("nisu primljeni novi podaci");
        }
    }
    @Override
    public void updateWithNewData(TemperatureData temperatureData) {
        city.setText(temperatureData.getName());
        temperature.setText(temperatureData.getTemp());
        windSpeed.setText(temperatureData.getWindSpeed());
        humidity.setText(temperatureData.getHumidity());
    }
}
