package com.mubly.comewaves.view.fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.model.adapter.VideoWatchAdapter;
import com.mubly.comewaves.model.model.OnlineVideoListItem;
import com.mubly.comewaves.model.model.VideoListItem;
import com.volokh.danylo.video_player_manager.Config;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoidesListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    //视频数据，相当于普通adapter里的datas
    private List<VideoListItem> mLists = new ArrayList<>();

    //它充当ListItemsVisibilityCalculator和列表（ListView, RecyclerView）之间的适配器（Adapter）。
    private RecyclerViewItemPositionGetter mItemsPositionGetter;

    //ListItemsVisibilityCalculator可以追踪滑动的方向并在过程中计算每个Item的可见度
    //SingleListViewItemActiveCalculator会在滑动时获取每个View的可见度百分比.
    //所以其构造方法里需要传入mLists，而mLists里的每个item实现了ListItem接口
    //的getVisibilityPercents方法，也就是返回当前item可见度的方法.
    //这样ListItemsVisibilityCalculator就可以计算当前item的可见度了.

    private final ListItemsVisibilityCalculator mVideoVisibilityCalculator =
            new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mLists);


    //SingleVideoPlayerManager就是只能同时播放一个视频。
    //当一个view开始播放时，之前那个就会停止
    private final VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {
        }
    });

    private int mScrollState;
    private LinearLayoutManager mLayoutManager;
    private static final String URL =
            "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_voides_list, container, false);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        for (int i = 0; i < 10; ++i) {
            mLists.add(new OnlineVideoListItem(mVideoPlayerManager, "测试", "http://00imgmini.eastday.com/mobile/20190311/2019031111_5c19847902984f9ea4acebd7bcc5ab2f_5661_wmk.jpg", URL));
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        VideoWatchAdapter adapter = new VideoWatchAdapter(mLists);
        mRecyclerView.setAdapter(adapter);
        //这里是文档上默认的写法，直接复制下来。
        //查看了下源码其中VisibilityCalculator.onScrollStateIdle的这
        //个方法又调用了方法calculateMostVisibleItem，用来计算滑动状态改变时
        //的最大可见度的item.这个方法的计算方法是这样的：当view无论是向上还是
        //向下滚动时，在滚动的过程中，计算可见度最大的item。当滚动状态为空闲时
        //此时最后一个计算得出的可见度最大的item就是当前可见度最大的item
        //而onScroll方法是处理item滚出屏幕后的计算,用于发现新的活动item
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                mScrollState = scrollState;
                if(scrollState == RecyclerView.SCROLL_STATE_IDLE && !mLists.isEmpty()){

                    mVideoVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(!mLists.isEmpty()){
                    mVideoVisibilityCalculator.onScroll(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition() - mLayoutManager.findFirstVisibleItemPosition() + 1,
                            mScrollState);
                }
            }
        });

        mItemsPositionGetter = new RecyclerViewItemPositionGetter(mLayoutManager, mRecyclerView);
        return rootView;
    }
    //文档上的默认实现，复制下来
    //onResume()中调用方法，使屏幕亮起时启动对View的可见度的计算。
    @Override
    public void onResume() {
        super.onResume();
        if(!mLists.isEmpty()){
            // need to call this method from list view handler in order to have filled list

            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {

                    mVideoVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition());

                }
            });
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        mVideoPlayerManager.resetMediaPlayer(); // 页面不显示时, 释放播放器
    }

}
