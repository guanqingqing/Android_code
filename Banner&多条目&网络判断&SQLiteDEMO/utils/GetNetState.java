package com.example.guan.lian_manhua.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/25 19:30
 */
public class GetNetState {

            //获取当前的网络状态是否可用
            public static boolean getNetworkState(Context context){
                //获得手机所有连接管理对象
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                //获得NetworkInfo对象
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
                //遍历每一个对象
                for (NetworkInfo networkInfo :networkInfos){
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED){
                        // debug信息
                        Toast.makeText(context,"TypeName = " + networkInfo.getTypeName(),Toast.LENGTH_SHORT).show();
                        //网络可用
                        return true;
                    }
                }
                //网络不可用
                return false;
            }
    }

