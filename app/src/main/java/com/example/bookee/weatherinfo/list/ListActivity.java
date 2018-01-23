package com.example.bookee.weatherinfo.list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.bookee.weatherinfo.R;
import java.util.ArrayList;

public class ListActivity extends android.app.ListActivity implements MvpContract.View {
    private ListView listView;
    private MvpContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=findViewById(android.R.id.list);
        presenter=new Presenter() ;

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.newCitySelected(i, (android.app.ListActivity) view.getContext(),new MvpContract.PresenterToActivityCallback() {
                    @Override
                    public void onSucess(Intent i) {
                        startActivity(i);
                        finish();
                    }
                    @Override
                    public void onFailure() {
                        //TODO what errors can happen here?!?! think about it
                    }
                });
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//todo sta mislis, da li ovo pripada ovde? Ili mozda u modelu?
        String cityList=sharedPreferences.getString(String.valueOf(R.string.globalCityListName),"");
        presenter.getCityList(cityList);
    }
    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }
    @Override
    public void displaySearchedCities(ArrayList<String> listOfCityNames) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfCityNames);
        listView.setAdapter(adapter);
    }


}