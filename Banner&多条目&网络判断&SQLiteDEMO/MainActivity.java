package com.example.guan.lian_manhua;

import android.support.v4.app.Fragment;

import com.example.guan.lian_manhua.fragment.One;
import com.example.guan.lian_manhua.fragment.Three;
import com.example.guan.lian_manhua.fragment.Two;
import com.example.library.BaseTablayoutActivity;

public class MainActivity extends BaseTablayoutActivity{


    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int tablayoutId() {
        return R.id.tablayout;
    }

    @Override
    public int framlayoutId() {
        return R.id.framelayout;
    }

    @Override
    public String[] tabTitles() {
        return new String[]{"漫画","发现","我的"};
    }

    @Override
    public int[] tabImages() {
        return new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    }

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
