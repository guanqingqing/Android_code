package com.bway.webview_progressbar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bway.webview_progressbar.R;
import com.bway.webview_progressbar.bean.ListData;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<ListData.DataBeanX.DataBean> list;
    private Context context;

    public MyAdapter(Context context, List<ListData.DataBeanX.DataBean> list) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item, null);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ListData.DataBeanX.DataBean bean = list.get(position);
        viewHolder.title.setText(bean.getGroup().getText());
        Glide.with(context).load(bean.getGroup().getUser().getAvatar_url()).into(viewHolder.image);
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.title)
        TextView title;
        @ViewInject(R.id.image)
        ImageView image;
    }
}
