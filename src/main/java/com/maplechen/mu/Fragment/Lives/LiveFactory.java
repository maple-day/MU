package com.maplechen.mu.Fragment.Lives;

import com.maplechen.mu.Common.BaseFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/13.
 */
public class LiveFactory {

    private static HashMap<Integer,BaseFragment> mfragment = new HashMap<Integer, BaseFragment>();

    public  static BaseFragment getFragment(int position){
        BaseFragment fragment = mfragment.get(position);
        if(fragment == null){
            switch (position) {
                case 0:
                    fragment = new LoLFragemnt();
                    break;
                case 1:
                    fragment = new LoLFragemnt();
                    break;
                case 2:
                    fragment = new LoLFragemnt();
                    break;
                case 3:
                    fragment = new LoLFragemnt();
                    break;

                default:
                    break;
            }
            mfragment.put(position,fragment);
        }
        return fragment;
    }

}
