package com.andy.week1;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.List;


public class MyApp extends Application{

    private DbManager mDbManager;
    private List<Urls> mList;

    private static MyApp sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        DbManager.DaoConfig config = new DbManager.DaoConfig()
                .setDbName("week1")
                .setDbVersion(1)
                .setAllowTransaction(true);

        mDbManager = x.getDb(config);
        sApp=this;
    }

    public DbManager getDbManager() {
        return mDbManager;
    }

    public List<Urls> getList() {
        return mList;
    }

    public void setList(List<Urls> list) {
        mList = list;
    }

    public static  MyApp getInstace(){
        return sApp;
    }
}
