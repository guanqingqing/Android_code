package com.example.guan.project1_day2_qqlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mQQlogin;
    private ImageView mQQimage;
    private UMShareAPI umShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mQQlogin = (Button) findViewById(R.id.QQlogin);
        mQQimage= (ImageView) findViewById(R.id.QQimage);
        
		//给按钮设置点击事件
        mQQlogin.setOnClickListener(this);
		//获取App类中的mUMShareAPI
        App app = (App) getApplication();
        umShareAPI = app.getmUMShareAPI();
    }

    @Override
    public void onClick(View v) {

       //判断是否安装QQ
        if (umShareAPI.isInstall(this, SHARE_MEDIA.QQ)) {
			 //获取用户信息
            umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    if (share_media == SHARE_MEDIA.QQ){
                        if (i==0){
                            Log.e("onComplete","onComplete :登录成功");
                        }else  if (i==2){
                            Log.e("onComplete","onComplete :获取用户信息录成功");
							
							//用户信息一般是用map装,这里从map里获取用户信息
                            String name=map.get("name");
                            String imageurl=map.get("iconurl");

                            mQQlogin.setText(name);
                            Glide.with(MainActivity.this).load(imageurl).into(mQQimage);
                        }
                    }
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {

                }
            });
        } else {
            Toast.makeText(MainActivity.this, "没有安装QQ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
		//必须添加
        umShareAPI.onActivityResult(requestCode, resultCode, data);
    }
}
