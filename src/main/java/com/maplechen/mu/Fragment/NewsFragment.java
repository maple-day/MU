package com.maplechen.mu.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.maplechen.mu.Adapter.MyPagerAdapter;
import com.maplechen.mu.Common.BaseFragment;
import com.maplechen.mu.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/13.
 */
public class NewsFragment extends BaseFragment {

    @BindView(R.id.fragment_new_tab)
    TabLayout TLayout;
    @BindView(R.id.fragment_new_vpager)
    ViewPager Vpager;


    @Override
    public void initDate(String content) {

    }

    @Override
    protected void InitView() {

        TLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              //  Toast.makeText(getActivity(),"tab"+tab.getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        Vpager.setAdapter(new MyPagerAdapter(fragmentManager));
        TLayout.setupWithViewPager(Vpager);
    }

    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    protected int serLayoutId() {
        return R.layout.fragment_news;

    }

    @Override
    public String getNewsType() {
        return null;
    }


}
