package com.bway.fragment.week2;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/8/12 8:52
 */

public class App extends Application {

    private DbManager mManager;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
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
