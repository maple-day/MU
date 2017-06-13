package com.maplechen.mu.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maplechen.mu.Fragment.Lives.LiveFactory;
import com.maplechen.mu.R;
import com.maplechen.mu.Utils.UIUtils;

/**
 * Created by Administrator on 2017/5/29.
 */
public class MyLiveAdapter extends FragmentPagerAdapter {

    private final String[] lives;

    public MyLiveAdapter(FragmentManager fm) {
        super(fm);
        lives =  UIUtils.getStringArray(R.array.title_live);
    }

    @Override
    public Fragment getItem(int position) {
        return LiveFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return lives.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lives[position];
    }
}
