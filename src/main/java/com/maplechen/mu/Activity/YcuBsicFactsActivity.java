package com.maplechen.mu.Activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maplechen.mu.Common.BaseActivity;
import com.maplechen.mu.R;
import com.maplechen.mu.Utils.UIUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/7.
 * 学院概况 ----学院介绍和学院历史沿革
 */
public class YcuBsicFactsActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.basicfacts_tv_provider)
    TextView basicfactsTvProvider;
    @BindView(R.id.basicfacts_ll)
    LinearLayout basicfactsLl;

    private String url;
    private String httpurl;
    private String context;
    private String provider;
    private String history;

    @Override
    protected void getHistory(String history) {
        this.history = history;
    }

    @Override
    protected void getUrl(String url) {
        this.url = url;
        httpurl = url + ".html";
    }

    @Override
    protected String setUrl() {
        return httpurl;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ycu_basicfacts;
    }

    @Override
    protected void initData(String content) {
        Document doc = Jsoup.parse(content);
        Elements select = doc.select("div.content").select("P");
        System.out.println("select:" + select.size());
        context = select.first().text();
        if (history.equals("学院简介")) {
            provider = select.last().text();
        }

    }

    @Override
    public void initView() {
        String title = getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTextViewContext(context);
//        basicfactsTvContext.setText(context);
        if (history.equals("学院简介")) {
            basicfactsTvProvider.setText(provider);
        }

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

    //对内容进行截取设置textview( 换行问题)
    private void setTextViewContext(String context) {
        String[] text = context.split("    ");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < text.length; i++) {
            TextView textView = new TextView(this);
            textView.setTextSize(UIUtils.dip2px(6));
            int px = UIUtils.dip2px(7);
            textView.setPadding(px,px,px,0);
            textView.setText("    "+text[i]);
            basicfactsLl.addView(textView,params);
        }
    }


}
