package com.example.guan.project1_day2_qqlogin;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;



public class App extends Application {
	//静态代码块
//    static {
//
//    }

    //代码块
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    private UMShareAPI mUMShareAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        mUMShareAPI = UMShareAPI.get(this);
    }

    public UMShareAPI getmUMShareAPI() {
        return mUMShareAPI;
    }
}
