package com.maplechen.mu.Utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2017/5/6.
 */
public class InitViewUtils {
    public static void initView(AppCompatActivity context, Toolbar toolbar, TextView toolbarTitle, XRecyclerView recyclerView){
        String title = context.getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        //    recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }
}
