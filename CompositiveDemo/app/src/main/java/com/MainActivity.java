package com.bway.zonghedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;
    @ViewInject(R.id.image1)
    private ImageView leftMenuImage;
    @ViewInject(R.id.image2)
    private ImageView rightMenuImage;
    @ViewInject(R.id.tablayout)
    private TabLayout mTabLayout;
    @ViewInject(R.id.day_night)
    private Button day_night;
    @ViewInject(R.id.left)
    private ImageView left;
    @ViewInject(R.id.right)
    private ImageView right;
    @ViewInject(R.id.pindao)
    private TextView pindao;
    private App app;
    private SlidingMenu leftmenu;
    private String[] tabs;
    private String mUrl;
    private boolean isnight;
    private List<ChannelBean> mlist;
    private String jsonstr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        app = (App) getApplication();
        initView();
    }

    private void initView() {
		//获取侧滑的布局
        View left = View.inflate(this, R.layout.lift, null);
        View right = View.inflate(this, R.layout.right, null);

        leftMenuImage = (ImageView) left.findViewById(R.id.image1);
        rightMenuImage = (ImageView) right.findViewById(R.id.image2);
        day_night = (Button) left.findViewById(R.id.day_night);

		//点击左右侧滑的两个图片可以实现QQ第三方登录
        leftMenuImage.setOnClickListener(this);
        rightMenuImage.setOnClickListener(this);
		
		//点击按钮切换日夜间模式
        day_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("user_setting", Context.MODE_PRIVATE);
                isnight = sp.getBoolean("night", false);
                if (isnight) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sp.edit().putBoolean("night", false).commit();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sp.edit().putBoolean("night", true).commit();
                }
                recreate();
            }
        });
		
		//实现侧滑
        leftmenu = new SlidingMenu(this);
        leftmenu.setMenu(left);
        leftmenu.setSecondaryMenu(right);
        leftmenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置滑动菜单视图的宽度
        leftmenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        leftmenu.setFadeDegree(0.35f);
        // 设置触摸屏幕的模式
        leftmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        leftmenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


        tabs = getResources().getStringArray(R.array.tabs);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MyFragment.getInstance();
            }

            @Override
            public int getCount() {
                return tabs.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Event(value = R.id.left)
    private void leftEvent(View v) {
        if (mUrl == null) {
            if (!leftmenu.isMenuShowing())
                leftmenu.showMenu();
        }
    }

    @Event(value = R.id.pindao)
    private void pindao(View v) {
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
    @Event(value = R.id.right)
    private void rightEvent(View v) {
        if (mUrl == null) {
            if (!leftmenu.isMenuShowing())
                leftmenu.showSecondaryMenu();
        }
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(MainActivity.this, "QQdenfgl", Toast.LENGTH_SHORT).show();
        app.getUMShareAPI().getPlatformInfo(this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (share_media == SHARE_MEDIA.QQ) {
                    mUrl = map.get("iconurl");
                    if (mUrl != null) {
                        Glide.with(MainActivity.this).load(mUrl).into(leftMenuImage);
                        Glide.with(MainActivity.this).load(mUrl).into(rightMenuImage);
                    }
                }
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.e("onError", "onError: " + throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        app.getUMShareAPI().onActivityResult(requestCode, resultCode, data);
        if (requestCode==ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE){
            jsonstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            Log.e("onActivityResult","onActivityResult:"+ jsonstr);
        }

    }
}
