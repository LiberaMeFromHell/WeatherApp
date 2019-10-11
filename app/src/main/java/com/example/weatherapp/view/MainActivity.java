package com.example.weatherapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.weatherapp.R;
import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.viewmodel.WeatherViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View view;
    private NavController navController;
    private View hostFragment;
    private DrawerLayout drawerLayout;
    private LinearLayout drawerLinearLayout;
    private WeatherViewModel viewModel;
    //TODO:rid of

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        //TODO: get rid of

        hostFragment = findViewById(R.id.navhost_fragment);
        drawerLinearLayout = findViewById(R.id.drawerLinearLayout);
        navController = Navigation.findNavController(this, R.id.navhost_fragment);
        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);

        drawerLayout = findViewById(R.id.drawer_layout);


        view = findViewById(R.id.bottomFrameLayout);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.searchFragment);
                drawerLayout.closeDrawers();

            }
        });

        viewModel.getLocations().observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                populateNavigationDrawer(locations);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Setting hostFragment' height to constant value
        int wrapSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        hostFragment.measure(wrapSpec, wrapSpec);
        hostFragment.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, hostFragment.getMeasuredHeight()));
    }

    private void populateNavigationDrawer(List<Location> locations) {

        Log.d("tag", "populateNavigationDrawer: ");
        drawerLinearLayout.removeAllViews();

        for (Location location1 : locations) {
            View drawerItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_drawer_layout, drawerLinearLayout, false);
            TextView itemDrawerText = drawerItem.findViewById(R.id.itemDrawerCity);
            itemDrawerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewModel.downloadCurrentCondition(location1.getStringKey());
                    viewModel.downloadForecast(location1.getStringKey());
                }
            });
            itemDrawerText.setText(location1.getLocalizedName());
            ImageView itemDrawerImage = drawerItem.findViewById(R.id.itemDrawerDelete);
            drawerLinearLayout.addView(drawerItem);
        }
    }
}
