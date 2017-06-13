package com.maplechen.mu.Activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.maplechen.mu.Adapter.ClubAdapter;
import com.maplechen.mu.Common.BaseActivity;
import com.maplechen.mu.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 学生社团
 * Created by Administrator on 2017/5/7.
 */
public class YcuClubActivity extends BaseActivity {


    List<String> datalist = new ArrayList<>();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.club_xrv)
    RecyclerView recycleview;

    private ClubAdapter adapter;


    @Override
    protected String setUrl() {
        return "B20110603181749.html";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ycu_club;
    }

    @Override
    protected void initData(String content) {
        Document doc = Jsoup.parse(content);
        Elements select = doc.select("div.content").select("P.textthick1");
        for (Element element : select) {
            String text = element.text();
            if ((!text.equals("")) && text != null) {
                datalist.add(text);
            }

        }
        Log.e("YcuClubActivity", "size" + datalist.size());
    }

    @Override
    public void initView() {
        String title = getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);
        adapter = new ClubAdapter(datalist);
        recycleview.setAdapter(adapter);

    }

    @Override
    public void initBar() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            closeCurrent();
        }
        return super.onOptionsItemSelected(item);
    }


}
