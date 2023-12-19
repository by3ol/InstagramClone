package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("4PdZHTEGvpqVG92e3MLOwXvB2RXcCWdmPjhvBMcu")
                // if defined
                .clientKey("LLv2fZnJmCBrg42oun4zXkyN6gedGoNN1snzPxG8")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
