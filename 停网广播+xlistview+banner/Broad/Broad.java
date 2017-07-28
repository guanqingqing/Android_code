package com.example.guan.guanqingqing1506b20170728.Broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;



public class Broad extends BroadcastReceiver{
        AlertDialog.Builder builder;
        @Override
        public void onReceive(final Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (!networkInfo.isConnected()){
                Toast.makeText(context,"WIFI已经断开",Toast.LENGTH_SHORT).show();
                builder = new AlertDialog.Builder(context);
                builder.setTitle("当前不是wifi网络是否去设置");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        context.startActivity(it);
                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        }
}
