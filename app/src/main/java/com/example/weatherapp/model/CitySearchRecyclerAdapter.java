package com.example.weatherapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.database.WeatherRepository;
import com.example.weatherapp.model.pojo.citysearch.Location;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CitySearchRecyclerAdapter extends RecyclerView.Adapter<CitySearchRecyclerAdapter.LocationViewHolder> {

    private List<Location> locations;
    Context context;

    public CitySearchRecyclerAdapter(List<Location> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_location_layout, parent,false);
        LocationViewHolder locationViewHolder = new LocationViewHolder(view);
        context = parent.getContext();
        return locationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        Location location = locations.get(position);
        holder.getLocationTextView().setText(location.getLocalizedName());
        holder.getAreaTextView().setText(location.getCountry().getLocalizedName() + " " +
                location.getCountry().getID() + " " +
                location.getAdministrativeArea().getLocalizedName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCity(location);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView locationTextView;
        TextView areaTextView;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            areaTextView = itemView.findViewById(R.id.areaTextView);
        }

        public TextView getLocationTextView() {
            return locationTextView;
        }

        public TextView getAreaTextView() {
            return areaTextView;
        }

    }
    //TODO: move out from the class
    private void addCity(Location location) {
        Observable<Location> observable = Observable
                .just(location)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
        observable.subscribeWith(new DisposableObserver<Location>() {
                    @Override
                    public void onNext(Location location) {
                        WeatherRepository.insertLocation(location);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
