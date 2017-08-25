package com.bway.webview_progressbar.act;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bway.webview_progressbar.R;
import com.bway.webview_progressbar.bean.Data;
import com.bway.webview_progressbar.fragment.MyFragment;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 实现Tablayout和ViewPager的结合
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tablayout)
    private TabLayout mTabLayout;
    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;
    private List<Data.DataBean> mList;
    private Fragment[] frags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        lodeData();
    }

    private void lodeData() {
        //请求数据(数据为tablayout显示的数据)
        String urlPath ="http://lf.snssdk.com/neihan/service/tabs/";
        RequestParams params=new RequestParams(urlPath);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if(result!=null){
                    Gson gson=new Gson();
                    Data data = gson.fromJson(result,Data.class);
                    //调用方法
                    initTab(data.getData());
                }else {
                    onError(new NullPointerException("返回数据为空"),false);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MainActivity.this, "返回数据为空", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void initTab(final List<Data.DataBean> data) {
        if(data!=null) {
            frags = new Fragment[data.size()];
            mList=data;
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragment(position);
            }

            @Override
            public int getCount() {
                return data==null?0:data.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return data.get(position).getName();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private Fragment getFragment(int position) {
        Fragment fragment = frags[position];
        if(fragment==null){
            fragment=MyFragment.getInstance(mList.get(position).getUrl());
            frags[position]=fragment;
        }
        return fragment;
    }

}
