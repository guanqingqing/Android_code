package com.bway.zonghedemo;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;


public class App extends Application {
    private UMShareAPI mUMShareAPI;
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        mUMShareAPI = UMShareAPI.get(this);
    }
    public UMShareAPI getUMShareAPI() {
        return mUMShareAPI;
    }
}
