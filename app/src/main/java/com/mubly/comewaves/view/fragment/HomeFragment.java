package com.mubly.comewaves.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.adapter.MyViewPageAdapter;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.present.HomePresent;
import com.mubly.comewaves.view.interfaceview.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment  {
    @BindView(R.id.home_top_tab)
    TabLayout mTablayout;
    @BindView(R.id.home_viewpage)
    ViewPager mViewPager;

    MyViewPageAdapter myViewPageAdapter;
    private List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    public void initData() {
        super.initData();
        titles.add("视频");
        titles.add("图片");
        fragments.add(HomeInFragment.newInstance(1 ));
        fragments.add(HomeInFragment.newInstance(2));
        myViewPageAdapter = new MyViewPageAdapter(getActivity().getSupportFragmentManager(), titles, fragments);
        mViewPager.setAdapter(myViewPageAdapter);
//        mViewPager.setOffscreenPageLimit(2);//解决fragment重新创建的问题
        mTablayout.setupWithViewPager(mViewPager);
    }



}
