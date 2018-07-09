package com.example.madhavbangaru.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Madhav Bangaru on 03-07-2018.
 */

public class PlaceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PlaceFragment())
                .commit();
    }
}
