package com.carapp.application;

import android.app.Application;

import com.corelibrary.application.AppContext;

/**
 * Created by Administrator on 2017/9/28.
 */

public class CarApplication extends Application {

    private static CarApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppContext.init(this);
    }

    public static CarApplication getInstance() {
        return mInstance;
    }
}
