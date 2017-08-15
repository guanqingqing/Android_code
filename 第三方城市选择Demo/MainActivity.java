package com.bway.project1_cityentry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.city_picker.CityListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btncity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncity= (Button) findViewById(R.id.city);
    }

    @Override
    public void onClick(View v) {
        CityListActivity.startCityActivityForResult(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CityListActivity.REQUEST_CODE&&resultCode==CityListActivity.RESULT_CODE){
            String city=data.getStringExtra(CityListActivity.RESULT_KEY);
            btncity.setText(city);
        }
    }
}
