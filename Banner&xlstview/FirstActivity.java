package com.example.dell.yuekao;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;



public class FirstActivity extends AppCompatActivity {
    int i = 3;
    TextView textView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(i<0){
                startActivity(new Intent(FirstActivity.this,MainActivity.class));
                finish();
            }else{
                textView.setText(i+"秒");
                i--;
                handler.sendEmptyMessageDelayed(99,1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fristactivity);
        textView=(TextView)findViewById(R.id.firsttext);
        handler.sendEmptyMessageDelayed(99,1000);
    }
}
