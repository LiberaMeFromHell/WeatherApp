package com.example.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weatherapp.R;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerBuilder drawerBuilder = new DrawerBuilder(this);
        drawerBuilder.withActivity(this).build();
    }
}
