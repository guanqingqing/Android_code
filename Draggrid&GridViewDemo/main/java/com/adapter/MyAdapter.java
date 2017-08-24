package com.bway.project1_yuekao5.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.project1_yuekao5.R;
import com.bway.project1_yuekao5.bean.Data;
import com.bway.project1_yuekao5.utils.App;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Data.ResultBean.IndexProductsBean> list;
    private Context context;
    private ImageLoader imageLoader;

    public MyAdapter(Context context, List<Data.ResultBean.IndexProductsBean> list) {
        super();
        this.list = list;
        this.context = context;
        //因为App中已经初始化过ImageLoder  这里直接引用
        imageLoader=((App)context.getApplicationContext()).getmImageLoader();
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
        Data.ResultBean.IndexProductsBean bean = list.get(position);
        viewHolder.title.setText(bean.getName());
        viewHolder.title.setSelected(true);
        imageLoader.displayImage(bean.getPic(),viewHolder.image);
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.image)
        ImageView image;
        @ViewInject(R.id.title)
        TextView title;
    }
}
