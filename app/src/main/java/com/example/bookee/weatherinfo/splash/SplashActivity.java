package com.example.bookee.weatherinfo.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bookee.weatherinfo.details.DetailsActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startApp=new Intent(this, DetailsActivity.class);
        startActivity(startApp);
        finish();
    }
}
