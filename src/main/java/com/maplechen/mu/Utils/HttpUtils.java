package com.maplechen.mu.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.maplechen.mu.Domain.Msg;
import com.maplechen.mu.Http.MySubscriber;
import com.maplechen.mu.Http.RetrofitClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */
public class HttpUtils {

    private static HttpUtils httpUtils;
    public static HttpUtils getIntance(){
        if(httpUtils==null){
            synchronized (HttpUtils.class){
                if(httpUtils==null){
                    httpUtils=new HttpUtils();
                }
            }
        }
        return httpUtils;
    }
    public  void onNetYcu(final boolean isLoadMore , final Context context , final List<Msg> datalist, final RecyclerView.Adapter adapter, final XRecyclerView recyclerView, String url){

        RetrofitClient.getInstance(context).getYcuHtml(new MySubscriber() {
            @Override
            public void onErrorMsg(Throwable e) {
                Toast.makeText(context, "加载失败啦", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccessMsg(String html) {
                List<Msg> list = new ArrayList<>();
                Document doc = Jsoup.parse(html);
                Elements select = doc.select("table.black").select("tr");
                for (Element element : select) {
                    String text = element.select("td>a").text();
                    String url = element.select("td>a").attr("href");
                    String date = element.select("td>font").text();
                    Msg msg = new Msg(text, date, url);
                    list.add(msg);
                }
                if(isLoadMore){
                    datalist.clear();
                }
                datalist.addAll(list);
                adapter.notifyDataSetChanged();
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        }, url);

    }

}
