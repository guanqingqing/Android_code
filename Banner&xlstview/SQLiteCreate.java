package com.example.dell.yuekao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLiteCreate extends SQLiteOpenHelper {
    public SQLiteCreate(Context context) {
        super(context,"User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql  = "create table users (id Integer primary key autoincrement,name varchar(20),desc varchar(50),image varchar(50))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
