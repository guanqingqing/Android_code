package com.example.guan.lian_manhua.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/26 9:01
 */

public class SqliteUserHelper extends SQLiteOpenHelper{
    public SqliteUserHelper(Context context) {
        super(context, "user", null, 1);
        // TODO Auto-generated constructor stub
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  user (id Integer primary key autoincrement, text varchar(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
