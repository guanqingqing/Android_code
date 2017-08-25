package com.bway.guanqingqing1506b20170821.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bway.guanqingqing1506b20170821.R;
import com.example.library.BaseTablayoutActivity;

import java.util.ArrayList;
import java.util.List;

import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;
import fragment.Fragment5;
import fragment.Fragment6;

public class MainActivity extends BaseTablayoutActivity {
    private ImageView jia;
    private List<ChannelBean> mlist;
    private String jsonstr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //点击加号  管理频道
        jia= (ImageView) findViewById(R.id.jia);
        jia.setOnClickListener(new View.OnClickListener() {
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
                    ChannelActivity.startChannelActivity(MainActivity.this,mlist);
                }else if (jsonstr!=null){
                    ChannelActivity.startChannelActivity(MainActivity.this,jsonstr);
                }
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int tablayoutId() {
        return R.id.tablayout;
    }

    @Override
    public int framlayoutId() {
        return R.id.framelayout;
    }

    @Override
    public String[] tabTitles() {
        return new String[]{"推荐", "热点", "杭州", "时尚", "科技", "政治"};
    }

    @Override
    public int[] tabImages() {
        return new int[0];
    }

    @Override
    public Fragment getFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
            case 3:
                fragment = new Fragment4();
                break;
            case 4:
                fragment = new Fragment5();
                break;
            case 5:
                fragment = new Fragment6();
                break;

            default:
                break;

        }
        return fragment;
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
