package com.example.guan.lian_manhua.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/25 20:49
 */
public class GlideImageLoader extends ImageLoader {
    public GlideImageLoader(Context context){

    }


    public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
    }




}



