package com.maplechen.mu.Fragment.News;

import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.maplechen.mu.Adapter.BeautyAdapter;
import com.maplechen.mu.Common.BaseFragment;
import com.maplechen.mu.Domain.NewInfo;
import com.maplechen.mu.Http.MySubscriberNew;
import com.maplechen.mu.Http.RetrofitNew;
import com.maplechen.mu.R;

import java.util.List;

import butterknife.BindView;

/**
 * 美女图片
 * * Created by Administrator on 2017/4/13.
 */
public class BeautyFragment extends BaseFragment implements XRecyclerView.LoadingListener {


    private List<NewInfo.NewList> newslist;
    @BindView(R.id.news_xrviewa)
    XRecyclerView recyclerView;
    private BeautyAdapter adapter;

    @Override
    public void initDate(String content) {
        Gson gson = new Gson();
        NewInfo newInfo = gson.fromJson(content, NewInfo.class);
        newslist = newInfo.newslist;
    }

    @Override
    protected void InitView() {
  //      StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(this);

        adapter = new BeautyAdapter(newslist, this.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected String setUrl() {
        return "meinv";
    }

    @Override
    protected int serLayoutId() {
        return R.layout.news_beauty;
    }

    @Override
    public String getNewsType() {
        return "meinv";
    }


    public void onRefresh() {
        RetrofitNew.getInstance(this.getContext()).getJson("meinv", new MySubscriberNew() {
            @Override
            public void onFailure(Throwable e) {

            }

            @Override
            public void OnSuccess(NewInfo newInfo) {
                if (newInfo.code.equals("200")) {
                    List<NewInfo.NewList> list = newInfo.newslist;
                    newslist.clear();
                    newslist.addAll(list);
                    adapter.notifyDataSetChanged();
                    recyclerView.refreshComplete();
                    recyclerView.loadMoreComplete();
                }
            }
        });
    }


    public void onLoadMore() {

    }

}
