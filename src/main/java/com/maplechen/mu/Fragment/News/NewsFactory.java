package com.maplechen.mu.Fragment.News;

import com.maplechen.mu.Common.BaseFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/13.
 */
public class NewsFactory {

    private static HashMap<Integer,BaseFragment> mfragment = new HashMap<Integer, BaseFragment>();

    public  static BaseFragment getFragment(int position){
        BaseFragment fragment = mfragment.get(position);
        if(fragment == null){
            switch (position) {
                case 0:
                    fragment = new SocietyFragment();//社会新闻
                    break;
                case 1:
                    fragment = new ItFragment();//it咨询
                    break;
                case 2:
                    fragment = new DomesticFragment();//国内新闻
                    break;
                case 3:
                    fragment = new IntFragment();//国际新闻
                    break;
                case 4:
                    fragment = new AnecdoteFragment();//奇闻异事
                    break;
                /*case 5:
                    fragment = new BeautyFragment();//美女图片
                    break;*/
                case 5:
                    fragment = new SportFragment();//体育新闻
                    break;
                case 6:
                    fragment = new TechFragment();//科技新闻
                    break;
                case 7:
                    fragment = new ArmyFragment();//军事新闻
                    break;
                case 8:
                    fragment = new WorkFragment();//创业新闻
                    break;
                case 9:
                    fragment = new TourismFragment();//旅游资讯
                    break;
                case 10:
                    fragment = new DisportFragment();//娱乐新闻
                    break;

                default:
                    break;
            }
            mfragment.put(position,fragment);
        }

        return fragment;
    }

}
