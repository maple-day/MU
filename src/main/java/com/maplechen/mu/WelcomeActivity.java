package com.maplechen.mu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.maplechen.mu.Activity.HomeActivity;
import com.maplechen.mu.Utils.PaletteUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WelcomeActivity extends Activity {

    @BindView(R.id.iv_entry)
    ImageView ivEntry;

    private static final int ANIM_TIME = 1000;

    private static final float SCALE_END = 1.15F;
    private static final int[] Imgs = {
            R.drawable.welcome1, R.drawable.welcome2,
            R.drawable.welcome3, R.drawable.welcome4,
            R.drawable.welcome5, R.drawable.welcome6,
            R.drawable.welcome7, R.drawable.welcome8,};
    private Random random;
    private int i;//获取第几张背景图片


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        random = new Random(SystemClock.elapsedRealtime());
        i = random.nextInt(Imgs.length - 1);
        PaletteUtils.getWelcomeActivityBgColor(this, Imgs[i]);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initView();
    }

    //启动动画
    private void initView() {
        Glide.with(this).load(Imgs[i]).into(ivEntry);
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startAnim();//开启动画
                    }
                });
    }

    //开启动画
    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                WelcomeActivity.this.finish();
            }
        });

    }
}
