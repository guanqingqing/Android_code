package com.andy.week1;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tablayout)
    private TabLayout mTabLayout;
    private List<Urls> mList;
    private Fragment [] frags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        
        initTab();

        showFragmentByPos(0);
    }

    private void initTab() {
        mList = MyApp.getInstace().getList();
        for (Urls urls : mList){
            mTabLayout.addTab(mTabLayout.newTab().setText(urls.name));
        }

        frags=new Fragment[mList.size()];
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                showFragmentByPos(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showFragmentByPos(int pos) {
        Fragment frag = getFragment(pos);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,frag)
                .commit();
    }

    private Fragment getFragment(int pos) {
        Fragment frag = frags[pos];
        if(frag==null){
            frag=MyFragment.getInstance(mList.get(pos).url);
            frags[pos]=frag;
        }
        return frag;
    }
}
