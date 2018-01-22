package com.example.bookee.weatherinfo.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements MvpContract.View {

    private EditText cityName;
    private ProgressBar progressBar;

    private MvpContract.Presenter presenter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.menuAction(this, item, new MvpContract.PresenterActivityCallback() {
            @Override
            public void openActivity(Intent i) {
                startActivity(i);
                finish();
            }
        });
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button getForecast;
        getForecast = findViewById(R.id.get_forecast_button);
        cityName = findViewById(R.id.desired_city_name);
        progressBar = findViewById(R.id.progressBar);

        presenter = new Presenter();

        cityName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
        getForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desiredCity;
                desiredCity = cityName.getText().toString();
                cityName.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                presenter.getData(desiredCity);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }
    @Override
    public void receiveDataFromPresenter(TemperatureData newTemperature) {
        if (newTemperature != null) {

            addCityTemperatureToGlobal(newTemperature);

            Intent i = new Intent();
            i.putExtra("newTemp", newTemperature);
            setResult(RESULT_OK, i);
            progressBar.setVisibility(View.INVISIBLE);
            finish();
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            cityName.setVisibility(View.VISIBLE);
            Toast.makeText(this, R.string.errorWrongCityName, Toast.LENGTH_LONG).show();
        }
    }
    @SuppressLint("ApplySharedPref")
    private void addCityTemperatureToGlobal(TemperatureData newTemperature) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String oldGlobalCityList = sharedPreferences.getString(String.valueOf(R.string.globalCityListName), "");
        Type type = new TypeToken<ArrayList<TemperatureData>>() {
        }.getType();
        ArrayList<TemperatureData> globalCityList = gson.fromJson(oldGlobalCityList, type);

        globalCityList.add(newTemperature);

        String newGlobalCityList = gson.toJson(globalCityList);
        editor.putString(String.valueOf(R.string.globalCityListName), newGlobalCityList);
        editor.commit();
    }
    @Override
    public void errorHappened(String errorMessage) {
        progressBar.setVisibility(View.INVISIBLE);
        cityName.setVisibility(View.VISIBLE);
        showErrorDialog(errorMessage);
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogTheme));
        builder.setTitle(R.string.errorTitle)
                .setMessage(message)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent reset = getIntent();
                        finish();
                        startActivity(reset);
                    }
                })
                .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                }).create()
                .show();
    }
}


