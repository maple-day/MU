package com.maplechen.mu.Common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maplechen.mu.View.onLoadingPage;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */
public abstract class BaseFragment extends Fragment {

    private onLoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new onLoadingPage(container.getContext()) {
            @Override
            protected String getType() {
                return getNewsType();
            }

            @Override
            protected void onSuccess(ReasultState reasultState, View successView) {
                ButterKnife.bind(BaseFragment.this,successView);
                initDate(reasultState.getContent());
                InitView();
            }
            @Override
            protected String getUrl() {
                return setUrl();
            }

            @Override
            protected int getLayoutId() {
                return serLayoutId();
            }
        };

        return loadingPage;
    }
    public abstract void initDate(String content) ;
    protected abstract void InitView();
    protected abstract String setUrl();
    protected abstract int serLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        loadingPage.show();
        super.onActivityCreated(savedInstanceState);
    }
    public abstract String getNewsType();
}
