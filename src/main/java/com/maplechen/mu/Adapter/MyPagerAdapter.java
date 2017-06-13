package com.maplechen.mu.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maplechen.mu.Fragment.News.NewsFactory;
import com.maplechen.mu.Utils.UIUtils;

import static com.maplechen.mu.R.array.tab_news;

/**
 * Created by Administrator on 2017/5/9.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        titles = UIUtils.getStringArray(tab_news);

    }

    @Override
    public Fragment getItem(int position) {
        return NewsFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
