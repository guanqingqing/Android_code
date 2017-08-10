package com.bway.fragment.lian_qqlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.share.QQOauthUtils;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private QQOauthUtils mQQOauthUtils;
    private TextView name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image= (ImageView) findViewById(R.id.image);
        name1 = (TextView) findViewById(R.id.name);
        mQQOauthUtils=new QQOauthUtils(this, new QQOauthUtils.IUserPhoto() {
            @Override
            public void userPhoto(String userPhoto,String name) {
                Glide.with(MainActivity.this).load(userPhoto).into(image);
                name1.setText(name);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mQQOauthUtils.onActivityResult(requestCode,resultCode,data);
    }
    public void but(View v) {
        mQQOauthUtils.qqLogin();
    }
}
