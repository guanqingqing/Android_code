package com.example.guan.guanqingqing1506b20170724;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * 类描述:适配器
 * 姓名: guanqingqing
 * 时间:  2017/7/24 9:03
 */
public class MyAdapter extends BaseAdapter{
    private List<Data.ListBean> list;
    private Context context;
    final int TYPE0=0;
    final int TYPE1=1;


	ImageLoader imageLoader;
	DisplayImageOptions options;

    	public MyAdapter(Context context, List<Data.ListBean> list) {
    		super();
    		this.list = list;
            this.context=context;

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
            int type = getItemViewType(position);
    		if (convertView == null) {
                viewHolder = new ViewHolder();
                switch (type){
                    case TYPE0:
                        convertView = View.inflate(context,R.layout.item1, null);
                        viewHolder.item1_image1= (ImageView) convertView.findViewById(R.id.item1_image1);
                        viewHolder.item1_image2= (ImageView) convertView.findViewById(R.id.item1_image2);
                        break;
                    case TYPE1:
                        convertView = View.inflate(context,R.layout.item2, null);
                        viewHolder.item2_image1= (ImageView) convertView.findViewById(R.id.item2_image1);
                        viewHolder.item2_image2= (ImageView) convertView.findViewById(R.id.item2_image2);
                        viewHolder.item2_image3= (ImageView) convertView.findViewById(R.id.item2_image3);
                        viewHolder.item2_image4= (ImageView) convertView.findViewById(R.id.item2_image4);
                        break;
                    default:
                        break;
                }


    			convertView.setTag(viewHolder);
    		} else {
    			viewHolder = (ViewHolder) convertView.getTag();
    		}
    		switch (type){
                case TYPE0:
					String pic = list.get(position).getPic();
					String[] split = pic.split("\\|");
					imageLoader.displayImage(split[0],viewHolder.item1_image1,options);
					imageLoader.displayImage(split[1],viewHolder.item1_image2,options);
                    break;
                case TYPE1:
					String pic2 = list.get(position).getPic();
               if (pic2!=null){
	            String[] split2 = pic2.split("\\|");
	            imageLoader.displayImage(split2[0],viewHolder.item2_image1,options);
				imageLoader.displayImage(split2[1],viewHolder.item2_image2,options);
	            imageLoader.displayImage(split2[0],viewHolder.item2_image3,options);
	            imageLoader.displayImage(split2[1],viewHolder.item2_image4,options);
                }

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
        return 4;
    }

    class ViewHolder {
    		ImageView item1_image1,item1_image2,item2_image1,item2_image2,item2_image3,item2_image4;
        }
}
