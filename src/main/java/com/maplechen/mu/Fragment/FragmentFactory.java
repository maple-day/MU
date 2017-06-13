package com.maplechen.mu.Fragment;

import com.maplechen.mu.Common.BaseFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/13.
 */
public class FragmentFactory {

    private static HashMap<Integer,BaseFragment> mfragment = new HashMap<Integer, BaseFragment>();

    public  static BaseFragment getFragment(int position){
        BaseFragment fragment = mfragment.get(position);
        if(fragment == null){
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new SysFragment();
                    break;
                case 2:
                    fragment = new NewsFragment();
                    break;
                case 3:
                    fragment = new ImgFragment();//专题
                    break;
                default:
                    break;
            }
            mfragment.put(position,fragment);
        }

        return fragment;
    }

}
