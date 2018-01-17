package com.example.bookee.weatherinfo.details;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;

import com.example.bookee.weatherinfo.findcity.SearchActivity;


public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final int REQUEST_NEW_CITY = 2;
    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private ProgressBar progressBar;

    private MvpContract.Presenter weatherPresenter;
    private boolean newCityData = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DEBUG", "onCREATE");
        setContentView(R.layout.details_activity);

        city = findViewById(R.id.current_city);
        temperature = findViewById(R.id.current_temp);
        windSpeed = findViewById(R.id.wind_info);
        humidity = findViewById(R.id.humidity_info);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);
        weatherPresenter = new Presenter();

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherPresenter.ActionSomethingIsClicked();
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
        super.onResume();
        weatherPresenter.bindView(this);
        Log.i("DEBUG", "onRESUME");

        /*Intent intent = getIntent();

        Bundle extras = intent.getExtras();*/

        //if (extras != null) {
        //Log.i("DEBUG", "pre vadjenja iz extras");
        //   weatherPresenter.displayNewData(extras);

        // } else {
        Log.i("DEBUG", "NE VADIM NISTA IZ extras");
        if (!newCityData) {
            progressBar.setVisibility(View.VISIBLE);
            weatherPresenter.getData();
        }
        // }
    }

    @Override
    public void errorHappened(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNewActivity() {
        Intent newCityIntent = new Intent(this, SearchActivity.class);
        Log.i("CODE", String.valueOf(REQUEST_NEW_CITY));
        Log.i("DEBUG", "startActivityForRes");
        startActivityForResult(newCityIntent, REQUEST_NEW_CITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("DEBUG novo ime", data.getExtras().getString("name"));
        Log.i("DEBUG requestCode je", String.valueOf(requestCode));
        Log.i("DEBUG resultCode je", String.valueOf(resultCode));
        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_CITY) {
            Bundle newCityWeather = data.getExtras();
            Log.i("DEBUG novo ime 2", newCityWeather.getString("name"));
            newCityData = true;
            weatherPresenter.bindView(this);
            weatherPresenter.displayNewData(newCityWeather);

        } else {
            errorHappened("nisu primljeni novi podaci");
        }

    }

    @Override
    public void updateWithNewData(String name, String temp, String wind, String humid) {
        city.setText(name);
        temperature.setText(temp);
        windSpeed.setText(wind);
        humidity.setText(humid);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
