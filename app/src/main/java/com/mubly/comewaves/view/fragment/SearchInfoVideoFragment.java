package com.mubly.comewaves.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mubly.comewaves.R;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.interfaces.ScrollChange;
import com.mubly.comewaves.videoplayer.MyVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchInfoVideoFragment extends Fragment {

    private int type;
    private String videoUrl;
    MyVideoPlayer videoPlayer;
    CallBack callBack;
    ScrollChange scrollChange;

    public static SearchInfoVideoFragment instance(int type, String videoUrl) {
        SearchInfoVideoFragment fragment = new SearchInfoVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("videoUrl", videoUrl);
        fragment.setArguments(bundle);
        return fragment;

    }

    public void setVideoPlayerListener(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setScrollChangeListener(ScrollChange scrollChange) {
        this.scrollChange = scrollChange;
    }

    private void init() {
//        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        videoPlayer.setUp(videoUrl, true, "测试视频");
        videoPlayer.setIsTouchWiget(false);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        videoPlayer.startPlayLogic();
        initEvent();
    }

    private void initEvent() {
        videoPlayer.setVideoAllCallBack(new GSYSampleCallBack() {
            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
                if (null != callBack) {
                    callBack.callBack(null);
                }
            }
        });
        videoPlayer.setScrollChangeListener(new ScrollChange() {
            @Override
            public void scrollChange(float deltaX, float deltaY) {
                if (null != scrollChange) {
                    scrollChange.scrollChange(deltaX, deltaY);
                }

            }

            @Override
            public void scrollChanged(float deltaX, float deltaY) {
                if (null != scrollChange) {
                    scrollChange.scrollChanged(deltaX, deltaY);
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_info_video, container, false);
        videoPlayer = view.findViewById(R.id.StandardGSYVideoPlayer);
        type = getArguments().getInt("type", 0);
        videoUrl = getArguments().getString("videoUrl");
        if (type == 0) {
            init();
        }

        return view;
    }

    public void starVideo() {
        init();
    }
}
