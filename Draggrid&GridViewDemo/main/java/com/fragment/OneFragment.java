package com.bway.project1_yuekao5.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bway.project1_yuekao5.MyChannel;
import com.bway.project1_yuekao5.R;
import com.bway.project1_yuekao5.adapter.MyAdapter;
import com.bway.project1_yuekao5.bean.Data;
import com.bway.project1_yuekao5.utils.GetNetState;
import com.bway.project1_yuekao5.utils.GlideImageLoader;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment)
public class OneFragment extends Fragment {
    @ViewInject(R.id.banner)
    private Banner banner;
    private AlertDialog dialog;
    private View view;
    @ViewInject(R.id.gridview)
    private GridView mGridView;
    private MyAdapter adapter;
    private List<Data.ResultBean.IndexProductsBean> mList;
    @ViewInject(R.id.more)
    private TextView more;
    private List<ChannelBean> listChannels;
    private String mJson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = x.view().inject(this, inflater, container);
        //刚开始做网络判断
        setNet();
        return view;
    }

    private void setNet() {
        //如果有网就获取数据
        if (GetNetState.getNetworkState(getActivity())) {
            initData();
            initView();
        } else {
            //如果没有网就通过AlertDialog的方式提示设置网络
            Toast.makeText(getActivity(), "没有网络，应用无法正常使用，请开启网络", Toast.LENGTH_SHORT).show();
            dialog();
        }
    }

    private void dialog() {
        dialog = new AlertDialog.Builder(getActivity())
                .setTitle("无网络连接")
                .setMessage("请检查你的网络连接")
                .setNegativeButton("取消", null)
                //选择设置则跳转到系统设置网络
                .setPositiveButton("设置",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(
                                        Settings.ACTION_WIFI_SETTINGS));
                            }
                        }).create();
        dialog.show();
    }

    private void initView() {
        initBanner();
        mList = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), mList);
        mGridView.setAdapter(adapter);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPindao();
            }
        });
    }

    private void initData() {
        String urlPath = "http://www.babybuy100.com/API/getShopOverview.ashx";
        // Xutils网络请求
        RequestParams params = new RequestParams(urlPath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    //解析数据
                    Gson gson = new Gson();
                    Data data = gson.fromJson(result, Data.class);
                    List<Data.ResultBean.BrandsBean> listBrands = data.getResult().getBrands();
                    //开启无限轮播
                    startBanner(listBrands);
                    //更新GridView
                    mList.addAll(data.getResult().getIndexProducts());
                    adapter.notifyDataSetChanged();
                    //初始化频道管理
                    initPinDaoData(data);
                } else {
                    onError(new NullPointerException("返回结果为空"), false);
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

    private void startBanner(List<Data.ResultBean.BrandsBean> listBrands) {
        List<String> listUrls = new ArrayList<String>();
        List<String> listTitles = new ArrayList<String>();

        for (Data.ResultBean.BrandsBean bean : listBrands) {
            //将数据放到集合里
            listUrls.add(bean.getPic());
            listTitles.add(bean.getTitle());
        }
        //Banner设置图片集合
        banner.setImages(listUrls);
        //Banner设置标题
        banner.setBannerTitles(listTitles);
        banner.start();
    }

    private void initBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    private void initPinDaoData(Data data) {
        //new一个 封装ChannelActivity中的ChannelBean集合
        listChannels = new ArrayList<>();
        //将data中获取的getNations()放入 我的频道集合(listCate)中
        List<Data.ResultBean.CategoryBean> listCate = data.getResult().getCategory();
        //将data中获取的getNations()放入 更多频道集合(listNati)中
        List<Data.ResultBean.NationsBean> listNati = data.getResult().getNations();

        //最后将两个集合循环遍历 将两个集合中的数据放入ChannelBean中 来实现频道管理中的数据
        for (Data.ResultBean.CategoryBean bean : listCate) {
            ChannelBean channelbean = new ChannelBean(bean.getName(), true);
            listChannels.add(channelbean);
        }
        for (Data.ResultBean.NationsBean bean : listNati) {
            ChannelBean channelbean = new ChannelBean(bean.getName(), false);
            listChannels.add(channelbean);
        }

    }

    //点击"更多"时调用 跳转到频道管理页面
    private void toPindao() {
        if (mJson == null) {
            MyChannel.startChannelForResult(getActivity(), listChannels, MyChannel.class);
        } else {
            MyChannel.startChannelForResult(getActivity(), mJson, MyChannel.class);
        }

    }

    //activity返回mJson
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mJson = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
    }
}
