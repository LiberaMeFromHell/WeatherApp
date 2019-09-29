package com.example.weatherapp.view;


import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.model.network.DataReceiver;
import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private SearchView searchView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

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
                            dataReceiver.getCityList(apiKey, "295954")
                                    .subscribeWith(new DisposableObserver<List<Location>>() {
                                        @Override
                                        public void onNext(List<Location> locations) {
                                            Log.d("tag", "onViewCreated");
                                            updateCurrentCondition(locations);
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

    public void updateCurrentCondition(List<Location> locations) {
        for (Location l : locations) {
            Log.d("tag", "updateCurrentCondition: " + l.getLocalizedName());
        }
    }
}
