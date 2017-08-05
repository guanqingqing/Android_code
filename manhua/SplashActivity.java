package com.andy.week1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private DbManager mDbManager;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mDbManager = MyApp.getInstace().getDbManager();
        try {
            List<Urls> all = mDbManager.findAll(Urls.class);
            if(all==null||all.size()==0){
                Log.e("onCreate", "onCreate: 解析保存数据库");
                saveData();
            }else {
                Log.e("onCreate", "onCreate: 查询保存全局变量");
                MyApp.getInstace().setList(all);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMain();
            }
        },2000);

    }

    private void startMain() {
        Intent intent = new Intent(this,MainActivity1.class);
        startActivity(intent);
        finish();
    }

    private void saveData() {
        List<Urls> list = parseXml();
        try {
            //保存数据库
            mDbManager.save(list);
            //把集合保存全局变量
            MyApp.getInstace().setList(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * pull解析xml
     * @return
     */
    private List<Urls> parseXml() {
        List<Urls> list=new ArrayList<>();

        Urls urls = null;
        InputStream xmlStream = readXml();
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(xmlStream,"utf-8");
            int eventType = parser.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
//                        Log.e("parseXml", "parseXml: "+name );
                        if(name.equals("week")){
                            urls=new Urls();
                        }else  if(name.equals("name")){
                            urls.name = parser.nextText();
                        }else  if(name.equals("url")){
                            urls.url=parser.nextText();
                            list.add(urls);
                        }
                        break;
                }
                eventType=parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                xmlStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 读取xml文件
     * @return
     */
    private InputStream readXml() {
        try {
            InputStream in = getAssets().open("urls.xml");
            return in;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
