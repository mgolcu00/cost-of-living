package com.example.cost_of_livng_v01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CityActivity extends AppCompatActivity {
    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras =getIntent().getExtras();
        name=extras.getString("name");
        setTitle(name);

    }
}
