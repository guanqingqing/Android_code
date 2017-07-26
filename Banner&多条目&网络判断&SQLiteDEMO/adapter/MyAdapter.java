package com.example.guan.lian_manhua.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guan.lian_manhua.R;
import com.example.guan.lian_manhua.bean.Data;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/7/25 10:07
 */
public class MyAdapter extends BaseAdapter {
    private List<Data.DataBean.FeedsBean> list;
    private Context context;
    final int TYPE0 = 0;
    final int TYPE1 = 1;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public MyAdapter(Context context, List<Data.DataBean.FeedsBean> list) {
        super();
        this.list = list;
        this.context = context;

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);

        //将configuration配置到imageloader中
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(configuration);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .build();


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
        ViewHolder viewHolder = null;
        ViewHolder viewHolder2 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE0:
                    convertView = View.inflate(context, R.layout.one_item1, null);
                    viewHolder = new ViewHolder();
                    viewHolder.item1_image = (ImageView) convertView.findViewById(R.id.item1_image);
                    viewHolder.item1_text = (TextView) convertView.findViewById(R.id.item1_text);
                    convertView.setTag(viewHolder);
                    break;
                case TYPE1:
                    convertView = View.inflate(context, R.layout.one_item2, null);
                    viewHolder2 = new ViewHolder();
                    viewHolder2.item2_text = (TextView) convertView.findViewById(R.id.item2_text);
                    convertView.setTag(viewHolder2);
                    break;
                default:
                    break;
            }
        } else {
            if (type == TYPE0) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                viewHolder2 = (ViewHolder) convertView.getTag();
            }
        }

        Data.DataBean.FeedsBean bean = list.get(position);

        switch (type) {
            case TYPE0:
                viewHolder.item1_text.setText(bean.getContent().getText());
                imageLoader.displayImage(bean.getUser().getAvatar_url(), viewHolder.item1_image, options);
                break;
            case TYPE1:
                viewHolder2.item2_text.setText(bean.getContent().getText());
                break;
            default:
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        int p = position % 6;
        if (p == 0) {
            return TYPE0;
        } else if (p < 3) {
            return TYPE1;
        } else {
            return TYPE0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolder {
        ImageView item1_image;
        TextView item1_text, item2_text;
    }
}
