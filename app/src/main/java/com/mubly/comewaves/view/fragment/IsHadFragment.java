package com.mubly.comewaves.view.fragment;


import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.view.costomview.CircleImageView;
import com.mubly.comewaves.view.costomview.PileLayout;
import com.mubly.comewaves.view.costomview.ScrollViewPage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class IsHadFragment extends BaseFragment {
    @BindView(R.id.ishad_comment_rv)
    RecyclerView commentRv;
    @BindView(R.id.ishad_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.isHad_vp)
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
        return R.layout.fragment_is_had;
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
        title.add("衣服");
        title.add("席子");
        title.add("鞋子");
        title.add("包包");
        title.add("美妆");
        fragmentList.add(IsHadInFragment.newInstance(0));
        fragmentList.add(IsHadInFragment.newInstance(1));
        fragmentList.add(IsHadInFragment.newInstance(2));
        fragmentList.add(IsHadInFragment.newInstance(3));
        fragmentList.add(IsHadInFragment.newInstance(4));
        smartAdapter = new SmartAdapter<String>(labelList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_ishad_comment_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {

                PileLayout mPileLayout = (PileLayout) holder.getChildView(R.id.comment_avtar);
                initPraises(mPileLayout);
            }


        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        commentRv.setNestedScrollingEnabled(false);
        commentRv.setLayoutManager(layoutManager);
        commentRv.setAdapter(smartAdapter);
        mViewPage.setAdapter(new myPageAdapter(getChildFragmentManager(), title));
        mTabLayout.setupWithViewPager(mViewPage);
    }
    public void initPraises(PileLayout pileLayout) {
        pileLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int i = 0; i < 3; i++) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_praise, pileLayout, false);
            Glide.with(mContext).load(R.drawable.start_img).into(imageView);
            pileLayout.addView(imageView);
        }

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
