package com.example.dell.yuekao;

import android.app.Application;

import com.nostra13.universalimageloader.*;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import org.xutils.x;



public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initImage();
        initUtils();
    }

    public void initImage() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .build();
        ImageLoaderConfiguration imageloader = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(imageloader);
    }

    private void initUtils() {
        x.Ext.init(this);
    }
}
