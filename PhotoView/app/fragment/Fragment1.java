package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bway.guanqingqing1506b20170821.App;
import com.bway.guanqingqing1506b20170821.activity.NextActivity;
import com.bway.guanqingqing1506b20170821.R;
import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import data.ComicsBean;
import data.Data;
import me.maxwin.view.XListView;


public class Fragment1 extends Fragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    private XListView mListView;
    private DbManager mDbManager;
    private List<ComicsBean> mList;
    private String urlpath = "http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3NzQyMjQwNjE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjEzIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6ImJpZ25veCIsIkZyb21Ib21lcGFnZVVwZGF0ZURhdGUiOjAsIiRzY3JlZW5faGVpZ2h0IjoxMjgwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6MTEsIiRzY3JlZW5fd2lkdGgiOjcyMCwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNoaW5hIE1vYmlsZSIsIiRtb2RlbCI6IlZQaG9uZSIsIiRhcHBfdmVyc2lvbiI6IjMuNi4yIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo2YWRkYzdhZTQ1MjUwMzY1Iiwib3JpZ2luYWxfaWQiOiJBOjZhZGRjN2FlNDUyNTAzNjUiLCJldmVudCI6IlJlYWRIb21lUGFnZSJ9";
    private View view;
    private MyAdapter adapter;
    private int i = 0;
    private int j = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, null, false);
        App app = (App) getActivity().getApplication();
        mDbManager = app.getmManager();
        initView();
        //判断数据库里面的内容
        try {
            List<ComicsBean> list = mDbManager.findAll(ComicsBean.class);
            if (list != null && list.size() > 0) {
                mList.addAll(list);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "数据库", Toast.LENGTH_LONG).show();
            } else {
                initData();
                Toast.makeText(getActivity(), "网络", Toast.LENGTH_LONG).show();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void initData() {
        RequestParams params = new RequestParams(urlpath);
        params.addBodyParameter("offset", i + "");
        params.addBodyParameter("limit", j + "");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Data data = gson.fromJson(result, Data.class);
                mList.addAll(data.getData().getComics());
                adapter.notifyDataSetChanged();
                //  存入数据库
                try {
                    mDbManager.save(data.getData());
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "数据库保存成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        mList = new ArrayList<>();
        mListView = (XListView) view.findViewById(R.id.xlistview);
        adapter = new MyAdapter(getActivity(), mList);
        mListView.setAdapter(adapter);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        //点击条目
        mListView.setOnItemClickListener(this);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                        stopXlistview();
                    }
                });
                initData();
            }
        }.start();


    }

    private void stopXlistview() {
        mListView.stopLoadMore();
        mListView.stopRefresh();
    }

    //上拉加载更多
    @Override
    public void onLoadMore() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                        stopXlistview();
                    }
                });
                initData();
            }
        }.start();

    }

    //点击条目跳转  在NextActivity中将条目中图片放大缩小(使用PhotoView)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = mList.get(position - 1).getCover_image_url();
        Intent intent = new Intent(getActivity(), NextActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
