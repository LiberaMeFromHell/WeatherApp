package com.example.weatherapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.pojo.citysearch.Location;

import org.w3c.dom.Text;

import java.util.List;

public class CitySearchRecyclerAdapter extends RecyclerView.Adapter<CitySearchRecyclerAdapter.LocationViewHolder> {

    private List<Location> locations;

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_location_layout, parent,false);
        LocationViewHolder locationViewHolder = new LocationViewHolder(view);
        return locationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        Location location = locations.get(position);
        holder.getLocationTextView().setText(location.getLocalizedName());
        holder.getAreaTextView().setText(location.getCountry().getLocalizedName() + " " +
                location.getCountry().getID() + " " +
                location.getAdministrativeArea().getLocalizedName());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView locationTextView;
        TextView areaTextView;
        String id;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
