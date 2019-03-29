package com.mubly.comewaves.view.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.model.SearchVideoVo;
import com.mubly.comewaves.present.SearchInfoPresent;
import com.mubly.comewaves.videoplayer.ScrollCalculatorHelper;
import com.mubly.comewaves.view.costomview.SampleCoverVideo;
import com.mubly.comewaves.view.fragment.SearchInfoVideoFragment;
import com.mubly.comewaves.view.interfaceview.SearchInfoView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.youth.banner.transformer.CubeOutTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查询二级页面
 */
public class SearchInfoActivity extends BaseActivity<SearchInfoPresent, SearchInfoView> implements SearchInfoView {
    @BindView(R.id.search_info_viewpage)
    ViewPager mViewPage;
    //    @BindView(R.id.search_info_mRecyclerView)
//    RecyclerView mRecyclerView;
    @BindView(R.id.search_info_mRecyclerView2)
    RecyclerView mRecyclerView2;
    @BindView(R.id.bottom_list_layout)
    FrameLayout bottomListLayout;
    @BindView(R.id.hide_bottom_list)
    TextView hideBottomLayout;
    @BindView(R.id.isNeedShowBottom)
    TextView showBottomLayout;


    private List<SearchVideoVo> videoList = new ArrayList<>();
    SmartAdapter smartAdapter;
    SmartAdapter smartAdapter2;
    ScrollCalculatorHelper scrollCalculatorHelper;
    private boolean isPlayed;
    private int cateId;
    private int scrollY;

    @Override
    protected int getLayoutId() {
        cateId = getIntent().getIntExtra("categoryId", 0);
        return R.layout.activity_search_info;
    }

    @Override
    public void initEvent() {
        super.initEvent();

    }

    @Override
    protected SearchInfoPresent createPresenter() {
        return new SearchInfoPresent();
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getCategaryTwo(cateId);
    }

    @Override
    public void initView() {
        super.initView();
        int playTop = CommonUtil.getScreenHeight(mContext) / 2 - CommonUtil.dip2px(mContext, 100);
        int playBottom = CommonUtil.getScreenHeight(mContext) / 2 + CommonUtil.dip2px(mContext, 100);
        //自定播放帮助类
        scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.search_view_position, playTop, playBottom);
        smartAdapter = new SmartAdapter<SearchVideoVo>(videoList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_search_video_view;
            }

            @Override
            public void dealView(VH holder, SearchVideoVo data, int position) {
                SampleCoverVideo sampleCoverVideo = (SampleCoverVideo) holder.getChildView(R.id.search_view_position);
                sampleCoverVideo.setUpLazy(data.getVideo_url(), true, null, null, "");
                sampleCoverVideo.setIsTouchWiget(false);
                if (position == 0 && !isPlayed) {
                    sampleCoverVideo.startPlayLogic();
                    isPlayed = true;
                }

            }

        };
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(smartAdapter);
//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(mRecyclerView);
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            int firstVisibleItem, lastVisibleItem;
//
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
//                lastVisibleItem = layoutManager.findLastVisibleItemPosition() + 1;
//                scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
//                smartAdapter2.selectIndex(firstVisibleItem);
//                mRecyclerView2.scrollToPosition(firstVisibleItem);
//            }
//        });
        smartAdapter2 = new SmartAdapter<SearchVideoVo>(videoList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.layout1;
            }

            @Override
            public void dealView(VH holder, SearchVideoVo data, final int position) {
                holder.setNetImage(mContext, R.id.button1, data.getFirst_url());
                if (getSelectIndex() == position) {
                    holder.getChildView(R.id.button1).setSelected(true);
                } else {
                    holder.getChildView(R.id.button1).setSelected(false);
                }
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPage.setCurrentItem(position);
                        smartAdapter2.selectIndex(position);
                    }
                });
            }

        };
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView2.setLayoutManager(layoutManager2);
        mRecyclerView2.setAdapter(smartAdapter2);

    }

    private void viewPage() {
        final List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            SearchInfoVideoFragment fragment = SearchInfoVideoFragment.instance(i, videoList.get(i).getVideo_url());
            final int finalI = i;
            fragment.setVideoPlayerListener(new CallBack() {
                @Override
                public void callBack(String data) {
                    if (finalI + 1 < videoList.size()) {
                        mViewPage.setCurrentItem(finalI + 1);
                    } else {
                        mViewPage.setCurrentItem(0);
                    }
                }
            });
            fragmentList.add(fragment);
        }
        mViewPage.setPageTransformer(true, new CubeOutTransformer());
        mViewPage.setAdapter(new pageAdapter(getSupportFragmentManager(), fragmentList));
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((SearchInfoVideoFragment) fragmentList.get(position)).starVideo();
                smartAdapter2.selectIndex(position);
                mRecyclerView2.scrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int position) {


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void getSearchVideoInfo(List<SearchVideoVo> searchVideoVo) {
        if (null != searchVideoVo) {
            videoList.addAll(searchVideoVo);
            smartAdapter2.notifyDataSetChanged();
            viewPage();
        }
    }


    @OnClick({R.id.hide_bottom_list, R.id.isNeedShowBottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hide_bottom_list:
                showBottomLayout.setVisibility(View.VISIBLE);
                Log.i("SearchInfo","bottom:"+bottomListLayout.getBottom()+"hight"+bottomListLayout.getHeight());
                bottomListLayout.setVisibility(View.GONE);
//                bottomListLayout.scrollTo(0, bottomListLayout.getBottom());
                break;
            case R.id.isNeedShowBottom:
                showBottomLayout.setVisibility(View.GONE);
//                bottomListLayout.scrollTo(0, 0);
                bottomListLayout.setVisibility(View.VISIBLE);
                break;
        }
    }


    class pageAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragmentList;

        public pageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }

    }
}
