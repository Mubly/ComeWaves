package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import com.bumptech.glide.Glide;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.present.IsHadPresent;
import com.mubly.comewaves.view.activity.MessageCreateActivity;
import com.mubly.comewaves.view.activity.VideoScreenCropActivity;
import com.mubly.comewaves.view.costomview.CircleImageView;
import com.mubly.comewaves.view.costomview.PileLayout;
import com.mubly.comewaves.view.costomview.ScrollViewPage;
import com.mubly.comewaves.view.interfaceview.IsHadView;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;


public class IsHadFragment extends BaseFragment<IsHadPresent, IsHadView> {
    //    @BindView(R.id.ishad_comment_rv)
//    RecyclerView commentRv;
    @BindView(R.id.ishad_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.isHad_vp)
    ViewPager mViewPage;
    @BindView(R.id.relase_ishad_fb)
    FloatingActionButton relaseFb;

    List<String> title = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
//    SmartAdapter smartAdapter;
//    List<String> labelList = new ArrayList<>();


    @Override
    protected IsHadPresent createPresenter() {
        return new IsHadPresent();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        relaseFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageCreateActivity.class);
                intent.putExtra("type", Constant.PULL_IMAGE_CODE);
                startActivity(intent);
//                Intent intent = new Intent(mContext, VideoScreenCropActivity.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_is_had;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
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
//        smartAdapter = new SmartAdapter<String>(labelList) {
//            @Override
//            public int getLayout(int viewType) {
//                return R.layout.item_ishad_comment_layout;
//            }
//
//            @Override
//            public void dealView(VH holder, String data, int position) {
//
//                PileLayout mPileLayout = (PileLayout) holder.getChildView(R.id.comment_avtar);
//                initPraises(mPileLayout);
//            }
//
//
//        };
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        commentRv.setNestedScrollingEnabled(false);
//        commentRv.setLayoutManager(layoutManager);
//        commentRv.setAdapter(smartAdapter);
        mViewPage.setAdapter(new myPageAdapter(getChildFragmentManager(), title));
        mTabLayout.setupWithViewPager(mViewPage);
    }

//    public void initPraises(PileLayout pileLayout) {
//        pileLayout.removeAllViews();
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        for (int i = 0; i < 3; i++) {
//            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_praise2, pileLayout, false);
//            Glide.with(mContext).load(R.drawable.start_img).into(imageView);
//            pileLayout.addView(imageView);
//        }
//
//    }

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
