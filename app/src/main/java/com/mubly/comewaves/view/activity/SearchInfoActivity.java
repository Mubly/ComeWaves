package com.mubly.comewaves.view.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.SearchVideoVo;
import com.mubly.comewaves.present.SearchInfoPresent;
import com.mubly.comewaves.videoplayer.ScrollCalculatorHelper;
import com.mubly.comewaves.view.costomview.SampleCoverVideo;
import com.mubly.comewaves.view.interfaceview.SearchInfoView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.CubeInTransformer;
import com.youth.banner.transformer.CubeOutTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.FlipHorizontalTransformer;
import com.youth.banner.transformer.FlipVerticalTransformer;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
import com.youth.banner.transformer.RotateDownTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;
import com.youth.banner.transformer.StackTransformer;
import com.youth.banner.transformer.TabletTransformer;
import com.youth.banner.transformer.ZoomInTransformer;
import com.youth.banner.transformer.ZoomOutSlideTransformer;
import com.youth.banner.transformer.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 查询二级页面
 */
public class SearchInfoActivity extends BaseActivity<SearchInfoPresent, SearchInfoView> implements SearchInfoView {
    //        @BindView(R.id.search_info_viewpage)
//    ViewPager mViewPage;
    @BindView(R.id.search_info_mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_info_mRecyclerView2)
    RecyclerView mRecyclerView2;
    private View view1, view2, view3;
    private List<View> viewList;
    private List<SearchVideoVo> videoList = new ArrayList<>();
    SmartAdapter smartAdapter;
    SmartAdapter smartAdapter2;
    ScrollCalculatorHelper scrollCalculatorHelper;
    private boolean isPlayed;
    private int cateId;

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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(smartAdapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition() + 1;
                scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
                smartAdapter2.selectIndex(firstVisibleItem);
                mRecyclerView2.scrollToPosition(firstVisibleItem);
            }
        });
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

                        mRecyclerView.scrollToPosition(position);
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
//        final LayoutInflater lf = getLayoutInflater().from(this);
//        view1 = lf.inflate(R.layout.layout1, null);
//        view2 = lf.inflate(R.layout.layout2, null);
//        view3 = lf.inflate(R.layout.layout3, null);
//        viewList = new ArrayList<View>();
//        viewList.add(view1);
//        viewList.add(view2);
//        viewList.add(view3);
//        final PagerAdapter pagerAdapter = new PagerAdapter() {
//
//            @Override
//            public boolean isViewFromObject(View arg0, Object arg1) {
//
//                return arg0 == arg1;
//            }
//
//
//            @Override
//            public int getCount() {
//
//                return viewList.size();
//            }
//
////            @Override
////            public void notifyDataSetChanged() {
////                super.notifyDataSetChanged();
////            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position,
//                                    Object object) {
//                container.removeView(viewList.get(position));
//
//            }
//
//            @Override
//            public int getItemPosition(Object object) {
//
//                return super.getItemPosition(object);
//            }
//
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                container.addView(viewList.get(position));
//                return viewList.get(position);
//            }
//
//        };
//        mViewPage.setPageTransformer(true, new CubeOutTransformer());
//        mViewPage.setAdapter(pagerAdapter);
//        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
////                pagerAdapter.notifyDataSetChanged();
////                if (position == viewList.size() - 2) {
////                    viewList.add(lf.inflate(R.layout.layout1,null));
//////                    mViewPage.notify();
////                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int position) {
//
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
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
            smartAdapter.notifyDataSetChanged();
            smartAdapter2.notifyDataSetChanged();
        }
    }
}
