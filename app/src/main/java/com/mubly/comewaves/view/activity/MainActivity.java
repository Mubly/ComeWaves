package com.mubly.comewaves.view.activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseActivity;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.view.costomview.MyViewPager;
import com.mubly.comewaves.view.fragment.HomeFragment;
import com.mubly.comewaves.view.fragment.IsHadFragment;
import com.mubly.comewaves.view.fragment.MineFragment;
import com.mubly.comewaves.view.fragment.ReleaseFragment;
import com.mubly.comewaves.view.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    /**
     * 首页Activity
     *
     * @return
     */
    @BindView(R.id.main_mypager)
    MyViewPager main_mypager;
    @BindView(R.id.ll_home)
    LinearLayout homeTab;
    @BindView(R.id.ll_live)
    LinearLayout searchTab;
    @BindView(R.id.ll_release)
    LinearLayout releaseTab;
    @BindView(R.id.ll_info)
    LinearLayout isHadTab;
    @BindView(R.id.ll_mine)
    LinearLayout mineTab;

    HomeFragment homeFragment = new HomeFragment();
    ReleaseFragment releaseFragment = new ReleaseFragment();
    IsHadFragment isHadFragment = new IsHadFragment();
    MineFragment mineFragment = new MineFragment();
    SearchFragment searchFragment = new SearchFragment();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        ImmersionBar.with(this).statusBarColor(R.color.gray_aph).init();
//        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(true).fitsSystemWindows(true).init();
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        fragmentList.add(homeFragment);
        fragmentList.add(searchFragment);
        fragmentList.add(releaseFragment);
        fragmentList.add(isHadFragment);
        fragmentList.add(mineFragment);
        main_mypager.setOffscreenPageLimit(5);
        main_mypager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        main_mypager.setCurrentItem(0);
        main_mypager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

//                if (TextUtils.isEmpty(AppConfig.token.get()) && (position == 2 || position == 3)) {
//                    LoginActivity.startAction(mContext);
//                } else {
//                    setTextColor(position);
//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.ll_home, R.id.ll_live, R.id.ll_release, R.id.ll_info, R.id.ll_mine,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                main_mypager.setCurrentItem(0);
                break;
            case R.id.ll_live:
                main_mypager.setCurrentItem(1);
                break;
            case R.id.ll_release:
                main_mypager.setCurrentItem(2);
                break;
            case R.id.ll_info:
                main_mypager.setCurrentItem(3);
                break;
            case R.id.ll_mine:
                if (AppConfig.token.get() == null) {
                    startActivity(new Intent(this, LoginActivity.class));
                }else
                main_mypager.setCurrentItem(4);
                break;
        }

    }


    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
