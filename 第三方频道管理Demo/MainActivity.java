package com.bway.draggrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<ChannelBean> mlist;
    private String jsonstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        if (mlist == null) {
            mlist=new ArrayList<>();
            mlist.add(new ChannelBean("推荐",true));
            mlist.add(new ChannelBean("军事",true));
            mlist.add(new ChannelBean("北京",true));
            mlist.add(new ChannelBean("图片",false));
            mlist.add(new ChannelBean("音乐",false));
            mlist.add(new ChannelBean("娱乐",false));
            mlist.add(new ChannelBean("热点",false));
            mlist.add(new ChannelBean("汽车",false));
            mlist.add(new ChannelBean("美女",false));
            mlist.add(new ChannelBean("国际",false));
            mlist.add(new ChannelBean("健康",false));
            mlist.add(new ChannelBean("体育",false));
            mlist.add(new ChannelBean("科学",false));
            ChannelActivity.startChannelActivity(this,mlist);
        }else if (jsonstr!=null){
            ChannelActivity.startChannelActivity(this,jsonstr);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE){
            jsonstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            Log.e("onActivityResult","onActivityResult:"+ jsonstr);
        }

    }
}
