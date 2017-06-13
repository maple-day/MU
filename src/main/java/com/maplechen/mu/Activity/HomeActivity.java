package com.maplechen.mu.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maplechen.mu.Common.ActivityManager;
import com.maplechen.mu.Fragment.FragmentFactory;
import com.maplechen.mu.Fragment.ImgFragment;
import com.maplechen.mu.Fragment.HomeFragment;
import com.maplechen.mu.Fragment.NewsFragment;
import com.maplechen.mu.Fragment.SysFragment;
import com.maplechen.mu.R;
import com.maplechen.mu.Utils.UIUtils;
import com.maplechen.mu.Common.BaseActivity;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_main)
    LinearLayout contentMain;
    @BindView(R.id.nav_view)
    ArcNavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.content)//内容
    FrameLayout content;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)//bottom 按钮 主页
    LinearLayout llHome;
    @BindView(R.id.iv_sys)
    ImageView ivSys;
    @BindView(R.id.tv_sys)
    TextView tvSys;
    @BindView(R.id.ll_sys)//bottom 按钮  教务系统
    LinearLayout llSys;
    @BindView(R.id.iv_news)
    ImageView ivNews;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.ll_new)//bottom 按钮 新闻
    LinearLayout llNew;
    @BindView(R.id.iv_game)
    ImageView ivGame;
    @BindView(R.id.tv_game)
    TextView tvGame;
    @BindView(R.id.ll_game)//bottom 按钮 娱乐
    LinearLayout llGame;
    private ActionBarDrawerToggle toggle;
    private FragmentManager fm;
    private FragmentTransaction bt;
    private HomeFragment homeFragmeny;
    private SysFragment sysFragment;
    private NewsFragment newsFragment;
    private ImgFragment imgFragment;


//        System.out.println("屏幕密度"+ getResources().getDisplayMetrics().density);

    @OnClick({R.id.ll_home,R.id.ll_sys,R.id.ll_new,R.id.ll_game})
    public void changetab(View view){
        switch (view.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_sys:
                setSelect(1);
                break;
            case R.id.ll_new:
                setSelect(2);
                break;
            case R.id.ll_game:
                setSelect(3);
                break;
        }

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    public void initView() {
        toolbar.setTitle("MU");
        toolbar.setTitleTextColor(getResources().getColor(R.color.barColor));
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
//        toolbar.setNavigationIcon(R.mipmap.icon_qq);
        navView.setNavigationItemSelectedListener(this);
        setSelect(0);
    }

    @Override
    protected void initData(String content) {

    }

    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    public void initBar() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ViewGroup view = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
        View childAt = view.getChildAt(0);
        if(childAt!=null){
            ViewCompat.setFitsSystemWindows(childAt,true);
            ViewCompat.setChildrenDrawingOrderEnabled(view,false);
        }
    }



    private void setSelect(int position) {
        fm = getSupportFragmentManager();
        bt = fm.beginTransaction();
        hideFragment();
        resettab();
        switch (position) {
            case 0:
                if (homeFragmeny == null) {
                    homeFragmeny = (HomeFragment) FragmentFactory.getFragment(0);
                    bt.add(R.id.content, homeFragmeny);
                }
                // bt.replace(R.id.content, homeFragmeny);
                ivHome.setImageResource(R.mipmap.home1);
                tvHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                bt.show(homeFragmeny);
                break;
            case 1:
                if (sysFragment == null) {
                    sysFragment = (SysFragment) FragmentFactory.getFragment(1);
                    bt.add(R.id.content, sysFragment);
                }
                // bt.replace(R.id.content, touZiFragmeny);
                tvSys.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ivSys.setImageResource(R.mipmap.system1);
                bt.show(sysFragment);
                break;
            case 2:
                if (newsFragment == null) {
                    newsFragment = (NewsFragment) FragmentFactory.getFragment(2);
                    bt.add(R.id.content, newsFragment);
                }
                // bt.replace(R.id.content, meFragmeny);
                ivNews.setImageResource(R.mipmap.news);
                tvNews.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                bt.show(newsFragment);
                break;
            case 3:
                if (imgFragment == null) {
                    imgFragment = (ImgFragment) FragmentFactory.getFragment(3);
                    bt.add(R.id.content, imgFragment);
                }
                // bt.replace(R.id.content, fragmeny);
                ivGame.setImageResource(R.mipmap.game1);
                tvGame.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                bt.show(imgFragment);
                break;

            default:
                break;

        }
        bt.commit();
    }

    private void hideFragment() {
        if (homeFragmeny != null) {
            bt.hide(homeFragmeny);
        }
        if (sysFragment != null) {
            bt.hide(sysFragment);
        }
        if (newsFragment != null) {
            bt.hide(newsFragment);
        }
        if (imgFragment != null) {
            bt.hide(imgFragment);
        }
    }
    private void resettab() {
        ivHome.setImageResource(R.mipmap.home);
        ivSys.setImageResource(R.mipmap.system);
        ivNews.setImageResource(R.mipmap.new1);
        ivGame.setImageResource(R.mipmap.game);
        tvHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvSys.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvNews.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvGame.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_out) {
            ActivityManager.getInstance().removeAll();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }





}
