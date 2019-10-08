package com.example.weatherapp.model;

import android.app.Application;
import android.content.Context;

public class AppContext {

    private Application application;

    public AppContext (Application application) {
        this.application = application;
    }

    public Context provideContext() {
        return application.getApplicationContext();
    }
}