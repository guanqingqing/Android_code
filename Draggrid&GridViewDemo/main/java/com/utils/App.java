package com.bway.project1_yuekao5.utils;

import com.andy.library.AppApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.BuildConfig;
import org.xutils.x;

public class App extends AppApplication {
    private ImageLoader mImageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        DisplayImageOptions options= new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
               .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        mImageLoader=ImageLoader.getInstance();
        mImageLoader.init(configuration);
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }
}
