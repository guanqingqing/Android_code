package com.example.guan.lian_manhua.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guan.lian_manhua.bean.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/26 9:03
 */

public class UserDao {
    private SQLiteDatabase db;

    public UserDao(Context context) {
        SqliteUserHelper helper =new SqliteUserHelper(context);
        db=helper.getWritableDatabase();
    }

    public boolean add(String text) {
        ContentValues values = new ContentValues();
        values.put("text", text);

        long result = db.insert("user", null, values);
        // result 不等于-1 代表插入数据库成功
        if (result != -1) {
            return true;}
        else {
            return false;
        }

    }
    public List<Data.DataBean.FeedsBean.ContentBean> findAll() {
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        List<Data.DataBean.FeedsBean.ContentBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String text = cursor.getString(cursor.getColumnIndex("text"));
            Data.DataBean.FeedsBean.ContentBean user = new  Data.DataBean.FeedsBean.ContentBean();
            user.setText(text);
            list.add(user);
        }
        return list;
    }
}
