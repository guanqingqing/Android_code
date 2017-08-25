package com.bway.guanqingqing1506b20170821.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bway.guanqingqing1506b20170821.myview.HackyViewPager;
import com.bway.guanqingqing1506b20170821.R;

import uk.co.senab.photoview.PhotoView;

/**
 * 放大图片的类
 */

public class NextActivity extends AppCompatActivity {
    private HackyViewPager hvpPhoto;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextactivity);
        initView();

    }

    private void initView() {
        hvpPhoto= (HackyViewPager) findViewById(R.id.Photo);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        hvpPhoto.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView photoView=new PhotoView(container.getContext());
                Glide.with(container.getContext()).load(url).into(photoView);
                container.addView(photoView);
                return photoView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);

            }
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });
    }
}
