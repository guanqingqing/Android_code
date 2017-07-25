package com.example.guan.lian_manhua;

import android.support.v4.app.Fragment;

import com.example.library.BaseTablayoutActivity;
//这里继承的是自己封装的一个library,实现他的方法(包已经放进来)
public class MainActivity extends BaseTablayoutActivity{


    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }
    //返回tablayout的布局
    @Override
    public int tablayoutId() {
        return R.id.tablayout;
    }
    //返回framlayout的布局
    @Override
    public int framlayoutId() {
        return R.id.framelayout;
    }
    //给底部的导航栏设置标题
    @Override
    public String[] tabTitles() {
        return new String[]{"漫画","发现","我的"};
    }
    //给底部的导航栏设置图片
    @Override
    public int[] tabImages() {
		//这里是一个int集合,用于放底部导航栏的图片
        return new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    }
    //初始化了几个Fragment   
	//这样就实现了Tablayout与Fragment结合的底部导航栏,具体每个Fragment的功能再具体实现,这里只是加载了Fragment的布局
    @Override
    public Fragment getFragment(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new One();
                break;
            case 1:
                fragment=new Two();
                break;
            case 2:
                fragment=new Three();
                break;
            default:
                break;
        }
        return fragment;
    }
}
