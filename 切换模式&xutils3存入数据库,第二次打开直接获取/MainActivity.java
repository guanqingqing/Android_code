package com.bway.fragment.week2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    private String urlpath = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private List<DataBean> mList;
    private MyAdapter adapter;
    @ViewInject(R.id.xlistviewe)
    private XListView mXListView;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.image)
    private ImageView image;
    private DbManager mDbManager;
    private boolean isnight;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initView();
        initData();
    }

    private void initView() {
        mList = new ArrayList<>();
        adapter = new MyAdapter(this, mList);
        mXListView.setAdapter(adapter);

        App app = (App) getApplication();
        mDbManager = app.getmManager();

        //XListView设置
        mXListView.setPullLoadEnable(true);
        mXListView.setXListViewListener(this);
    }

    private void initData() {
        try {
            //分页加载
            List<DataBean> lists = mDbManager.selector(DataBean.class).where("page", "=", page).findAll();
            if (mList != null && mList.size() > 0) {
                mList.addAll(lists);
                adapter.notifyDataSetChanged();
                stopxlistview();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        String url = urlpath + page;
        RequestParams params = new RequestParams(url);
        //xutils网络模块
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析数据
                Data data = new Gson().fromJson(result, Data.class);
                //遍历数据加上page
                for (DataBean bean : data.getData()) {
                    bean.setPage(page);
                }
                try {
                    //存入数据库
                    mDbManager.save(data.getData());
                    Toast.makeText(MainActivity.this, "数据库保存成功", Toast.LENGTH_SHORT).show();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                mList.addAll(data.getData());
                adapter.notifyDataSetChanged();
                stopxlistview();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //点击切换模式按钮,响应时间,切换日夜间模式
    @Event(value = R.id.day)
    private void onclick(View view) {
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

    @Override
    public void onRefresh() {
        page = 1;
        mList.clear();
        initData();
        stopxlistview();
    }

    private void stopxlistview() {
        mXListView.stopRefresh();
        mXListView.stopLoadMore();
        mXListView.setRefreshTime("刚刚");

    }

    @Override
    public void onLoadMore() {
        page++;
        initData();
        stopxlistview();
    }
}
