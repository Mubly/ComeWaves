package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;


import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.model.adapter.MyViewPageAdapter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.present.SearchPresent;
import com.mubly.comewaves.view.activity.HotLebelActivity;
import com.mubly.comewaves.view.costomview.ScrollViewPage;
import com.mubly.comewaves.view.interfaceview.SearchView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import android.support.annotation.NonNull;

import butterknife.BindView;


/**
 *
 */
public class SearchFragment extends BaseFragment<SearchPresent, SearchView> implements SearchView {
    @BindView(R.id.top_label_rv)
    RecyclerView topLabelRv;
    @BindView(R.id.search_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.search_viewpage)
    ViewPager mViewPage;
    //    @BindView(R.id.search_scroll_view)
//    NestedScrollView mScrollView;
    List<String> title = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    SmartAdapter smartAdapter;
    List<String> labelList = new ArrayList<>();


    @Override
    protected SearchPresent createPresenter() {
        return new SearchPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getCategaryOne(1);

    }

    @Override
    public void initEvent() {
        super.initEvent();

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        labelList.add("wiueu");
        smartAdapter = new SmartAdapter<String>(labelList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_search_top_img_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
                holder.setText(R.id.search_top_amount, "232");
                holder.setText(R.id.search_top_title, "周边店铺");
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, HotLebelActivity.class));
                    }
                });
//                holder.setNetImage(mContext,R.id.search_top_img_iv,R.drawable.start_img);
            }


        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topLabelRv.setNestedScrollingEnabled(false);
        topLabelRv.setLayoutManager(layoutManager);
        topLabelRv.setAdapter(smartAdapter);
    }

    @Override
    public void getOneTab(List<CategoryVo> categoryVoList) {
        title.clear();
        fragmentList.clear();
        for (CategoryVo categoryVo : categoryVoList) {
            title.add(categoryVo.cate_name);
            fragmentList.add(SearchInFragment.newInstance(categoryVo.cate_id));
        }
        mViewPage.setAdapter(new myPageAdapter(getActivity().getSupportFragmentManager(), title));
        mTabLayout.setupWithViewPager(mViewPage);
    }


    private class myPageAdapter extends FragmentPagerAdapter {
        List<String> titleList;

        public myPageAdapter(FragmentManager fm, List<String> titleList) {
            super(fm);
            this.titleList = titleList;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        public List<String> getTitleList() {
            return titleList;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
