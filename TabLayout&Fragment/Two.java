package com.example.guan.lian_manhua;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/24 20:28
 */
public class Two extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.two,null,false);
        return view;
    }
}
