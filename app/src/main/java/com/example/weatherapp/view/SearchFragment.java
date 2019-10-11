package com.example.weatherapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.model.CitySearchRecyclerAdapter;
import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchView searchView;

    private CitySearchRecyclerAdapter recyclerAdapter;

    private List<Location> locations;

    private WeatherViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initRecyclerView(view);

        searchView = view.findViewById(R.id.searchView);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d("tag", "onQueryTextChange: " + newText);

                if (newText.length() > 2)
                    viewModel.findLocations(newText);
                return false;
            }
        });

        viewModel.getFoundLocations().observe(this, new Observer<List<Location>>() {

            @Override
            public void onChanged(List<Location> foundLocations) {
                updateCurrentCondition(foundLocations);
            }
        });

        return view;
    }

    private void updateCurrentCondition(List<Location> l) {
        locations.clear();
        locations.addAll(l);
        recyclerAdapter.notifyDataSetChanged();
        for (Location i : locations) {

            Log.d("tag", "updateCurrentCondition: " + i.getLocalizedName());
        }
    }

    private void initRecyclerView(View container) {
        locations = new ArrayList<>();
        recyclerAdapter = new CitySearchRecyclerAdapter(locations,this);
        recyclerView = container.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }

    void insertLocation(Location location) {

    }

}
