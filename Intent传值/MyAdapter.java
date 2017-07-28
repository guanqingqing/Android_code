package com.example.guan.guanqingqing1506b20170717;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MyAdapter extends BaseAdapter{
   private List<Data.ListBean> list;
   	private Context context;

   	public MyAdapter(Context context, List<Data.ListBean> list) {
   		super();
   		this.list = list;
   		this.context=context;
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
   			convertView = View.inflate(context,R.layout.item,null);
   			viewHolder = new ViewHolder();
            viewHolder.title= (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.data= (TextView) convertView.findViewById(R.id.item_dada);
            viewHolder.image= (ImageView) convertView.findViewById(R.id.item_image);
   			convertView.setTag(viewHolder);
   		} else {
   			viewHolder = (ViewHolder) convertView.getTag();
   		}
   		Data.ListBean bean=list.get(position);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.data.setText(bean.getDate());
		//Glide解析图片
        Glide.with(context).load(bean.getPic()).into(viewHolder.image);
   		return convertView;
   	}

   	class ViewHolder {
   		ImageView image;
        TextView title,data;
   	}
}
