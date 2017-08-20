package com.bway.zonghedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 类描述:
 * 姓名: guanqingqing
 * 时间:  2017/8/19 11:06
 */
public class MyAdapter extends BaseAdapter {
    private List<Data.DataBean.ComicsBean> list;
    private Context context;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    public MyAdapter(Context context, List<Data.DataBean.ComicsBean> list) {
        super();
        this.list = list;
        this.context = context;

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);

        //将configuration配置到imageloader中
        imageLoader= ImageLoader.getInstance();
        imageLoader.init(configuration);
        options=new DisplayImageOptions.Builder()
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fram_item, null);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder, convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Data.DataBean.ComicsBean bean = list.get(position);
        viewHolder.title.setText(bean.getTitle());
        imageLoader.displayImage(bean.getCover_image_url(),viewHolder.image,options);
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.item_title)
        TextView title;
        @ViewInject(R.id.image)
        ImageView image;
    }
}
