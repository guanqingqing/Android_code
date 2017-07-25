package com.example.library;

import android.support.v4.app.Fragment;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/6/29 23:50
 */

public interface ITablayout {
    int layoutId();
    int tablayoutId();
    int framlayoutId();

    String[] tabTitles();
    int[] tabImages();
    Fragment getFragment(int position);

}
