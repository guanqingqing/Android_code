package com.andy.week1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.frag)
public class MyFragment extends Fragment {

    @ViewInject(R.id.tv)
    private TextView tv;

    @ViewInject(R.id.lv)
    private ListView lv;

    private String urlPath;

    private List<Data.DataBean.ComicsBean> list;
    private MyAdapter adapter;

    public static MyFragment getInstance(String urlPath) {
        MyFragment frag = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", urlPath);
        frag.setArguments(bundle);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        urlPath = getArguments().getString("url");
        tv.setText(urlPath);
        list=new ArrayList<>();
        adapter=new MyAdapter();
        lv.setAdapter(adapter);
        loadData();
        return view;
    }

    private void loadData() {
        if (urlPath == null)
            return;
        RequestParams params = new RequestParams(urlPath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new GsonBuilder().create();
                Data data = gson.fromJson(result, Data.class);
                list.addAll(data.getData().getComics());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder =null;
            if(view==null){
                view=View.inflate(getActivity(),R.layout.item,null);
                holder=new ViewHolder();
                x.view().inject(holder,view);

                view.setTag(holder);
            }else {
                holder= (ViewHolder) view.getTag();
            }

            Data.DataBean.ComicsBean bean = list.get(i);

            holder.tv.setText(bean.getTitle());
            Glide.with(MyFragment.this)
                    .load(bean.getCover_image_url()).into(holder.image);
            return view;
        }
    }

    class ViewHolder{
        @ViewInject(R.id.title)
        TextView tv;
        @ViewInject(R.id.image)
        ImageView image;
    }
}
