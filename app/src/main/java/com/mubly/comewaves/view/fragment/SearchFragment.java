package com.mubly.comewaves.view.fragment;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.MyViewPageAdapter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.view.costomview.ScrollViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 *
 */
public class SearchFragment extends BaseFragment {
    @BindView(R.id.top_label_rv)
    RecyclerView topLabelRv;
    @BindView(R.id.search_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.search_viewpage)
    ScrollViewPage mViewPage;

    List<String> title = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    SmartAdapter smartAdapter;
    List<String> labelList = new ArrayList<>();



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initData() {
        super.initData();


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
        title.add("衣服");
        title.add("席子");
        title.add("鞋子");
        title.add("包包");
        title.add("美妆");
        title.add("美食");
        title.add("办公");
        title.add("公交");
        title.add("出行");
        title.add("旅游");
        title.add("户外");
        title.add("生活");
        fragmentList.add(SearchInFragment.newInstance(0));
        fragmentList.add(SearchInFragment.newInstance(1));
        fragmentList.add(SearchInFragment.newInstance(2));
        fragmentList.add(SearchInFragment.newInstance(3));
        fragmentList.add(SearchInFragment.newInstance(4));
        fragmentList.add(SearchInFragment.newInstance(5));
        fragmentList.add(SearchInFragment.newInstance(6));
        fragmentList.add(SearchInFragment.newInstance(7));
        fragmentList.add(SearchInFragment.newInstance(8));
        fragmentList.add(SearchInFragment.newInstance(9));
        fragmentList.add(SearchInFragment.newInstance(10));
        fragmentList.add(SearchInFragment.newInstance(11));
        smartAdapter = new SmartAdapter<String>(labelList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_search_top_img_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
                holder.setText(R.id.search_top_amount, "232");
                holder.setText(R.id.search_top_title, "周边店铺");
//                holder.setNetImage(mContext,R.id.search_top_img_iv,R.drawable.start_img);
            }


        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topLabelRv.setNestedScrollingEnabled(false);
        topLabelRv.setLayoutManager(layoutManager);
        topLabelRv.setAdapter(smartAdapter);

        mViewPage.setAdapter(new myPageAdapter(getActivity().getSupportFragmentManager(), title));
        mTabLayout.setupWithViewPager(mViewPage);
    }

    private class myPageAdapter extends FragmentStatePagerAdapter {
        List<String> titleList;

        public myPageAdapter(FragmentManager fm, List<String> titleList) {
            super(fm);
            this.titleList = titleList;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
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
