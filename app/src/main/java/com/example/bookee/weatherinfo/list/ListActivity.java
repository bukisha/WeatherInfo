package com.example.bookee.weatherinfo.list;

import android.os.Bundle;
import com.example.bookee.weatherinfo.R;

public class ListActivity extends android.app.ListActivity implements MvpContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
}