package com.example.weatherapp.view;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.model.CitySearchRecyclerAdapter;
import com.example.weatherapp.model.network.DataReceiver;
import com.example.weatherapp.model.pojo.citysearch.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private CitySearchRecyclerAdapter recyclerAdapter;
    private SearchView searchView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<Location> locations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initRecyclerView(view);

        searchView = view.findViewById(R.id.searchView);

        DataReceiver dataReceiver = DataReceiver.getInstance();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d("tag", "onQueryTextChange: " + newText);

                String apiKey = BuildConfig.ApiKey;
                if (newText.length() > 2)
                    compositeDisposable.add(
                            dataReceiver.getCityList(apiKey, newText)
                                    .subscribeWith(new DisposableObserver<List<Location>>() {
                                        @Override
                                        public void onNext(List<Location> l) {
                                            Log.d("tag", "onViewCreated");
                                            updateCurrentCondition(l);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.d("tag", "onError" + e);
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    }));
                return false;
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
        recyclerAdapter = new CitySearchRecyclerAdapter(locations);
        recyclerView = container.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }

}
