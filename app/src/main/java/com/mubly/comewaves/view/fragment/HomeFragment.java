package com.mubly.comewaves.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.adapter.MyViewPageAdapter;
import com.mubly.comewaves.present.HomePresent;
import com.mubly.comewaves.view.interfaceview.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresent, HomeView> implements HomeView {
    @BindView(R.id.home_top_tab)
    TabLayout mTablayout;
    @BindView(R.id.home_viewpage)
    ViewPager mViewPager;

    MyViewPageAdapter myViewPageAdapter;
    private List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();
    @Override
    protected HomePresent createPresenter() {
        return new HomePresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        super.initData();
        titles.add("视频");
        titles.add("图片");
        fragments.add(HomeInFragment.newInstance(0, 0));
        fragments.add(HomeInFragment.newInstance(1, 1));
        myViewPageAdapter = new MyViewPageAdapter(getActivity().getSupportFragmentManager(), titles, fragments);
        mViewPager.setAdapter(myViewPageAdapter);
//        mViewPager.setOffscreenPageLimit(2);//解决fragment重新创建的问题
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showError(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void shoSuccess() {

    }
}