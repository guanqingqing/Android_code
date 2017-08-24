package com.bway.project1_yuekao5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bway.project1_yuekao5.fragment.OneFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.bottom_tab_bar)
    private BottomTabBar mTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initTab();
    }

    private void initTab() {
        mTabBar.init(getSupportFragmentManager())
                .addTabItem("漫画", R.mipmap.ic_launcher, OneFragment.class)
                .addTabItem("发现", R.mipmap.ic_launcher, OneFragment.class)
                .addTabItem("V社区", R.mipmap.ic_launcher, OneFragment.class)
                .addTabItem("我的", R.mipmap.ic_launcher, OneFragment.class);
    }
}

