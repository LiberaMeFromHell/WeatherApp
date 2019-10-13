package com.example.weatherapp.view;

import android.text.BoringLayout;
import android.util.Log;

import com.example.weatherapp.R;

public class WeatherIcon {

    public int iconPicker(int icon_id) {

        int iconPath = R.drawable.ic_na;
        Log.d("tag", "iconPicker: " + icon_id);
        switch (icon_id) {
            case 1:
            case 2:
            case 3:
            case 4:
                iconPath = R.drawable.ic_sunny;
                break;

            case 5:
                iconPath = R.drawable.ic_hazy_sunny;
                break;

            case 6:
            case 7:
            case 8:
                iconPath = R.drawable.ic_cloudy;
                break;

            case 11:
                iconPath = R.drawable.ic_fog;
                break;

            case 12:
                iconPath = R.drawable.ic_showers;
                break;

            case 13:
            case 14:
                iconPath = R.drawable.ic_day_showers;
                break;

            case 15:
                iconPath = R.drawable.ic_thunderstorm;
                break;

            case 16:
            case 17:
                iconPath = R.drawable.ic_day_thunderstorm;
                break;

            case 18:
                iconPath = R.drawable.ic_rain;
                break;

            case 19:
            case 22:
                iconPath = R.drawable.ic_snow;
                break;

            case 20:
            case 21:
                iconPath = R.drawable.ic_day_snow;
                break;

            case 24:
            case 31:
                iconPath = R.drawable.ic_cold;
                break;

            case 25:
            case 26:
                iconPath = R.drawable.ic_sleet;
                break;

            case 29:
                iconPath = R.drawable.ic_rain_and_snow;
                break;

            case 30:
                iconPath = R.drawable.ic_hot;
                break;

            case 32:
                iconPath = R.drawable.ic_windy;
                break;

            case 33:
            case 34:
            case 35:
            case 36:
                iconPath = R.drawable.ic_night_clear;
                break;

            case 37:
                iconPath = R.drawable.ic_hazy_night;
                break;

            case 38:
                iconPath = R.drawable.ic_night_cloudy;
                break;

            case 39:
            case 40:
                iconPath = R.drawable.ic_night_showers;
                break;

            case 41:
            case 42:
                iconPath = R.drawable.ic_night_thunderstorm;
                break;

            case 43:
            case 44:
                iconPath = R.drawable.ic_night_snow;
                break;
        }
        return iconPath;
    }
}
