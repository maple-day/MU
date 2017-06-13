package com.maplechen.mu.Fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maplechen.mu.Activity.StartHomeActivity;
import com.maplechen.mu.Common.BaseFragment;
import com.maplechen.mu.R;
import com.maplechen.mu.Utils.UIUtils;
import com.maplechen.mu.View.fly.StellarMap;

import java.util.Random;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/13.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.fragmen_ll_home)
    LinearLayout fragmenLlHome;
    private String[] datas;


    @Override
    public void initDate(String content) {

    }

    @Override
    protected void InitView() {
        datas = UIUtils.getStringArray(R.array.tab_home);
        StellarMap map = new StellarMap(UIUtils.getContext());
        map.setAdapter(new MyMapAdapter());
        map.setGroup(0, true);//设置默认页面,是否有动画  要卸载adapter后面有效
        map.setRegularity(8, 8);
        int padding = UIUtils.dip2px(10);
        map.setInnerPadding(padding, padding, padding, padding);
        fragmenLlHome.addView(map);
    }

    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    protected int serLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public String getNewsType() {
        return null;
    }


    class MyMapAdapter implements StellarMap.Adapter {

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public int getCount(int group) {
            int count = datas.length / getGroupCount();
            if (group == getGroupCount() - 1) {
                count += datas.length % getGroupCount();
            }
            return count;
        }

        @Override
        public View getView(int group, int position, final View convertView) {
            position += group * getCount(group - 1);
            TextView textView = new TextView(UIUtils.getContext());
            textView.setText(datas[position]);
            final String text = datas[position];
            Random random = new Random();
            int r = 30 + random.nextInt(210);
            int g = 30 + random.nextInt(210);
            int b = 30 + random.nextInt(210);
            textView.setTextColor(Color.rgb(r, g, b));
            textView.setTextSize(UIUtils.dip2px(8) + random.nextInt(8));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartHomeActivity.stratActivity(getContext(), text);
                }
            });
            return textView;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            //向下滑是true加载上一页    向上滑是false加载下一页
            if (isZoomIn) {
                if (group > 0) {
                    group--;
                } else {
                    group = getGroupCount() - 1;
                }

            } else {
                if (group < getGroupCount() - 1) {
                    group++;
                } else {
                    group = 0;
                }
            }
            return group;
        }

    }


}
