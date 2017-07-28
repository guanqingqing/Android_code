package com.example.guan.guanqingqing1506b20170717;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private String urlpath="http://qhb.2dyt.com/Bwei/news?page=1&type=7&postkey=ad1AK";
    private List<Data.ListBean> mList;
    private MyAdapter adapter;
     private Handler mhandler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 //gson解析
                 Data data=new Gson().fromJson(msg.obj.toString(),Data.class);
                // 将数据添加到集合
                 mList.addAll(data.getList());
                 //刷新UI
                 adapter.notifyDataSetChanged();
             }
         };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始方法
        initView();
        initData();
}

    private void initData() {
        //开启一个线程
        new Thread(){
            @Override
            public void run() {
                //网络获取的数据用resilt接受
               String result= UrlConnection.getUrlConnect(urlpath);
                //传到handler
                 Message msg = new Message();
                  msg.what = 1;

                  msg.obj = result;
                  mhandler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        mListView= (ListView) findViewById(R.id.listview);
        mList=new ArrayList<>();
        adapter=new MyAdapter(this,mList);
        mListView.setAdapter(adapter);

        //listview的单击事件,跳转传参
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,NextActivity.class);
                intent.putExtra("title",mList.get(position).getTitle());
                intent.putExtra("data",mList.get(position).getDate());
                intent.putExtra("pic",mList.get(position).getPic());
                startActivity(intent);

            }
        });
    }
    }
