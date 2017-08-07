package com.skopincev.testtaskdb2;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by skopi on 06.08.2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm
        Realm.init(this);
    }
}
