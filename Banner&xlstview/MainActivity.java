package com.example.dell.yuekao;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import org.xutils.common.Callback.CommonCallback;
import java.util.ArrayList;
import java.util.List;
import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener{
    XListView xListView;
    int page = 1;
    Banner banner;
    SQLiteDatabase db;
    private List<Data.DataBean.ReturnDataBean.ComicsBean> list;
    private List<ImageData.DataBean.ReturnDataBean.GalleryItemsBean> list2;
    private List<String> listimage;
    String url = "http://app.u17.com/v3/appV3_3/android/phone/list/commonComicList"
            + "?argValue=23&argName=sort&argCon=0"
            + "&android_id=4058040115108878"
            + "&v=3330110&model=GT-P5210"
            + "&come_from=Tg002%20HTTP/1.1" + "&page=";
    String imageurl="http://app.u17.com/v3/appV3_3/android/phone/comic/boutiqueListNew?sexType=2&android_id=4058040115108878&v=3330110&model=GT-P5210&come_from=Tg002%20HTTP/1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (isNet()){
            Toast.makeText(MainActivity.this,"有网",Toast.LENGTH_LONG).show();
            getData();
            getImage();
        }else{
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setMessage("是否进行网络判断")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    })
                    .create();
        }


    }

    private void getImage() {
        RequestParams params = new RequestParams(imageurl);
        x.http().get(params, new CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ImageData imagedata = new Gson().fromJson(s,ImageData.class);
                list2.addAll(imagedata.getData().getReturnData().getGalleryItems());
                for (int i =0;i<list2.size();i++){
                    listimage.add(list2.get(i).getCover());
                }
                ContentValues values  =  new ContentValues();
                for (ImageData.DataBean.ReturnDataBean.GalleryItemsBean imagebean : list2){
                    values.put("image",imagebean.getCover());
                    db.insert("users",null,values);
                }

                banner.setImageLoader(new ImageActivity(MainActivity.this));
                banner.setImages(listimage);
                banner.setDelayTime(2000);
                banner.start();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        banner=(Banner)findViewById(R.id.banner);
        xListView=(XListView)findViewById(R.id.xlistview);
        xListView.setXListViewListener(this);
        xListView.setPullLoadEnable(true);
        list=new ArrayList<>();
        list2=new ArrayList<>();
        listimage=new ArrayList<>();
        SQLiteCreate sqLiteCreate = new SQLiteCreate(MainActivity.this);
         db = sqLiteCreate.getWritableDatabase();

    }

    private void getData() {
        RequestParams params = new RequestParams(url + page);
        x.http().get(params, new CommonCallback<String>() {
            /**
             * 成功
             * */
            @Override
            public void onSuccess(String s) {
                Data data = new Gson().fromJson(s, Data.class);
                list.addAll(data.getData().getReturnData().getComics());
                Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG).show();
                ContentValues values  =  new ContentValues();
                for (Data.DataBean.ReturnDataBean.ComicsBean comicsBean : list){
                    values.put("name",comicsBean.getName());
                    values.put("desc",comicsBean.getDescription());
                    db.insert("users",null,values);
                }

                MyBase myBase = new MyBase(list,MainActivity.this);
                xListView.setAdapter(myBase);

            }
            /**
             * 错误
             * */
            @Override
            public void onError(Throwable throwable, boolean b) {
                Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
            }
            /**
             * 取消
             * */
            @Override
            public void onCancelled(CancelledException e) {

            }
            /**
             * 加载完毕
             * */
            @Override
            public void onFinished() {
                xListView.stopRefresh();
                xListView.stopLoadMore();
            }

        });
    }

    @Override
    public void onRefresh() {
        page=1;
        list.clear();
        getData();
    }

    @Override
    public void onLoadMore() {
        page++;
        getData();
    }
    public boolean isNet(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info!=null&&info.isConnected();
    }
}
