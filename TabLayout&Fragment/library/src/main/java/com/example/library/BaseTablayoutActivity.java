package com.example.library;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/6/29 23:46
 */

public abstract class BaseTablayoutActivity extends AppCompatActivity implements ITablayout{
    private FrameLayout mFramlayout;
    private TabLayout mTablayout;
    private Fragment[] mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutId()!=0){
            setContentView(layoutId());

            initView();
        }else {
            Log.e("onCreate","onCreate:layoutId没有返回布局ID");
        }

    }

    private void initView() {
        mTablayout= (TabLayout) findViewById(tablayoutId());
        mFramlayout= (FrameLayout) findViewById(framlayoutId());

        if (tabTitles()!=null){
            mFragments=new Fragment[tabTitles().length];
            for (int i = 0;i<tabTitles().length;i++){
              TabLayout.Tab tab=mTablayout.newTab();
                tab.setText(tabTitles()[i]);
                if (tabImages()!=null&&i<tabImages().length){
                    tab.setIcon(tabImages()[i]);
                }
                mTablayout.addTab(tab);
            }
        }

        showFragment(0);

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                showFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showFragment(int position) {
        Fragment fragment=mFragments[position];
        if (fragment==null){

            fragment=getFragment(position);
            mFragments[position]=fragment;
        }

        getSupportFragmentManager().beginTransaction().replace(framlayoutId(),fragment).commit();

    }

}
