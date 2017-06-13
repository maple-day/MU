package com.maplechen.mu.Activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.maplechen.mu.Adapter.NoticeAdapter;
import com.maplechen.mu.Common.BaseActivity;
import com.maplechen.mu.Domain.Msg;
import com.maplechen.mu.R;
import com.maplechen.mu.Utils.HttpUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/6.
 */
public class YcuNewBaseActivity extends BaseActivity implements XRecyclerView.LoadingListener {


    @BindView(R.id.xrv_notice)
    XRecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private NoticeAdapter adapter;

    int CurrentPage = 1;
    List<Msg> datalist = new ArrayList<>();
    private String url;
    private String httpurl;

    @Override
    protected void getUrl(String url) {
        this.url = url;
        httpurl = url+".html";
    }

    @Override
    protected String setUrl() {
        return httpurl;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ycu_notice;
    }

    @Override
    protected void initData(String content) {
        Document doc = Jsoup.parse(content);
        Elements select = doc.select("table.black").select("tr");
        for (Element element : select) {
            String text = element.select("td>a").text();
            String url = element.select("td>a").attr("href");
            String date = element.select("td>font").text();
            Msg msg = new Msg(text, date, url);
            datalist.add(msg);
        }
    }

    @Override
    public void initView() {
        String title = getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(title.equals("专业介绍")){
            recyclerView.setLoadingMoreEnabled(false);
            recyclerView.setPullRefreshEnabled(false);
            recyclerView.refresh();
        }else {
            recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
            recyclerView.setLoadingMoreEnabled(true);
            recyclerView.setPullRefreshEnabled(true);
            recyclerView.setLoadingListener(this);

        }
        adapter = new NoticeAdapter(datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.refresh();
        adapter.setOnItemClickListener(new NoticeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(YcuNewBaseActivity.this, datalist.get(position).msg, Toast.LENGTH_LONG).show();
                String urlload = "http://www.ycu.edu.cn/"+datalist.get(position-1).url;
              //  Toast.makeText(YcuNewBaseActivity.this, urlload, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(YcuNewBaseActivity.this, DetailActivity.class);
                intent.putExtra("url",urlload );
                startActivity(intent);
            }
        });
    }

    @Override
    public void initBar() {

    }

    @Override
    public void onRefresh() {
        HttpUtils.getIntance().onNetYcu(true,this,datalist,adapter,recyclerView,httpurl);
    }

    @Override
    public void onLoadMore() {
        String hurl = url+"_"+CurrentPage+".html";
        HttpUtils.getIntance().onNetYcu(false,this,datalist,adapter,recyclerView,hurl);
        CurrentPage++;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            closeCurrent();
        }
        return super.onOptionsItemSelected(item);
    }
}
