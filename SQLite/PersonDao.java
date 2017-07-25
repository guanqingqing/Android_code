package com.example.day3_teshu.dao;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.day3_teshu.SQLitePersonDbHelper;

public class PersonDao {
	private SQLiteDatabase db;

	public PersonDao(Context context) {
		SQLitePersonDbHelper helper = new SQLitePersonDbHelper(context);
		db = helper.getWritableDatabase();
	}

	public boolean add(String name, String phone) {
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("phone", phone);
		long result = db.insert("user", null, values);
		// result 不等于-1 代表插入数据库成功
		if (result != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 使用特殊方式修改一条数据记录
	 * 
	 * @param id
	 *            修改的条件值 1
	 * @param name
	 *            修改的具体值 姓名
	 * @return
	 */
	public boolean updete(int id, String name) {
		/**
		 * 参数1：表名 参数2：修改值 参数3：修改的条件 参数4：修改的条件值
		 */
		ContentValues values = new ContentValues();
		values.put("name", name);
		int result = db.update("user", values, "id=?",
				new String[] { String.valueOf(id) });
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 使用特殊的方式删除一条数据记录
	 * 
	 * @param id
	 *            删除的条件
	 * @return true 成功 false 失败
	 */
	public boolean delete(int id) {
		/**
		 * 参数1：表明 参数2：删除的条件 id 参数3：删除条件值 1
		 */
		int result = db.delete("user", "id=?",
				new String[] { String.valueOf(id) });
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String find(int id) {
		/**
		 * 参数1：表名 参数2：你要查询的列 * name new String[]{"name","phone"} 指定你要查询的列
		 * 参数3：查询条件 参数4：查询条件值 参数5：groupby 是否分组查询 参数6：having 子查询 参数7：是否排序 排序：
		 * 升序/降序
		 */
		Cursor cursor = db.query("user", null, "id=?",
				new String[] { String.valueOf(id) }, null, null, null);
		StringBuffer sb = new StringBuffer();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			sb.append("姓名：     " + name + "电话：   " + phone);
		}
		return sb.toString();
	}

	public List<User> findAll() {
		Cursor cursor = db.query("user", null, null, null, null, null, null);
		List<User> list = new ArrayList<User>();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));

			User user = new User();
			user.setName(name);
			user.setPhone(phone);

			list.add(user);
		}
		return list;
	}
}
