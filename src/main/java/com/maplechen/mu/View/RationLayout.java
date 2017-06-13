package com.maplechen.mu.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.maplechen.mu.R;

/**
 * Created by Administrator on 2017/5/15.
 */
public class RationLayout extends FrameLayout{

    private float ration;//缩放比例

    public RationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RationLayout);
        ration = typedArray.getFloat(R.styleable.RationLayout_ration, -1);//取不到就赋值-1
        typedArray.recycle();
    }

    public RationLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 1. 获取宽度
        // 2. 根据宽度和比例ratio, 计算控件的高度
        // 3. 重新测量控件
        int wmode = MeasureSpec.getMode(widthMeasureSpec);//获取宽度的模式
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);//宽度的大小

//        int hsize = MeasureSpec.getSize(heightMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);

        if(wmode== MeasureSpec.EXACTLY && hmode!=MeasureSpec.AT_MOST &&ration>0){

            //图片的宽度=控件的宽度-左侧内边距-右侧内边距
            int imagewidth=widthsize-getPaddingLeft()-getPaddingRight();

            //图片的高度 = 图片宽度/图片的比例
            int imageheight = (int) (imagewidth/ration +0.5f);
            //控件高度 = 图片的高度+上测内边距+下侧内边距
            int height =  imageheight +getPaddingTop()+getPaddingBottom();
            //根据最新的高度来生成heightMeasureSpec(高度模式是确定的)
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
