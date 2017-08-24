package com.bway.project1_yuekao5.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Banner的图片加载器
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        App app = (App) context.getApplicationContext();
        app.getmImageLoader().displayImage(path.toString(), imageView);
    }
}
