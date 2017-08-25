package com.bway.webview_progressbar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bway.webview_progressbar.R;
import com.bway.webview_progressbar.act.WebActivity;
import com.bway.webview_progressbar.adapter.MyAdapter;
import com.bway.webview_progressbar.bean.ListData;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;


@ContentView(R.layout.fragment)
public class MyFragment extends Fragment implements XListView.IXListViewListener {
    @ViewInject(R.id.xlistview)
    protected XListView lv;
    private String urlpath;
    private View view;
    private MyAdapter adapter;
    private List<ListData.DataBeanX.DataBean> mlist;

    public static MyFragment getInstance(String url) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = x.view().inject(this, inflater, container);
        initView();
        loadData();
        return view;

    }

    private void initView() {
        mlist = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), mlist);
        lv.setAdapter(adapter);
        //xlistview的一些设置
        lv.setPullLoadEnable(true);
        lv.setXListViewListener(this);
        //条目点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //判断不为空且有数据
                if (mlist != null && position <= mlist.size() && position > 0)
                    //调用方法(参数是posytion-1)
                    toWebView(position - 1);
            }
        });
    }

    private void toWebView(int position) {
        //获取url
        String url = mlist.get(position).getGroup().getShare_url();
        Intent intent = new Intent(getActivity(), WebActivity.class);
        //将url传到webActivity中
        intent.putExtra("url", url);
        //跳转
        startActivity(intent);
    }

    private void loadData() {
        //请求数据
        urlpath = getArguments().getString("url");
        RequestParams params = new RequestParams(urlpath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    ListData data = new Gson().fromJson(result, ListData.class);
                    mlist.addAll(data.getData().getData());
                    adapter.notifyDataSetChanged();

                    Log.e("onSuccess", "onSuccess: " + result);
                } else {
                    onError(new NullPointerException("返回数据为空"), false);
                }
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

    //下拉刷新
    @Override
    public void onRefresh() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //在fragment中要用geteActivity()   然后用runOnUiThread更改UI
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mlist.clear();
                        Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                        stopXlistview();
                    }
                });
                loadData();
            }
        }.start();


    }

    //xlistview停止的方法
    private void stopXlistview() {
        lv.stopLoadMore();
        lv.stopRefresh();
    }

    //上拉加载更多
    @Override
    public void onLoadMore() {

    }
}
