package com.maplechen.mu.Fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.maplechen.mu.Adapter.ImgAdapter;
import com.maplechen.mu.Common.BaseFragment;
import com.maplechen.mu.Domain.ImgInfo;
import com.maplechen.mu.Http.MySubscriberImg;
import com.maplechen.mu.Http.RetrofitImg;
import com.maplechen.mu.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/13.
 */
public class ImgFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    /**
     *   1	性感美女
         2	日韩美女
         3	丝袜美腿
         4	美女照片
         5	美女写真
         6	清纯美女
         7	性感车模
     * @param content
     */


    public List<ImgInfo.Gallery> datalist ;
    @BindView(R.id.news_xrviewa)
    XRecyclerView recyclerView;
    private ImgAdapter adapter;

    @Override
    public void initDate(String content) {
        Gson gson = new Gson();
        ImgInfo newInfo = gson.fromJson(content, ImgInfo.class);
        datalist = newInfo.tngou;
    }

    @Override
    protected void InitView() {
    //    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(this);
        adapter = new ImgAdapter(datalist, getContext());
        recyclerView.setAdapter(this.adapter);
    }

    @Override
    protected String setUrl() {
        return "5";
    }

    @Override
    protected int serLayoutId() {
        return R.layout.news_beauty;
    }

    @Override
    public String getNewsType() {
        return "img";
    }

    @Override
    public void onRefresh() {
        RetrofitImg.getInstance(getContext()).getJson("1", new MySubscriberImg() {
            @Override
            public void onFailure(Throwable e) {

            }
            @Override
            public void OnSuccess(ImgInfo imgInfo) {
                if (imgInfo.status.equals("true")) {
                    List<ImgInfo.Gallery> list = imgInfo.tngou;
                    datalist.clear();
                    datalist.addAll(list);
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
