package com.example.day3_teshu;

import java.util.List;

import com.example.day3_teshu.dao.PersonDao;
import com.example.day3_teshu.dao.User;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button mInsert, mDelete, mUpdate, mFind, mFindALL;
	private PersonDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dao = new PersonDao(this);

		mDelete = (Button) findViewById(R.id.delete);
		mInsert = (Button) findViewById(R.id.insert);
		mUpdate = (Button) findViewById(R.id.update);
		mFind = (Button) findViewById(R.id.select);
		mFindALL = (Button) findViewById(R.id.findall);

		mDelete.setOnClickListener(this);
		mInsert.setOnClickListener(this);
		mUpdate.setOnClickListener(this);
		mFind.setOnClickListener(this);
		mFindALL.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.insert:
			boolean result = dao.add("zhangsan", "123");
			if (result) {
				Toast.makeText(this, "Ìí¼Ó³É¹¦", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "Ìí¼ÓÊ§°Ü", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.delete:
			boolean result1 = dao.delete(1);
			if (result1) {
				Toast.makeText(this, "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "É¾³ýÊ§°Ü", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.update:
			boolean result2 = dao.updete(2, "guan");
			if (result2) {
				Toast.makeText(this, "ÐÞ¸Ä³É¹¦", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "ÐÞ¸ÄÊ§°Ü", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.select:
			String result3 = dao.find(3);
			if (result3!="") {
				Toast.makeText(this, result3, Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "²éÑ¯Ê§°Ü", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.findall:
			List<User> result4 = dao.findAll();
			if (result4.size()>0) {
				Toast.makeText(this, result4.toString(), Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, "²éÑ¯Ê§°Ü", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}

}
