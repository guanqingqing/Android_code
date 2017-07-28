package com.example.guan.guanqingqing1506b20170717;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class NextActivity extends Activity{
    private ImageView image;
    private TextView title,data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);
        initView();
    }

    private void initView() {
        image= (ImageView) findViewById(R.id.next_image);
        title= (TextView) findViewById(R.id.next_title);
        data= (TextView) findViewById(R.id.next_dada);

        //获得意图
        Intent intent = getIntent();
        //读取数据
        String t = intent.getStringExtra("title");
        String d = intent.getStringExtra("data");
        String i = intent.getStringExtra("pic");

        //将数据设置到布局
        title.setText(t);
        data.setText(d);
        //Glide加载图片
        Glide.with(NextActivity.this).load(i).into(image);
    }

}
