package com.example.guan.guanqingqing1506b20170728;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.guan.guanqingqing1506b20170728.Broad.Broad;
import com.example.guan.guanqingqing1506b20170728.adapter.MyAdapter;
import com.example.guan.guanqingqing1506b20170728.bean.Data;
import com.example.guan.guanqingqing1506b20170728.utils.GetNetState;
import com.example.guan.guanqingqing1506b20170728.utils.GlideImageLoader;
import com.example.guan.guanqingqing1506b20170728.utils.UrlConnevtion;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;


public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    private XListView xlistview;
    private Banner banner;
    private String urlpath="http://qhb.2dyt.com/Bwei/news?page=1&type=5&postkey=ff1d1AK";
    private List<Data.ListBean> mlist;
    private List<String> listviewpager;
    private MyAdapter adapter;
    private Broad myRecevier;
    private AlertDialog alertDialog;
     private Handler mhandler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 Data data=new Gson().fromJson(msg.obj.toString(),Data.class);
                 mlist.addAll(data.getList());
                 listviewpager.addAll(data.getListViewPager());
                 //设置图片集合
                 banner.setImages(listviewpager);
                 //banner设置方法全部调用完毕时最后调用
                 banner.start();
                 adapter.notifyDataSetChanged();

             }
         };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(GetNetState.getNetworkState(this)){
             initView();
             initData();
             registmyRecevier();
        }
    }
    //注册广播的方法
    private void registmyRecevier() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myRecevier = new Broad();
        this.registerReceiver(myRecevier,filter);
    }
    //请求数据的方法
    private void initData() {
        new Thread(){
            @Override
            public void run() {
              String result= UrlConnevtion.getUrlConnect(urlpath);
                 Message msg = new Message();
                  msg.what = 1;
                  msg.obj = result;
                  mhandler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        mlist=new ArrayList<>();
        listviewpager=new ArrayList<>();
        xlistview= (XListView) findViewById(R.id.xlistview);
        banner= (Banner) findViewById(R.id.banner);

        xlistview.setPullLoadEnable(true);
        xlistview.setXListViewListener(this);
        xlistview.setOnItemClickListener(this);

        adapter=new MyAdapter(this,mlist);
        xlistview.setAdapter(adapter);
        setBanner();
    }
    //设置轮播的方法
    private void setBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader(this));
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ScaleInOut);
        banner.setBannerTitles(listviewpager);
    }

    //下拉刷新
         @Override
         public void onRefresh() {
             new Thread(){
                 @Override
                 public void run() {
                     try {
                         sleep(1000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             mlist.clear();
                             Toast.makeText(MainActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();
                             stopXlistview();
                         }
                     });
                    initData();
                 }
             }.start();


         }
         //停止xlistview的方法
         private void stopXlistview() {
             xlistview.stopLoadMore();
             xlistview.stopRefresh();
         }

         //上拉加载更多
         @Override
         public void onLoadMore() {
             new Thread(){
                 @Override
                 public void run() {
                     try {
                         sleep(1000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             Toast.makeText(MainActivity.this,"加载成功",Toast.LENGTH_SHORT).show();
                             stopXlistview();
                         }
                     });
                     initData();
                 }
             }.start();

         }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        this.unregisterReceiver(myRecevier);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // 1.未在5秒内对用户输入事件响应
       // 2.BroadcastReceiver未在10秒内执行完

        alertDialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("应用程序无响应")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              finish();
                            }
                        }).create();

        alertDialog.show();
    }
}
