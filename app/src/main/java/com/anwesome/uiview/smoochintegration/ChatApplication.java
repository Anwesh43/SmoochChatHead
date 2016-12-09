package com.anwesome.uiview.smoochintegration;

import android.app.Application;

import io.smooch.core.Smooch;

/**
 * Created by anweshmishra on 09/12/16.
 */
public class ChatApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Smooch.init(this,AppConstants.SMOOCH_ID);
    }
}
