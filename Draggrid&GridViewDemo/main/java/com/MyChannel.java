package com.bway.project1_yuekao5;

import android.view.View;

import com.andy.library.ChannelActivity;

/**
 * 继承自封装类ChannelActivity  进行自己的设置
 */
public class MyChannel extends ChannelActivity {
    //修改频道布局 方法一
    @Override
    public void otherSetting() {
        super.otherSetting();
        //把ChannelActivity布局中的title_bar去掉
        findViewById(R.id.title_bar).setVisibility(View.GONE);
    }


    //方法二

//实现的ChannelActivity中的方法  此方法时在原来的基础上拿到布局控件进行修改
//也可以自己重写一个布局  将ChannelActivity布局复制进去  在此基础上对布局进行修改
// 然后实现layId()方法 返回值写自己重写的布局文件
//    @Override
//    public int layoutId() {
//        return R.layout.mychannel;
//    }


}
