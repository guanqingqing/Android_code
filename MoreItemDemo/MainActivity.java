package com.example.guan.guanqingqing1506b20170724;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**主页面
 * guanqingqing
 * 20170724
 */
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    private XListView xlistview;
    private String urlpath="http://qhb.2dyt.com/Bwei/news?type=5&postkey=ad1AK";
    private List<Data.ListBean> mList;
    private MyAdapter adapter;
    //handler发送消息
     private Handler mhandler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 Data data=new Gson().fromJson(msg.obj.toString(),Data.class);
                 mList.addAll(data.getList());
                 adapter.notifyDataSetChanged();
                 stopxlistview();
             }
         };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //调用方法
        initView();
        initData();

    }
    //停止xlistview的方法
   private void stopxlistview() {
        xlistview.stopRefresh();
        xlistview.stopLoadMore();
        xlistview.setRefreshTime("刚刚");

    }
    //开启线程获取网络数据
    private void initData() {
        new Thread(){
            @Override
            public void run() {
              String result=  UrlConnection.getUrlConnect(urlpath);
                 Message msg = new Message();
                  msg.what = 1;
                  msg.obj = result;
                  mhandler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        xlistview= (XListView) findViewById(R.id.xlistview);
        mList=new ArrayList<>();
        adapter=new MyAdapter(this,mList);
        xlistview.setAdapter(adapter);
        xlistview.setPullLoadEnable(true);
        xlistview.setXListViewListener(this);
    }

    @Override
    public void onRefresh(){
        mList.clear();
        initData();
    }

    @Override
    public void onLoadMore() {
     initData();
    }
}
