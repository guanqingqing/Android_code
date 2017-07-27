

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment1 extends Fragment {
    private List<News.DataBean> list;
      private Handler mHandler=new Handler(){
              @Override
              public void handleMessage(Message msg) {
                  String string = msg.obj.toString();
                  Gson gson = new Gson();
                  News news = gson.fromJson(string,News.class);
                  list.addAll(news.getData());
                 lvAdapter .notifyDataSetChanged();

              }
          };

    private ListView lv;
    private LvAdapter lvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);
        lv = (ListView) view.findViewById(R.id.lv);

        list = new ArrayList<>();
        lvAdapter = new LvAdapter(list,getActivity());
        lv.setAdapter(lvAdapter);

        doPost();


        return view;
    }

    private void doPost() {
         new Thread(){
                     @Override
                     public void run() {
                         String urlPath ="http://www.93.gov.cn/93app/data.do";
                         Map<String,Object> map=new HashMap<>();
                         map.put("channelId",0);
                         map.put("startNum",0);
                         String result = UrlConnection.postUrlConnect(urlPath,map);
         //                try {
         //                    byte [] buff = result.getBytes("gbk");
         //                    result=new String(buff,"utf-8");
         //                } catch (UnsupportedEncodingException e) {
         //                    e.printStackTrace();
         //                }
                         if(result!=null){
                             Message msg= Message.obtain();
                             msg.obj=result;
                             mHandler.sendMessage(msg);
                         }

                     }
                 }.start();
    }
}
