package com.bway.guanqingqing1506b20170821;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;


public class App extends Application {
    private DbManager mManager;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        DbManager.DaoConfig config = new DbManager.DaoConfig()
                .setDbName("news")
                .setDbVersion(1);
        mManager = x.getDb(config);
    }

    public DbManager getmManager() {
        return mManager;
    }
}
