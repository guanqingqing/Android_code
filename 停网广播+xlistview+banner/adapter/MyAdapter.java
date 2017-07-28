package com.example.guan.guanqingqing1506b20170728.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guan.guanqingqing1506b20170728.R;
import com.example.guan.guanqingqing1506b20170728.bean.Data;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;


public class MyAdapter extends BaseAdapter{
    private List<Data.ListBean> list;
    private Context context;
    final int TYPE0=0;
    final int TYPE1=1;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    ViewHolder viewHolder1;
    ViewHolder viewHolder2;

    	public MyAdapter(Context context, List<Data.ListBean> list) {
    		super();
    		this.list = list;
    		this.context=context;
            // SD卡上图片储存地址
            String path = Environment.getExternalStorageDirectory()
                    .getPath() + "/baweikaoshi";
            //创建默认的ImageLoader配置参数
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                    .createDefault(context);
    //                .diskCache(path);//自定义缓存路径


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
            int type = getItemViewType(position);

    		if (convertView == null) {
                switch (type){
                    case TYPE0:
                       convertView = View.inflate(context, R.layout.item1, null);
                        viewHolder1 = new ViewHolder();
                        viewHolder1.item1_image= (ImageView) convertView.findViewById(R.id.item1_image);
                        viewHolder1.item1_text= (TextView) convertView.findViewById(R.id.item1_text);
                        convertView.setTag(viewHolder1);
                        break;
                    case TYPE1:
                        convertView = View.inflate(context, R.layout.item2, null);
                        viewHolder2 = new ViewHolder();
                        viewHolder2.item2_image1= (ImageView) convertView.findViewById(R.id.item2_image1);
                        viewHolder2.item2_image2= (ImageView) convertView.findViewById(R.id.item2_image2);
                        viewHolder2.item2_image3= (ImageView) convertView.findViewById(R.id.item2_image3);
                        viewHolder2.item2_image4= (ImageView) convertView.findViewById(R.id.item2_image4);
                        viewHolder2.item2_text= (TextView) convertView.findViewById(R.id.item2_text);
                        convertView.setTag(viewHolder2);
                        break;
                    default:
                        break;
                }
    		} else {
                switch (type){
                    case TYPE0:
                        viewHolder1 = (ViewHolder) convertView.getTag();
                        break;
                    case TYPE1:
                        viewHolder2 = (ViewHolder) convertView.getTag();
                        break;
                    default:
                        break;
                }
    		}

            Data.ListBean bean=list.get(position);
            String pic = list.get(position).getPic();
            String[] split = pic.split("\\|");
            switch (type){
                case TYPE0:
                    viewHolder1.item1_text.setText(bean.getTitle());
                    imageLoader.displayImage(split[0],viewHolder1.item1_image,options);
                    break;
                case TYPE1:
                    viewHolder2.item2_text.setText(bean.getTitle());
                    imageLoader.displayImage(split[0],viewHolder2.item2_image1,options);
                    imageLoader.displayImage(split[1],viewHolder2.item2_image2,options);
                    imageLoader.displayImage(split[0],viewHolder2.item2_image3,options);
                    imageLoader.displayImage(split[1],viewHolder2.item2_image4,options);
                    break;
                default:
                    break;
            }
    		return convertView;
    	}

    @Override
    public int getItemViewType(int position) {
        //,当type=1一张图片,type=4四张图片
        int type = list.get(position).getType();
        if (type == 1){
            return TYPE0;
        }else if(type == 4){
            return TYPE1;
        }
        return 1;

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolder {
        TextView item1_text,item2_text;
        ImageView item1_image,item2_image1,item2_image2,item2_image3,item2_image4;
    	}
}
