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
		// result ������-1 ����������ݿ�ɹ�
		if (result != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ʹ�����ⷽʽ�޸�һ�����ݼ�¼
	 * 
	 * @param id
	 *            �޸ĵ�����ֵ 1
	 * @param name
	 *            �޸ĵľ���ֵ ����
	 * @return
	 */
	public boolean updete(int id, String name) {
		/**
		 * ����1������ ����2���޸�ֵ ����3���޸ĵ����� ����4���޸ĵ�����ֵ
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
	 * ʹ������ķ�ʽɾ��һ�����ݼ�¼
	 * 
	 * @param id
	 *            ɾ��������
	 * @return true �ɹ� false ʧ��
	 */
	public boolean delete(int id) {
		/**
		 * ����1������ ����2��ɾ�������� id ����3��ɾ������ֵ 1
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
		 * ����1������ ����2����Ҫ��ѯ���� * name new String[]{"name","phone"} ָ����Ҫ��ѯ����
		 * ����3����ѯ���� ����4����ѯ����ֵ ����5��groupby �Ƿ�����ѯ ����6��having �Ӳ�ѯ ����7���Ƿ����� ����
		 * ����/����
		 */
		Cursor cursor = db.query("user", null, "id=?",
				new String[] { String.valueOf(id) }, null, null, null);
		StringBuffer sb = new StringBuffer();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			sb.append("������     " + name + "�绰��   " + phone);
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
