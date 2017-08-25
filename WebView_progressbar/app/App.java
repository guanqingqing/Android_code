package com.bway.webview_progressbar;

import android.app.Application;

import org.xutils.*;
import org.xutils.BuildConfig;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
