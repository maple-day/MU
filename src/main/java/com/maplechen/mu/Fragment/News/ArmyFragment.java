package com.maplechen.mu.Fragment.News;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.maplechen.mu.Activity.DetailActivity;
import com.maplechen.mu.Adapter.NewSocietyAdapter;
import com.maplechen.mu.Common.BaseFragment;
import com.maplechen.mu.Domain.NewInfo;
import com.maplechen.mu.Http.MySubscriberNew;
import com.maplechen.mu.Http.RetrofitNew;
import com.maplechen.mu.R;

import java.util.List;

import butterknife.BindView;

/**
 *军事新闻
 * * Created by Administrator on 2017/4/13.
 */
public class ArmyFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    @BindView(R.id.news_xrview)
    XRecyclerView recyclerView;
    @BindView(R.id.ll_news)
    LinearLayout llNews;
    private List<NewInfo.NewList> newslist;
    private NewSocietyAdapter adapter;

    @Override
    public void initDate(String content) {
        Gson gson = new Gson();
        NewInfo newInfo = gson.fromJson(content, NewInfo.class);
        newslist = newInfo.newslist;
    }

    @Override
    protected void InitView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(this);
        adapter = new NewSocietyAdapter(newslist,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.refresh();
        adapter.setOnItemClickListener(new NewSocietyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "xinwen"+this.getClass().getSimpleName(), Toast.LENGTH_LONG).show();
                String urlload = newslist.get(position-1).url;
                //  Toast.makeText(YcuNewBaseActivity.this, urlload, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("url",urlload );
                startActivity(intent);
            }
        });
    }

    @Override
    protected String setUrl() {
        return "military";
    }

    @Override
    protected int serLayoutId() {
        return R.layout.news;
    }

    @Override
    public String getNewsType() {
        return "military";
    }

    @Override
    public void onRefresh() {
        RetrofitNew.getInstance(this.getContext()).getJson("military", new MySubscriberNew() {
            @Override
            public void onFailure(Throwable e) {

            }

            @Override
            public void OnSuccess(NewInfo newInfo) {
                if(newInfo.code.equals("200")){
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

    @Override
    public void onLoadMore() {

    }
}
