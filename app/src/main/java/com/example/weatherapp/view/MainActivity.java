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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.weatherapp.R;
import com.example.weatherapp.model.AppContext;
import com.example.weatherapp.model.database.WeatherRepository;
import com.example.weatherapp.model.pojo.citysearch.Location;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {


    private View view;
    private NavController navController;
    private View hostFragment;
    private DrawerLayout drawerLayout;
    private LinearLayout drawerLinearLayout;
    //TODO:rid of
    private CurrentConditionFragment currentConditionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: DI
        AppContext appContext = new AppContext(getApplication());
        //TODO: DI
        WeatherRepository.getInstance(appContext.provideContext());

        //TODO: rid of

        hostFragment = findViewById(R.id.navhost_fragment);
        drawerLinearLayout = findViewById(R.id.drawerLinearLayout);
        navController = Navigation.findNavController(this,R.id.navhost_fragment);
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

        populateNavigationDrawer();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Setting hostFragment' height to constant value
        int wrapSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        hostFragment.measure(wrapSpec,wrapSpec);
        hostFragment.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, hostFragment.getMeasuredHeight()));
        currentConditionFragment = (CurrentConditionFragment)getSupportFragmentManager().findFragmentById(R.id.navhost_fragment);
    }

    private void populateNavigationDrawer() {
        Log.d("tag", "populateNavigationDrawer: ");
        WeatherRepository.getLocationListObservable()
                .subscribe(new Consumer<List<Location>>() {
                    @Override
                    public void accept(List<Location> locations) {
                        drawerLinearLayout.removeAllViews();
                        for (Location location1: locations) {
                            View drawerItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_drawer_layout, drawerLinearLayout, false);
                            TextView itemDrawerText = drawerItem.findViewById(R.id.itemDrawerCity);
                            itemDrawerText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    WeatherRepository.getLocationObservable(location1.getLocalizedName()).subscribe(new Consumer<Location>() {
                                        @Override
                                        public void accept(Location location) throws Exception {
                                            Log.d("loca", location == null ? "yeap" : "noup, fuck you");
                                            Log.d("fraga", currentConditionFragment == null ? "yeap" : "noup, fuck you");
                                            currentConditionFragment.updateData(location.getLocalizedName());
                                        }
                                    });

                                }
                            });
                            itemDrawerText.setText(location1.getLocalizedName());
                            ImageView itemDrawerImage = drawerItem.findViewById(R.id.itemDrawerDelete);
                            drawerLinearLayout.addView(drawerItem);
                        }
                    }
                });
    }
}
