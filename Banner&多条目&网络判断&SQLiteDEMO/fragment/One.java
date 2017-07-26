package com.example.guan.lian_manhua.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guan.lian_manhua.bean.Data;
import com.example.guan.lian_manhua.bean.DataBanner;
import com.example.guan.lian_manhua.utils.GetNetState;
import com.example.guan.lian_manhua.utils.GlideImageLoader;
import com.example.guan.lian_manhua.adapter.MyAdapter;
import com.example.guan.lian_manhua.R;
import com.example.guan.lian_manhua.utils.UrlConnection;
import com.example.guan.lian_manhua.db.UserDao;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

import static com.example.guan.lian_manhua.utils.UrlConnection.getUrlConnect;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/24 20:28
 */
public class One extends  Fragment implements XListView.IXListViewListener {
    private View view;
    private  String result1;
    private Banner banner;
    private AlertDialog dialog;
    private MyAdapter adapter;
    private List<Data.DataBean.FeedsBean> mList;
    private List<DataBanner.BannerBean> listBanner;
    private List<String> list;
    private XListView xlistview;
    private String urlBanner="http://dota2xufserver.duapp.com/api/v1.0/news/refresh";
    private String urlpath="http://api.kkmh.com/v1/feeds/feed_lists?uid=&since=0&page_num=1&catalog_type=2&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODUzMzcwOTg2LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIiRsaWJfdmVyc2lvbiI6IjEuNi4zNCIsIiRuZXR3b3JrX3R5cGUiOiJXSUZJIiwiJHdpZmkiOnRydWUsIkZyb21WQ29tbXVuaXR5VGFiTmFtZSI6IueDremXqCIsIiRtYW51ZmFjdHVyZXIiOiJzYW1zdW5nIiwiJHNjcmVlbl9oZWlnaHQiOjEwMjQsIkhvbWVwYWdlVXBkYXRlRGF0ZSI6MCwiUHJvcGVydHlFdmVudCI6IlJlYWRWQ29tbXVuaXR5IiwiRmluZFRhYk5hbWUiOiLmjqjojZAiLCJhYnRlc3RfZ3JvdXAiOjcwLCIkc2NyZWVuX3dpZHRoIjo1NzYsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IlZDb21tdW5pdHlQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6MzA1NDA0NzkzMzIzNTEyMyIsIm9yaWdpbmFsX2lkIjoiQTozMDU0MDQ3OTMzMjM1MTIzIiwiZXZlbnQiOiJSZWFkVkNvbW11bml0eSJ9";
    private Handler mhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //判断msg.what的值,进行不同的操作
                //what==1的时候,解析列表的数据

                if (msg.what==1){
                    Data data=new Gson().fromJson(msg.obj.toString(),Data.class);
                    mList.addAll(data.getData().getFeeds());
                    adapter.notifyDataSetChanged();
                    stopxlistview();

                }else if (msg.what==2){
                  //what==2的时候,解析轮播的数据
                  DataBanner data1=new Gson().fromJson(msg.obj.toString(),DataBanner.class);
                  listBanner.addAll(data1.getBanner());
                    for (DataBanner.BannerBean bean:listBanner  ) {
                        //给轮播(Banner)设置图片集合的时候需要用到一个集合,此时初始一个list集合,把图片url装到list集合里
                        list.add(bean.getBackground());
                    }
                    //设置图片集合
                    banner.setImages(list);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                }
            }
        };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.one,null,false);
        //判断网络状态
        //当有网的时候调用方法

        if(GetNetState.getNetworkState(getActivity())){
              //调用初始化控件的方法
               initView();
            //调用初始化列表数据的方法(因为这里用了两个接口,所以初始化轮播的数据在下面方法中)
               initData();
            //调用初始化轮播数据的方法
               initDataBanner();
            //调用数据库操作的方法
               initDB();
        }else {
            //当无网的时候弹出Dialog,设置网络
            Toast.makeText(getActivity(),"没有网络，应用无法正常使用，请进行开启数据",Toast.LENGTH_SHORT).show();
            dialog = new AlertDialog.Builder(getActivity())
                    .setTitle("无网络连接")
                    .setMessage("请检查你的网络连接")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("设置",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    startActivity(new Intent(
                                            Settings.ACTION_WIFI_SETTINGS));
                                }
                            }).create();

            dialog.show();
        }

        return view;
    }

    private void initDB() {
        UserDao dao=new UserDao(getActivity());
       boolean add= dao.add(result1);
        if (add){
             Toast.makeText(getActivity(),"数据库插入成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(),"数据库插入失败",Toast.LENGTH_SHORT).show();
        }
       List<Data.DataBean.FeedsBean.ContentBean> findall= dao.findAll();
        if (findall.size()>0){
            Toast.makeText(getActivity(),"数据库查询成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(),"数据库查询失败",Toast.LENGTH_SHORT).show();
        }
    }

    private void initDataBanner() {
        new Thread(){
            @Override
            public void run() {
            String result2= UrlConnection.getUrlConnect(urlBanner);
                 Message msg = new Message();
                  msg.what = 2;
                  msg.obj = result2;
                  mhandler.sendMessage(msg);
            }
        }.start();
    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                result1= getUrlConnect(urlpath);
                 Message msg = new Message();
                  msg.what = 1;
                  msg.obj = result1;
                  mhandler.sendMessage(msg);
            }
        }.start();
    }
    //停止xlistview是所抽取的方法(在轮播的Handler中调用)
    private void stopxlistview() {
        xlistview.stopRefresh();
        xlistview.stopLoadMore();
        xlistview.setRefreshTime("刚刚");

    }
    private void initView() {
        //初始化集合
        mList=new ArrayList<>();
        listBanner=new ArrayList<>();
        list=new ArrayList<>();

        //找到控件
        xlistview= (XListView) view.findViewById(R.id.xlistview);
        banner= (Banner) view.findViewById(R.id.banner);
        //初始化适配器
        adapter=new MyAdapter(getActivity(),mList);
        //给xlistview设置适配器
        xlistview.setAdapter(adapter);

        xlistview.setPullLoadEnable(true);//上拉刷新
        // xListView.setPullRefreshEnable(true);//下拉刷新（可以不设）
        //xlistview设置监听
        xlistview.setXListViewListener(this);
        //调用设置Banner的方法
        setBanner();

    }
    private void setBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader(getActivity()));
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ScaleInOut);
        banner.setBannerTitles(list);

    }

    //下拉刷新
    @Override
    public void onRefresh() {
        mList.clear();
        initData();
    }
    //上拉加载更多
    @Override
    public void onLoadMore() {
    initData();
    }
}
